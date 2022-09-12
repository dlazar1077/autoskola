import { Injectable } from '@angular/core';
import { HttpService } from './http.service';

@Injectable({
  providedIn: 'root'
})
export class InfoService {

    autoskolaInfo : any;

    nazivAutoskole !: String;
    brojZapisa !: number;
    adresa !: string;
    email !: string;
    oNama !:string;
    kontaktBroj!:string;

    constructor(private http: HttpService) { }

    getAutoskolaInfo(){
        this.http.getHttp("autoskolaInfo").subscribe( (data:any) => {
            this.autoskolaInfo = data;
            this.nazivAutoskole = data.find((x:any) => x.sifra == "NASLOV_AUTOSKOLE").vrijednost;
            this.brojZapisa = data.find((x:any) => x.sifra == "BROJ_ZAPISA").vrijednost;
            this.adresa = data.find((x:any) => x.sifra == "ADRESA_AUTOSKOLE").vrijednost;
            this.email = data.find((x:any) => x.sifra == "EMAIL_ADRESA").vrijednost;
            this.oNama = data.find((x:any) => x.sifra == "O_NAMA").vrijednost;
            this.kontaktBroj = data.find((x:any) => x.sifra == "KONTAKT_BROJ").vrijednost;
        });
    }

}