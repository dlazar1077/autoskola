import { Component, OnDestroy, OnInit, ViewChild } from "@angular/core";
import { TranslateService } from "@ngx-translate/core";
import { ConfirmationService } from "primeng/api";
import { HttpService } from "src/app/core/services/http.service";
import { InfoService } from "src/app/core/services/info.service";
import { Instruktor } from "../../core/models/instruktor";

@Component({
    selector: 'app-instruktor',
    templateUrl: './instruktor.component.html',
    styleUrls: ['./instruktor.component.scss']
  })
  export class InstruktorComponent implements OnInit, OnDestroy{

    @ViewChild('dt') dt : any; 

    instruktori !: any;
    instruktor !: any;
    odabranInstruktor: any;
    instruktorDialog : boolean = false;
    submitted : boolean = false;

    currentLanguage !: string;
    currentLangSubscription : any;

    vozila:any;
    prvaLozinka : string = "";

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
        this.getInstruktori();
      });
    });

    this.http.getCodebooks(["kategorije"]).subscribe( (data:any) => {
        this.getInstruktori();
        this.codebooks = data;
    });

    
    this.http.getHttp("vozila").subscribe( (vehicles:any) => {
        this.vozila = vehicles.tableData;
    });
    
    }

    ngOnDestroy(): void {
        this.currentLangSubscription.unsubscribe();
    }

    getInstruktori(){
        this.http.getHttp("instruktori").subscribe( (data:any) => {
            this.instruktori = data;
        });
    }

    applyFilterGlobal(event : any, stringVal: any) {
        this.dt.filterGlobal((event.target as HTMLInputElement).value, stringVal);
    }

    hideDialog() {
        this.instruktorDialog = false;
        this.submitted = false;
    }

    editInstruktor(instruktor: any) {
        this.instruktor = {...instruktor};
        this.instruktorDialog = true;
        this.prvaLozinka = instruktor.korisnik.lozinka;
    }

    openNew() {
        this.instruktor = new Instruktor();
        this.submitted = false;
        this.instruktorDialog = true;
    }

    saveInstruktor() {
        this.submitted = true;
        console.log(this.checkForm())
        if (this.checkForm()) {
            if (this.instruktor.instruktorId) {
                if(this.instruktor.korisnik.lozinka === this.prvaLozinka){
                    this.instruktor.korisnik.lozinka = "";
                }
                this.http.putHttp("updateInstruktor",this.instruktor).subscribe( (data1:any) => {
                    this.getInstruktori();
                    this.instruktor = new Instruktor();
                    this.hideDialog();
                });
            }
            else {
                if(this.instruktor.korisnik.lozinka !== undefined || this.instruktor.korisnik.lozinka !== "null"){
                    this.instruktor.korisnik.ulogaId = 2;
                    this.http.postHttp("insertInstruktor",this.instruktor).subscribe( (data1:any) => {
                        this.getInstruktori();
                        this.instruktor = new Instruktor();
                        this.hideDialog();
                 });
                }
            }
        }
    }

    deleteInstruktor(instruktor: any) {
        this.confirmationService.confirm({
            message: this.translateService.instant('errorMessage.sureToDelete') + instruktor.korisnik.ime + ' ' + instruktor.korisnik.prezime + '?',
            header: this.translateService.instant('buttons.confirm'),
            icon: 'pi pi-exclamation-triangle',
            acceptLabel: this.translateService.instant('buttons.yes'),
            rejectLabel: this.translateService.instant('buttons.no'),
            accept: () => {
                this.http.putHttp("deleteInstruktor", instruktor).subscribe( (data1:any) => {
                    this.getInstruktori();
                    this.instruktor = new Instruktor();
                    this.hideDialog();
                });
            }
        });
    }

    categoryConverter(categoryId : any) : String{
        return this.codebooks.kategorije.find((x:any) => x.id.toString() === categoryId.toString()).name;
    }

    checkForm() : boolean {
        if(this.instruktor.brojSlobodnihMjesta === undefined || this.instruktor.brojSlobodnihMjesta === null) return false;
        if(this.instruktor.korisnik.ime === undefined || this.instruktor.korisnik.ime === "") return false;
        if(this.instruktor.korisnik.prezime === undefined || this.instruktor.korisnik.prezime === "") return false;
        if(this.instruktor.korisnik.korisnickoIme === undefined || this.instruktor.korisnik.korisnickoIme === "") return false;
        if(this.instruktor.korisnik.oib === undefined || this.instruktor.korisnik.oib === "") return false;
        if(this.instruktor.korisnik.email === undefined || this.instruktor.korisnik.email === "") return false;
        
        return true;
    }
  }