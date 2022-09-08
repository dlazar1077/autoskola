import { Component, OnInit, ViewChild } from "@angular/core";
import { TranslateService } from "@ngx-translate/core";
import { ConfirmationService } from "primeng/api";
import { HttpService } from "src/app/core/services/http.service";
import { InfoService } from "src/app/core/services/info.service";

@Component({
    selector: 'app-vozilo',
    templateUrl: './vozilo.component.html',
    styleUrls: ['./vozilo.component.scss']
  })
  export class VoziloComponent implements OnInit {

    @ViewChild('dt') dt : any; 

    vozila : any;
    vozilo : any;
    odabranoVozilo: any;
    voziloDialog : boolean = false;
    submitted : boolean = false;

    currentLanguage !: string;
    currentLangSubscription : any;

    codebooks: any;

  constructor(
    private http: HttpService, 
    private confirmationService: ConfirmationService, 
    private translateService : TranslateService,
    public infoService : InfoService
    ) { }

  ngOnInit(): void {
    this.currentLanguage = this.translateService.currentLang;
    this.currentLangSubscription = this.translateService.onLangChange.subscribe((response: any) => {
      this.translateService.get(['buttons']).subscribe( () => {
        this.getVozila();
      });
    });

    this.http.getCodebooks(["kategorije"]).subscribe( (data:any) => {
        this.getVozila();
        this.codebooks = data;
    });
    
    }

    getVozila(){
        this.http.getHttp("vozila").subscribe( (data1:any) => {
            this.vozila = data1.tableData;
        });
    }

    applyFilterGlobal(event : any, stringVal: any) {
        this.dt.filterGlobal((event.target as HTMLInputElement).value, stringVal);
    }

    hideDialog() {
        this.voziloDialog = false;
        this.submitted = false;
    }

    editVozilo(vozilo: any) {
        this.vozilo = {...vozilo};
        this.voziloDialog = true;
    }

    openNew() {
        this.vozilo = {};
        this.submitted = false;
        this.voziloDialog = true;
    }

    saveVozilo() {
        this.submitted = true;

        if (this.checkVozilo()) {
            if (this.vozilo.voziloId) {
                this.http.putHttp("updateVozilo",this.vozilo).subscribe( (data1:any) => {
                    this.getVozila();
                    this.vozilo = {};
                    this.hideDialog();
                });
            }
            else {
                this.http.postHttp("insertVozilo",this.vozilo).subscribe( (data1:any) => {
                    this.getVozila();
                    this.vozilo = {};
                    this.hideDialog();
                });
            }
        }
    }

    deleteVozilo(vozilo: any) {
        this.confirmationService.confirm({
            message: this.translateService.instant('errorMessage.sureToDelete') + vozilo.markaVozila + ' ' + vozilo.model + '?',
            header: this.translateService.instant('buttons.confirm'),
            icon: 'pi pi-exclamation-triangle',
            acceptLabel: this.translateService.instant('buttons.yes'),
            rejectLabel: this.translateService.instant('buttons.no'),
            accept: () => {
                this.http.putHttp("deleteVozilo", vozilo).subscribe( (data1:any) => {
                    this.getVozila();
                    this.vozilo = {};
                    this.hideDialog();
                });
            }
        });
    }

    categoryConverter(categoryId : any) : String{
        return this.codebooks.kategorije.find((x:any) => x.id.toString() === categoryId.toString()).name;
    }

    checkVozilo():boolean{
        if(this.vozilo.markaVozila !== '' && this.vozilo.markaVozila !== undefined){
            if(this.vozilo.model !== '' && this.vozilo.model !== undefined){
                return true;
            }
        }
        return false;
    }


  }