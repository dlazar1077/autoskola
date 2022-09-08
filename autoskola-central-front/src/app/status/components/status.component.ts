import { Component, OnInit, ViewChild } from "@angular/core";
import { TranslateService } from "@ngx-translate/core";
import { ConfirmationService } from "primeng/api";
import { HttpService } from "src/app/core/services/http.service";
import { InfoService } from "src/app/core/services/info.service";

@Component({
    selector: 'app-status',
    templateUrl: './status.component.html',
    styleUrls: ['./status.component.scss']
  })
  export class StatusComponent implements OnInit {

    @ViewChild('dt') dt : any; 

    statusi : any;
    status : any;
    odabraniStatus: any;
    statusDialog : boolean = false;
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
      this.translateService.get(['role', 'buttons']).subscribe( () => {
        this.getStatusi();
      });
    });
    this.getStatusi();
    }

    getStatusi(){
        this.http.getHttp("statusiPolaznika").subscribe( (data1:any) => {
            this.statusi = data1.tableData;
        });
    }

    applyFilterGlobal(event : any, stringVal: any) {
        this.dt.filterGlobal((event.target as HTMLInputElement).value, stringVal);
    }

    hideDialog() {
        this.statusDialog = false;
        this.submitted = false;
    }

    editStatus(status: any) {
        this.status = {...status};
        this.statusDialog = true;
    }

    openNew() {
        this.status = {};
        this.submitted = false;
        this.statusDialog = true;
    }

    saveStatus() {
        this.submitted = true;

        if (this.status.sifra.trim()) {
            if (this.status.statusPolaznikaId) {
                this.http.putHttp("updateStatusPolaznika",this.status).subscribe( (data1:any) => {
                    this.getStatusi();
                    this.status = {};
                    this.hideDialog();
                });
            }
            else {
                this.http.postHttp("insertStatusPolaznika",this.status).subscribe( (data1:any) => {
                    this.getStatusi();
                    this.status = {};
                    this.hideDialog();
                });
            }
        }
    }

    deleteStatus(status: any) {
        this.confirmationService.confirm({
            message: this.translateService.instant('errorMessage.sureToDelete') + status.naziv + '?',
            header: this.translateService.instant('buttons.confirm'),
            icon: 'pi pi-exclamation-triangle',
            acceptLabel: this.translateService.instant('buttons.yes'),
            rejectLabel: this.translateService.instant('buttons.no'),
            accept: () => {
                this.http.putHttp("deleteStatusPolaznika", status).subscribe( (data1:any) => {
                    this.getStatusi();
                    this.status = {};
                    this.hideDialog();
                });
            }
        });
    }
  }