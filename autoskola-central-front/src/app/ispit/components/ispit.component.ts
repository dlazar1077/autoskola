import { Component, HostListener, OnInit } from "@angular/core";
import { Router } from "@angular/router";
import { TranslateService } from "@ngx-translate/core";
import { ConfirmationService } from "primeng/api";
import { ComponentCanDeactivate } from "src/app/core/guards/pending-changes.guard";
import { Ispit } from "src/app/core/models/ispit";
import { Pitanje } from "src/app/core/models/pitanje";
import { AuthService } from "src/app/core/services/auth.service";
import { HttpService } from "src/app/core/services/http.service";
@Component({
    selector: 'app-ispit',
    templateUrl: './ispit.component.html',
    styleUrls: ['./ispit.component.scss']
})

export class IspitComponent implements OnInit, ComponentCanDeactivate {

    // @HostListener allows us to also guard against browser refresh, close, etc.
  @HostListener('window:beforeunload',  ['$event'])
  canDeactivate(): Promise<boolean> | boolean {
    if(this.canLeaveExam) return true;
    return false;
  }

    canLeaveExam : boolean = false;

    ispit : Ispit = new Ispit();

    pitanja : Pitanje[] = [];

    subscription:any;
    browserRefresh:any;

    constructor(
        private http: HttpService,
        private authService : AuthService,
        private confirmationService : ConfirmationService,
        private translateService : TranslateService,
        private router : Router
    ) { }

    ngOnInit(): void {
        this.http.getHttp("generirajIspit").subscribe((data : any) => {
            console.log(data);
            this.ispit.korisnikId = this.authService.getUser().id;
            //this.pitanja = data;
            this.pitanja = this.shuffle(data);
            for(let i=0; i<this.pitanja.length; i++){
                if(this.pitanja[i].odabiri !== undefined){
                    this.pitanja[i].odabiri = this.shuffle(this.pitanja[i].odabiri);
                }
            }
        });
    }

    getArrayLength(array : any[]) : number{
        return array.length;
    }

    shuffle(array: any[]): any[] {
        let currentIndex = array.length,  randomIndex;
    
        while (currentIndex != 0) {
      
          randomIndex = Math.floor(Math.random() * currentIndex);
          currentIndex--;
      
          [array[currentIndex], array[randomIndex]] = [
            array[randomIndex], array[currentIndex]];
        }
      
        return array;
    };

    zavrsiIspit(){
        this.confirmationService.confirm({
            message: this.translateService.instant('message.endExam'),
            header: this.translateService.instant('buttons.confirm'),
            icon: 'pi pi-exclamation-triangle',
            acceptLabel: this.translateService.instant('buttons.yes'),
            rejectLabel: this.translateService.instant('buttons.no'),
            accept: () => {
                this.zavrsi();
            }
        });
    }

    zavrsi(){
        this.canLeaveExam = true;
        this.ispit.statusIspita = "";
        this.ispit.pitanja = this.pitanja;
        this.ispit.maksimalniBrojBodova = this.ukupanBrojBodova(this.pitanja);
        this.ispit.ostvareniBrojBodova = this.brojOstvarenihBodova();
        this.setStatusIspita();
        console.log(this.ispit);
        this.http.postHttp("insertIspit",this.ispit).subscribe(() => {
            this.router.navigate(["/myProfile"]);
            
        });
    }

    ukupanBrojBodova(pitanja: Pitanje[]) : number{
        var ukupanBrojBodova : number = 0;

        for(let i = 0; i < pitanja.length; i++){
            ukupanBrojBodova += pitanja[i].brojBodova;
        }

        return ukupanBrojBodova;
    }

    brojOstvarenihBodova() : number{
        var brojOstvarenihBodova = 0;

        for(let i = 0; i < this.pitanja.length; i++){
            
            if(this.pitanja[i].odabiri.length === 1){
                if(this.pitanja[i].odabiri[0].tekst === this.pitanja[i].odgovor){
                    brojOstvarenihBodova +=  this.pitanja[i].brojBodova;
                }
            } else {
                var tocnost : boolean = true;
                for(let j = 0; j < this.pitanja[i].odabiri.length; j++){
                    if(this.pitanja[i].odabiri[j].tocanOdgovor === true && !this.pitanja[i].odgovor?.includes(this.pitanja[i].odabiri[j].sifra)){
                        tocnost = false;
                    } else if (this.pitanja[i].odabiri[j].tocanOdgovor === false && this.pitanja[i].odgovor?.includes(this.pitanja[i].odabiri[j].sifra)){
                        tocnost = false;
                    }
                }
                if(tocnost){ 
                    brojOstvarenihBodova +=  this.pitanja[i].brojBodova;
                } else if( !tocnost && this.pitanja[i].raskrizje == true ){
                    this.ispit.statusIspita = "PAD - Raskrižje netočno odgovoreno!"
                }
                if(this.pitanja[i].odgovor  !== null){
                    if(this.pitanja[i].odgovor.length >= 1) this.pitanja[i].odgovor = this.pitanja[i].odgovor.toString(); 
                }
                
            }
            
        }

        return brojOstvarenihBodova;
    }

    setStatusIspita(){
        if(this.ispit.statusIspita === '' || this.ispit.statusIspita === undefined){
            if(this.ispit.ostvareniBrojBodova < this.ispit.maksimalniBrojBodova * 0.9){
                this.ispit.statusIspita = "PAD - Premali broj bodova";
            } else {
                this.ispit.statusIspita = "PROLAZ";
            }
        }
        
    }
}