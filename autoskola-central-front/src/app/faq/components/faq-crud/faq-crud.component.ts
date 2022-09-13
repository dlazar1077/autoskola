import { Component, OnDestroy, OnInit, ViewChild } from "@angular/core";
import { TranslateService } from "@ngx-translate/core";
import { ConfirmationService } from "primeng/api";
import { HttpService } from "src/app/core/services/http.service";
import { InfoService } from "src/app/core/services/info.service";

@Component({
    selector: 'app-faq-crud',
    templateUrl: './faq-crud.component.html',
    styleUrls: ['./faq-crud.component.scss']
  })
  export class FAQCrudComponent implements OnInit, OnDestroy {

    @ViewChild('dt') dt : any; 

    faqs : any;
    faq : any;
    odabranFaq: any;
    faqDialog : boolean = false;
    submitted : boolean = false;

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
        this.http.getHttp("FAQ").subscribe( (data:any) => {
            this.faqs = data;
        });
    }

    applyFilterGlobal(event : any, stringVal: any) {
        this.dt.filterGlobal((event.target as HTMLInputElement).value, stringVal);
    }

    hideDialog() {
        this.faqDialog = false;
        this.submitted = false;
    }

    editFAQ(faq: any) {
        this.faq = {...faq};
        this.faqDialog = true;
    }

    openNew() {
        this.faq = {};
        this.submitted = false;
        this.faqDialog = true;
    }

    saveFAQ() {
        this.submitted = true;

        if (this.checkForm()) {
            if (this.faq.faqId) {
                this.http.putHttp("updateFAQ",this.faq).subscribe( (data1:any) => {
                    this.getFAQ();
                    this.faq = {};
                    this.hideDialog();
                });
            }
            else {
                this.http.postHttp("insertFAQ",this.faq).subscribe( (data1:any) => {
                    this.getFAQ();
                    this.faq = {};
                    this.hideDialog();
                });
            }
        }
    }

    deleteFAQ(faq: any) {
        this.confirmationService.confirm({
            message: this.translateService.instant('errorMessage.sureToDelete') + faq.pitanje + '?',
            header: this.translateService.instant('buttons.confirm'),
            icon: 'pi pi-exclamation-triangle',
            acceptLabel: this.translateService.instant('buttons.yes'),
            rejectLabel: this.translateService.instant('buttons.no'),
            accept: () => {
                this.http.putHttp("deleteFAQ", faq).subscribe( (data1:any) => {
                    this.getFAQ();
                    this.faq = {};
                    this.hideDialog();
                });
            }
        });
    }

    checkForm() : boolean{
        if(this.faq.pitanje != "" && this.faq.pitanje != undefined){
            if(this.faq.odgovor != "" && this.faq.odgovor != undefined){
                return true;
            }
        }
        return false;
    }
  }