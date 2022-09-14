import { Component, Input, OnDestroy, OnInit, ViewChild } from "@angular/core";
import { Router } from "@angular/router";
import { TranslateService } from "@ngx-translate/core";
import { Ispit } from "src/app/core/models/ispit";
import { Odabir } from "src/app/core/models/odabir";
import { Pitanje } from "src/app/core/models/pitanje";
import { AuthService } from "src/app/core/services/auth.service";
import { HttpService } from "src/app/core/services/http.service";
import { InfoService } from "src/app/core/services/info.service";

@Component({
    selector: 'app-pregled-ispita',
    templateUrl: './pregled-ispita.component.html',
    styleUrls: ['./pregled-ispita.component.scss']
  })
  export class PregledIspitaComponent implements OnInit, OnDestroy {

    @ViewChild('dt') dt : any; 

    ispiti !: Ispit[];

    ispit !: Ispit;
    odabraniIspit: any;
    ispitDialog : boolean = false;
    submitted : boolean = false;

    ispitInfo !: Ispit;

    currentLanguage !: string;
    currentLangSubscription : any;

    constructor(
      public authService : AuthService,
      public infoService : InfoService,
      private http : HttpService,
      private translateService : TranslateService,
      private router: Router
      ){}

    ngOnInit(): void {
      this.currentLanguage = this.translateService.currentLang;
      this.currentLangSubscription = this.translateService.onLangChange.subscribe((response: any) => {
      this.translateService.get(['category', 'buttons']).subscribe( () => {
          this.getIspiti();
        });
      });
      this.getIspiti();
    }

    ngOnDestroy(): void {
      this.currentLangSubscription.unsubscribe();
    }

    getIspiti(){
      if(this.authService.isAdminRoleRight()){
        var user = this.authService.getUser();
        this.http.getHttp("ispitiKorisnika", {id: user.id}).subscribe( (data : any) => {
        });
      } else if(this.authService.isModerator()){
        var user = this.authService.getUser();
        this.http.getHttp("ispitiKorisnika", {id: user.id}).subscribe( (data : any) => {
        });
      } else{
        var user = this.authService.getUser();
        this.http.getHttp("ispitiKorisnika", {id: user.id}).subscribe( (data : any) => {
            this.ispiti = data;
        });
      }
    }

    applyFilterGlobal(event : any, stringVal: any) {
      this.dt.filterGlobal((event.target as HTMLInputElement).value, stringVal);
  }

  pogledajIspit(ispitId: string){
    this.http.getHttp("ispitById",{id: ispitId}).subscribe((data: any) => {
      this.ispitInfo = data;
      this.ispitDialog = true;
    });
  }

  hideDialog(){
    this.ispitDialog = false;
  }

  getArrayLength(array : any[]) : number{
    return array.length;
  }

  checkOdabir(odabir : Odabir, pitanje: Pitanje) : string{
    if(odabir.tocanOdgovor === true){
      return 'green';
    } else if (pitanje.odgovor === null){
      return '';
    } else if (odabir.tocanOdgovor === false && pitanje.odgovor.includes(odabir.sifra) ){
      return 'red';
    } else {
      return '';
    }
  }

  checkIfMultipleQuestionIsOk(odabir : Odabir[], pitanje: Pitanje) : string{
    var tocnost : boolean = true;
    for(let j = 0; j < pitanje.odabiri.length; j++){
      if(pitanje.odabiri[j].tocanOdgovor === true && !pitanje.odgovor?.includes(pitanje.odabiri[j].sifra)){
        tocnost = false;
      } else if (pitanje.odabiri[j].tocanOdgovor === false && pitanje.odgovor?.includes(pitanje.odabiri[j].sifra)){
        tocnost = false;
      }
    }
    return tocnost ? "green" : "red";
  }

  checkIfInputQuestionIsOk(pitanje : Pitanje) : string{
    if(pitanje.odabiri[0].tekst === pitanje.odgovor){
      return "green";
    } else{
      return "red"
    }
  }

  getClassAboutStatus(status : String){
    if(status.includes("PROLAZ")){
      return "green";
    } else if ( status.includes("PAD")){
      return "red";
    } else {
      return "";
    }
  }

}