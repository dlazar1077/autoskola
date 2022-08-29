import { Component, OnInit, ViewChild } from "@angular/core";
import { TranslateService } from "@ngx-translate/core";
import { ConfirmationService } from "primeng/api";
import { HttpService } from "src/app/core/services/http.service";
import { InfoService } from "src/app/core/services/info.service";

@Component({
  selector: 'app-kategorija',
  templateUrl: './kategorija.component.html',
  styleUrls: ['./kategorija.component.scss']
})
export class KategorijaComponent implements OnInit {

    @ViewChild('dt') dt : any; 

    kategorije : any;
    kategorija : any;
    odabranaKategorija: any;
    kategorijaDialog : boolean = false;
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
      this.translateService.get(['category', 'buttons']).subscribe( () => {
        this.getKategorije();
      });
    });
    this.getKategorije();
    }

    getKategorije(){
        this.http.getHttp("kategorije").subscribe( (data1:any) => {
            this.kategorije = data1.tableData;
        });
    }

    applyFilterGlobal(event : any, stringVal: any) {
        this.dt.filterGlobal((event.target as HTMLInputElement).value, stringVal);
    }

    hideDialog() {
        this.kategorijaDialog = false;
        this.submitted = false;
    }

    editKategorija(kategorija: any) {
        this.kategorija = {...kategorija};
        this.kategorijaDialog = true;
    }

    openNew() {
        this.kategorija = {};
        this.submitted = false;
        this.kategorijaDialog = true;
    }

    saveKategorija() {
        this.submitted = true;

        if (this.kategorija.sifra.trim()) {
            if (this.kategorija.kategorijaId) {
                this.http.putHttp("updateKategorija",this.kategorija).subscribe( (data1:any) => {
                    this.getKategorije();
                    this.kategorija = {};
                    this.hideDialog();
                });
            }
            else {
                this.http.postHttp("insertKategorija",this.kategorija).subscribe( (data1:any) => {
                    this.getKategorije();
                    this.kategorija = {};
                    this.hideDialog();
                });
            }
        }
    }

    deleteKategorija(kategorija: any) {
        this.confirmationService.confirm({
            message: this.translateService.instant('errorMessage.sureToDelete') + kategorija.naziv + '?',
            header: this.translateService.instant('buttons.confirm'),
            icon: 'pi pi-exclamation-triangle',
            acceptLabel: this.translateService.instant('buttons.yes'),
            rejectLabel: this.translateService.instant('buttons.no'),
            accept: () => {
                this.http.putHttp("deleteKategorija", kategorija).subscribe( (data1:any) => {
                    this.getKategorije();
                    this.kategorija = {};
                    this.hideDialog();
                });
            }
        });
    }
}