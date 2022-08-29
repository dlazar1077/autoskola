import { Component, OnInit, ViewChild } from "@angular/core";
import { TranslateService } from "@ngx-translate/core";
import { ConfirmationService } from "primeng/api";
import { HttpService } from "src/app/core/services/http.service";
import { InfoService } from "src/app/core/services/info.service";

@Component({
    selector: 'app-autoskola-info',
    templateUrl: './autoskolaInfo.component.html',
    styleUrls: ['./autoskolaInfo.component.scss']
  })
  export class AutoskolaInfoComponent implements OnInit {

    @ViewChild('dt') dt : any; 

    autoskolaInfos : any;
    autoskolaInfo : any;
    odabranaAutoskolaInfo: any;
    autoskolaInfosDialog : boolean = false;
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
            this.getAutoskolaInfo();
          });
        });
        this.getAutoskolaInfo();
    }
        
    getAutoskolaInfo(){
        this.http.getHttp("autoskolaInfo").subscribe( (data:any) => {
            this.autoskolaInfos = data;
            this.infoService.getAutoskolaInfo();
        });
    }

    applyFilterGlobal(event : any, stringVal: any) {
        this.dt.filterGlobal((event.target as HTMLInputElement).value, stringVal);
    }

    hideDialog() {
        this.autoskolaInfosDialog = false;
        this.submitted = false;
    }

    editAutoskolaInfo(autoskolaInfo: any) {
        this.autoskolaInfo = {...autoskolaInfo};
        this.autoskolaInfosDialog = true;
    }

    openNew() {
        this.autoskolaInfo = {};
        this.submitted = false;
        this.autoskolaInfosDialog = true;
    }

    saveAutoskolaInfo() {
        this.submitted = true;

        if (this.autoskolaInfo.sifra.trim()) {
            if (this.autoskolaInfo.autoskolaInfoId) {
                this.http.putHttp("updateAutoskolaInfo",this.autoskolaInfo).subscribe( (data1:any) => {
                    this.getAutoskolaInfo();
                    this.autoskolaInfo = {};
                    this.hideDialog();
                });
            }
            else {
                this.http.postHttp("insertAutoskolaInfo",this.autoskolaInfo).subscribe( (data1:any) => {
                    this.getAutoskolaInfo();
                    this.autoskolaInfo = {};
                    this.hideDialog();
                });
            }
        }
    }

    deleteAutoskolaInfo(autoskolaInfo: any) {
        this.confirmationService.confirm({
            message: this.translateService.instant('errorMessage.sureToDelete') + autoskolaInfo.sifra + '?',
            header: this.translateService.instant('buttons.confirm'),
            icon: 'pi pi-exclamation-triangle',
            acceptLabel: this.translateService.instant('buttons.yes'),
            rejectLabel: this.translateService.instant('buttons.no'),
            accept: () => {
                this.http.putHttp("deleteAutoskolaInfo", autoskolaInfo).subscribe( (data1:any) => {
                    this.getAutoskolaInfo();
                    this.autoskolaInfo = {};
                    this.hideDialog();
                });
            }
        });
    }

  }