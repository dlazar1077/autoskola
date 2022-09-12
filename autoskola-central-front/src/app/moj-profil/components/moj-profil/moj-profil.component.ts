import { ThisReceiver } from "@angular/compiler";
import { Component, OnInit } from "@angular/core";
import { Router } from "@angular/router";
import { Instruktor } from "src/app/core/models/instruktor";
import { Korisnik } from "src/app/core/models/korisnik";
import { Polaznik } from "src/app/core/models/polaznik";
import { AuthService } from "src/app/core/services/auth.service";
import { HttpService } from "src/app/core/services/http.service";

@Component({
    selector: 'app-moj-profil',
    templateUrl: './moj-profil.component.html',
    styleUrls: ['./moj-profil.component.scss']
  })
  export class MojProfilComponent implements OnInit {

    codebooks : any;
    vozila:any;

    korisnik : any;
    instruktor !: Instruktor;
    polaznik !: Polaznik;

    submitted : any;
    userDialog : any;
    editKorisnik : Korisnik  = new Korisnik();
    editInstruktor : Instruktor = new Instruktor();
    editPolaznik : Polaznik = new Polaznik();
    prvaLozinka:any;

    upisDialog : any;

    instruktorDialog : any;
    slobodniInstruktori !: Instruktor[];
    odabranInstruktor : any;

    constructor(
        public authService : AuthService,
        private http : HttpService,
        private router: Router
        ){}

    ngOnInit(): void {
        this.http.getCodebooks(["kategorije, statusPolaznika"]).subscribe( (data:any) => {
            this.getMyProfileData(); 
            this.codebooks = data;
        });

        this.http.getHttp("vozila").subscribe( (vehicles:any) => {
            this.vozila = vehicles.tableData;
        });
       
    }

    getMyProfileData(){
        if(this.authService.isAdminRoleRight()){
            var user = this.authService.getUser();
            this.http.getHttp("korisnik", {id: user.id}).subscribe( (data : any) => {
                this.korisnik = data;
            });
        } else if(this.authService.isModerator()){
            var user = this.authService.getUser();
            this.http.getHttp("instruktor", {id: user.id}).subscribe( (data : any) => {
                this.instruktor = data;
                this.korisnik = this.instruktor.korisnik;
            });
        } else{
            var user = this.authService.getUser();
            this.http.getHttp("polaznik", {id: user.id}).subscribe( (data : any) => {
                this.polaznik = data;
                if(this.polaznik !== null){
                    this.korisnik = this.polaznik.korisnik;
                } else{
                    this.http.getHttp("korisnik", {id: user.id}).subscribe( (data : any) => {
                        this.polaznik = new Polaznik();
                        this.korisnik = data;
                        this.polaznik.korisnik = this.korisnik;
                        this.polaznik.korisnikId = this.korisnik.korisnikId;
                    });
                }
            });
        }
    }

    openEditDialog(){
        if(this.authService.isAdminRoleRight()){
            this.editKorisnik = {...this.korisnik}
            this.prvaLozinka = this.editKorisnik.lozinka;
        } else if(this.authService.isModeratorRoleRight()){
            this.editInstruktor = {...this.instruktor};
            this.editKorisnik = this.editInstruktor.korisnik;
            this.prvaLozinka = this.editKorisnik.lozinka;
        } else{
            this.editPolaznik = {...this.polaznik};
            this.editKorisnik = this.editPolaznik.korisnik;
            this.prvaLozinka = this.editKorisnik.lozinka;
        }
        this.userDialog = true;
    }

    hideDialog(){
        this.userDialog = false;
        this.submitted = false;
    }

    saveBasicInfo(){
        if(this.authService.isAdminRoleRight()){
            if(this.checkKorisnikForm()){
                if(this.editKorisnik.lozinka === this.prvaLozinka){
                    this.editKorisnik.lozinka = "";
                }
                this.http.putHttp("updateKorisnik", this.editKorisnik).subscribe(() => {
                    this.editKorisnik = new Korisnik();
                    this.getMyProfileData();
                    this.hideDialog();
                });
            }
        } else if(this.authService.isModerator()){
            if(this.checkKorisnikForm() && this.checkInstruktorForm()){
                if(this.editKorisnik.lozinka === this.prvaLozinka){
                    this.editKorisnik.lozinka = "";
                }
                this.editInstruktor.korisnik = this.editKorisnik;
                this.http.putHttp("updateInstruktor", this.editInstruktor).subscribe(() => {
                    this.editKorisnik = new Korisnik();
                    this.editInstruktor = new Instruktor();
                    this.getMyProfileData();
                    this.hideDialog();
                });
            }
        } else{
            if(this.checkKorisnikForm()){
                if(this.editKorisnik.lozinka === this.prvaLozinka){
                    this.editKorisnik.lozinka = "";
                }
                if(this.editPolaznik.polaznikId === null || this.editPolaznik.polaznikId === undefined){
                    this.http.putHttp("updateKorisnik", this.editKorisnik).subscribe(() => {
                        this.editKorisnik = new Korisnik();
                        this.getMyProfileData();
                        this.hideDialog();
                    });
                } else {
                    this.editPolaznik.korisnik= this.editKorisnik;
                    
                    this.http.putHttp("updatePolaznik", this.editPolaznik).subscribe(() => {
                       this.editKorisnik = new Korisnik();
                        this.getMyProfileData();
                        this.hideDialog();
                    });
                }
            }
        }
    }

    checkKorisnikForm() : boolean {
        if(this.editKorisnik.ime === undefined || this.editKorisnik.ime === "") return false;
        if(this.editKorisnik.prezime === undefined || this.editKorisnik.prezime === "") return false;
        if(this.editKorisnik.korisnickoIme === undefined || this.editKorisnik.korisnickoIme === "") return false;
        if(this.editKorisnik.oib === undefined || this.editKorisnik.oib === "") return false;
        if(this.editKorisnik.email === undefined || this.editKorisnik.email === "") return false;
        
        return true;
    }

    checkInstruktorForm() : boolean {
        if(this.instruktor.brojSlobodnihMjesta === undefined || this.instruktor.brojSlobodnihMjesta === null) return false;
        
        return true;
    }

    categoryConverter(categoryId : any) : String{
        return this.codebooks.kategorije.find((x:any) => x.id.toString() === categoryId.toString()).name;
    }

    statusConverter(statusId : any) : String{
        if(statusId !== null){
            if(statusId !== undefined){
                return this.codebooks.statusPolaznika.find((x:any) => x.id.toString() === statusId.toString()).name;
            }
        } 
        return "";
    }

    statusIdConverter(statusId : any) : number{
        return this.codebooks?.statusPolaznika.find((x:any) => x.name.toString().toLowerCase() === statusId.toString().toLowerCase( )).id;
    }

    upisPolaznika(){
        this.upisDialog = true;
    }

    upisi(){
        this.editPolaznik.korisnikId = this.polaznik.korisnik.korisnikId;
        this.editPolaznik.statusPolaznikaId = this.statusIdConverter("UPISAN");
        this.http.postHttp("insertPolaznik", this.editPolaznik).subscribe(() => {
            this.editKorisnik = new Korisnik();
             this.getMyProfileData();
             this.hideUpisDialog();
         });
    }

    hideUpisDialog(){
        this.upisDialog = false;
    }

    generirajTest(){
        this.router.navigate(['/exam']);
    }

    odaberiInstruktora(){
        this.http.getHttp("slobodniInstruktori", {id: this.polaznik.odabranaKategorijaId}).subscribe( (data : any) => {
            this.editPolaznik = {...this.polaznik};
            this.editKorisnik = this.editPolaznik.korisnik;
            this.editPolaznik.instruktorId = undefined;
            this.slobodniInstruktori = data;
            this.instruktorDialog = true;
        });
    }

    hideInstructorDialog(){
        this.instruktorDialog = false;
    }

    saveInstruktor(){
        this.editPolaznik.korisnik.lozinka = "";
        var instruktor : Instruktor | undefined= this.slobodniInstruktori.find((x:any) => x.instruktorId.toString() === this.editPolaznik.instruktorId?.toString());
        if(instruktor !== undefined) {
            instruktor.korisnik.lozinka = "";
            instruktor.brojSlobodnihMjesta = instruktor.brojSlobodnihMjesta - 1 ;
        }
        this.http.putHttp("updatePolaznik", this.editPolaznik).subscribe(() => {
            if(instruktor !== undefined) {
                this.http.putHttp("updateInstruktor", instruktor).subscribe();
            }
            this.editKorisnik = new Korisnik();
             this.getMyProfileData();
             this.hideInstructorDialog();
         });
    }
  }