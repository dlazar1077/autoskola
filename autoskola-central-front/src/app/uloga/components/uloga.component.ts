import { Component, OnDestroy, OnInit, ViewChild } from "@angular/core";
import { TranslateService } from "@ngx-translate/core";
import { ConfirmationService } from "primeng/api";
import { HttpService } from "src/app/core/services/http.service";
import { InfoService } from "src/app/core/services/info.service";

@Component({
    selector: 'app-uloga',
    templateUrl: './uloga.component.html',
    styleUrls: ['./uloga.component.scss']
  })
  export class UlogaComponent implements OnInit, OnDestroy {

    @ViewChild('dt') dt : any; 

    uloge : any;
    uloga : any;
    odabranaUloga: any;
    ulogaDialog : boolean = false;
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
        this.getUloge();
      });
    });
    this.getUloge();
    }

    ngOnDestroy(): void {
        this.currentLangSubscription.unsubscribe();
    }

    getUloge(){
        this.http.getHttp("uloge").subscribe( (data1:any) => {
            this.uloge = data1.tableData;
        });
    }

    applyFilterGlobal(event : any, stringVal: any) {
        this.dt.filterGlobal((event.target as HTMLInputElement).value, stringVal);
    }

    hideDialog() {
        this.ulogaDialog = false;
        this.submitted = false;
    }

    editUloga(uloga: any) {
        this.uloga = {...uloga};
        this.ulogaDialog = true;
    }

    openNew() {
        this.uloga = {};
        this.submitted = false;
        this.ulogaDialog = true;
    }

    saveUloga() {
        this.submitted = true;

        if (this.uloga.sifra.trim()) {
            if (this.uloga.ulogaId) {
                this.http.putHttp("updateUloge",this.uloga).subscribe( (data1:any) => {
                    this.getUloge();
                    this.uloga = {};
                    this.hideDialog();
                });
            }
            else {
                this.http.postHttp("insertUloge",this.uloga).subscribe( (data1:any) => {
                    this.getUloge();
                    this.uloga = {};
                    this.hideDialog();
                });
            }

            /*this.products = [...this.products];
            this.productDialog = false;
            this.product = {};*/
        }
    }

    deleteUloga(uloga: any) {
        this.confirmationService.confirm({
            message: this.translateService.instant('errorMessage.sureToDelete') + uloga.naziv + '?',
            header: this.translateService.instant('buttons.confirm'),
            icon: 'pi pi-exclamation-triangle',
            acceptLabel: this.translateService.instant('buttons.yes'),
            rejectLabel: this.translateService.instant('buttons.no'),
            accept: () => {
                this.http.putHttp("deleteUloge", uloga).subscribe( (data1:any) => {
                    this.getUloge();
                    this.uloga = {};
                    this.hideDialog();
                });
            }
        });
    }

  }