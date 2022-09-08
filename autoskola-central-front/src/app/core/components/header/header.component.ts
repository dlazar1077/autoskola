import { Component, OnInit } from '@angular/core';
import { NavigationEnd, Router } from '@angular/router';
import { TranslateService } from '@ngx-translate/core';
import { MenuItem } from 'primeng/api';
import { AuthService } from '../../services/auth.service';
import { InfoService } from '../../services/info.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit {

  items!: MenuItem[];

  languages : any;
  selectedLanguage : any;

  currentLanguage !: string;
  currentLangSubscription : any;

  info : any = this.infoService; 

  route !: String;

  constructor(private translateService : TranslateService, private authService : AuthService, private router : Router, public infoService : InfoService) { }

  ngOnInit(): void {
    this.currentLanguage = this.translateService.currentLang;
    this.currentLangSubscription = this.translateService.onLangChange.subscribe((response: any) => {
      this.translateService.get(['navbar', 'language']).subscribe( () => {
        this.getMenubarItems();
        this.getLanguages();
      });
    });

    this.router.events.subscribe((val : any) => {
      if (val instanceof NavigationEnd) {
        this.route = val.url.split('/')[1];
        this.getMenubarItems();
      }
    });
  }

  getMenubarItems(){
    this.items = [
      {
          label: this.translateService.instant('navbar.home'),
          icon : 'pi pi-home',
          routerLink: ['/home'],
          routerLinkActiveOptions: { exact: true }
      },
      {
        label: this.translateService.instant('navbar.myProfile'),
        icon : 'pi pi-user',
        visible : this.authService.isLoggedIn() !== undefined,
        routerLink: ['/myProfile'],
        routerLinkActiveOptions: { exact: true }
      },
      {
        label: this.translateService.instant('navbar.about'),
        icon : 'pi pi-book',
        routerLink: ['/about'],
        routerLinkActiveOptions: { exact: true }
      },
      {
        label: this.translateService.instant('navbar.administrator'),
        visible : this.authService.isAdminRoleRight(),
        items : [
          {
            label: this.translateService.instant('navbar.category'),
            routerLink: ['/category'],
            routerLinkActiveOptions: { exact: true }
          },
          {
            label: this.translateService.instant('navbar.roles'),
            routerLink: ['/roles'],
            routerLinkActiveOptions: { exact: true }
          },
          {
            label: this.translateService.instant('navbar.drivingSchoolInfo'),
            routerLink: ['/drivingSchoolInfo'],
            routerLinkActiveOptions: { exact: true }
          },
          {
            label: this.translateService.instant('navbar.vehicle'),
            routerLink: ['/vehicle'],
            routerLinkActiveOptions: { exact: true }
          }
          ,
          {
            label: this.translateService.instant('navbar.question'),
            routerLink: ['/question'],
            routerLinkActiveOptions: { exact: true }
          },
          {
            label: this.translateService.instant('navbar.instructor'),
            routerLink: ['/instructor'],
            routerLinkActiveOptions: { exact: true }
          },
          {
            label: this.translateService.instant('navbar.statuses'),
            routerLink: ['/statuses'],
            routerLinkActiveOptions: { exact: true }
          }
        ]
      }
    ];
  }

  getLanguages(){
    this.languages = [
      {name : this.translateService.instant('language.hr'), value : "hr", flag : "hr"},
      {name : this.translateService.instant('language.en'), value: "en", flag : "gb"}
    ];
  }

  changeLanguage(){
    this.translateService.use(this.selectedLanguage);
  }

  changeLang(){
    this.currentLanguage = this.currentLanguage === 'hr' ? 'en' : 'hr';
    this.translateService.use(this.currentLanguage);
  }

  isLoggedIn(){
    return this.authService.isLoggedIn() ? false : true;
  }

  logout(){
    this.authService.logout();
  }

}
