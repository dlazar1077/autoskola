import { Injectable } from '@angular/core';
import { HttpService } from './http.service';

@Injectable({
  providedIn: 'root'
})
export class InfoService {

    autoskolaInfo : any;

    nazivAutoskole !: String;
    brojZapisa !: number;

    constructor(private http: HttpService) { }

    getAutoskolaInfo(){
        this.http.getHttp("autoskolaInfo").subscribe( (data:any) => {
            this.autoskolaInfo = data;
            this.nazivAutoskole = data.find((x:any) => x.sifra == "NASLOV_AUTOSKOLE").vrijednost;
            this.brojZapisa = data.find((x:any) => x.sifra == "BROJ_ZAPISA").vrijednost;
        });
    }

}