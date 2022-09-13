import { Component, OnDestroy, OnInit } from "@angular/core";
import { TranslateService } from "@ngx-translate/core";
import { ConfirmationService } from "primeng/api";
import { HttpService } from "src/app/core/services/http.service";
import { InfoService } from "src/app/core/services/info.service";

@Component({
    selector: 'app-faq',
    templateUrl: './faq.component.html',
    styleUrls: ['./faq.component.scss']
  })
export class FAQComponent implements OnInit, OnDestroy {

  faqs : any;

  currentLanguage !: string;
  currentLangSubscription : any;

  constructor(
    private http: HttpService, 
    private confirmationService: ConfirmationService, 
    private translateService : TranslateService,
    public infoService : InfoService
    ) { }

  ngOnInit(): void {
    this.currentLanguage = this.translateService.currentLang;
    this.currentLangSubscription = this.translateService.onLangChange.subscribe((response: any) => {
    this.translateService.get(['autoskolaInfo', 'buttons']).subscribe( () => {
        this.getFAQ();
      });
    });
    this.getFAQ();
  }

  ngOnDestroy(): void {
    this.currentLangSubscription.unsubscribe();
  }

  getFAQ(){
    this.http.getHttp("FAQ").subscribe( (data : any) => {
      this.faqs = data;
    });
  }
}