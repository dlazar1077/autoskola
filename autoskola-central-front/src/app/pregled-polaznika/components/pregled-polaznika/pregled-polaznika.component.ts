import { Component, OnDestroy, OnInit, ViewChild } from "@angular/core";
import { TranslateService } from "@ngx-translate/core";
import { ConfirmationService } from "primeng/api";
import { Polaznik } from "src/app/core/models/polaznik";
import { Vozilo } from "src/app/core/models/vozilo";
import { AuthService } from "src/app/core/services/auth.service";
import { HttpService } from "src/app/core/services/http.service";
import { InfoService } from "src/app/core/services/info.service";
import { SharedService } from "src/app/core/services/shared.service";

@Component({
    selector: 'app-pregled-polaznika',
    templateUrl: './pregled-polaznika.component.html',
    styleUrls: ['./pregled-polaznika.component.scss']
  })
  export class PregledPolaznikaComponent implements OnInit, OnDestroy {
    @ViewChild('dt') dt : any; 

    prikazi : boolean = false;

    instruktor : any;
    polaznici !: Polaznik[];
    polaznik !: Polaznik;
    odabraniPolaznik: any;
    polaznikDialog : boolean = false;
    submitted : boolean = false;

    currentLanguage !: string;
    currentLangSubscription : any;

    codebooks: any;

    vozila !: Vozilo[];

    constructor(
        private http: HttpService, 
        private confirmationService: ConfirmationService, 
        private translateService : TranslateService,
        private authService : AuthService,
        public infoService : InfoService,
        private sharedService : SharedService
        ) { }

    ngOnInit(): void {
        this.currentLanguage = this.translateService.currentLang;
        this.currentLangSubscription = this.translateService.onLangChange.subscribe((response: any) => {
        this.translateService.get(['buttons']).subscribe( () => {
            //this.getPolazniciData();
            });
        });

        this.http.getCodebooks(["kategorije, statusPolaznika"]).subscribe( (data:any) => {
            this.getPolazniciData(); 
            this.codebooks = data;
        });

        this.sharedService.getRefresh().subscribe((value: boolean) => {
            if(value) {
                this.prikazi = false;
                this.prikazi = true;
                this.getPolazniciData();
            }
          
        })
    }

    ngOnDestroy(): void {
        this.currentLangSubscription.unsubscribe();
    }

    getPolazniciData(){
        if(this.authService.isAdminRoleRight()){
            var user = this.authService.getUser();
        } else if(this.authService.isModerator()){
            var user = this.authService.getUser();
            this.http.getHttp("instruktor", {id: user.id}).subscribe( (data : any) => {
                this.instruktor = data;
                this.http.getHttp("polazniciInstruktora", {id: this.instruktor.instruktorId}).subscribe( (data1 : any) => {
                    this.polaznici = data1;
                });
                this.http.getHttp("vozilaInstruktora", {id: this.instruktor.instruktorId}).subscribe( (data : any) => {
                    this.vozila = data.tableData;
                });
            });
        } else{
        }
    }

    applyFilterGlobal(event : any, stringVal: any) {
        this.dt.filterGlobal((event.target as HTMLInputElement).value, stringVal);
    }

    categoryConverter(categoryId : any) : String{
        if(categoryId !== undefined)
        return this.codebooks.kategorije.find((x:any) => x.id.toString() === categoryId.toString()).name;
        else return "";
    }

    statusConverter(statusId : any) : String{
        return this.codebooks.statusPolaznika.find((x:any) => x.id.toString() === statusId.toString()).name;
    }

    openPolaznik(polaznik : Polaznik){
        this.polaznik = polaznik;
        this.prikazi = true;
    }

    polozenaVoznja(polaznik: Polaznik){
        polaznik.statusPolaznikaId = this.codebooks.statusPolaznika.find((x:any) => x.name.toString() === "Položeno").id
        polaznik.korisnik.lozinka= "";
        this.http.putHttp("updatePolaznik", polaznik).subscribe( (data : any) => {
            this.getPolazniciData();
        });
    }

  }