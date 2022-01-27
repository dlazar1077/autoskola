import { Component, OnInit } from '@angular/core';
import { TranslateService } from '@ngx-translate/core';
import { MenuItem } from 'primeng/api';
import { AuthService } from '../../services/auth.service';

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

  constructor(private translateService : TranslateService, private authService : AuthService) { }

  ngOnInit(): void {
    
    
    this.currentLanguage = this.translateService.currentLang;
    this.currentLangSubscription = this.translateService.onLangChange.subscribe((response: any) => {
      this.translateService.get(['navbar', 'language']).subscribe( () => {
        this.getMenubarItems();
        this.getLanguages();
      });
    });
    //this.getMenubarItems();
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
        label: this.translateService.instant('navbar.about'),
        icon : 'pi pi-book',
        routerLink: ['/about'],
        routerLinkActiveOptions: { exact: true }
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
    console.log(this.translateService.currentLang);
  }

  isLoggedIn(){
    return this.authService.isLoggedIn() ? false : true;
  }

  logout(){
    this.authService.logout();
  }

}
