import { Component, OnInit, ViewChild } from "@angular/core";
import { TranslateService } from "@ngx-translate/core";
import { ConfirmationService } from "primeng/api";
import { HttpService } from "src/app/core/services/http.service";
import { InfoService } from "src/app/core/services/info.service";
import { Odabir } from "../../core/models/odabir";

@Component({
    selector: 'app-pitanje',
    templateUrl: './pitanje.component.html',
    styleUrls: ['./pitanje.component.scss']
  })
  export class PitanjeComponent implements OnInit {

    @ViewChild('dt') dt : any; 

    pitanja : any;
    pitanje : any;
    odabranoPitanje: any;
    pitanjeDialog : boolean = false;
    submitted : boolean = false;

    odabir1 : Odabir = new Odabir();
    odabir2 : Odabir = new Odabir();
    odabir3 : Odabir = new Odabir();
    odabir4 : Odabir = new Odabir();
    slika : any = undefined;

    odabiriPrijeEdita !: Odabir[];

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
           this.translateService.get(['buttons']).subscribe( () => {
            this.getPitanja();
          });
        });
        this.getPitanja();
    }
        
    getPitanja(){
        this.http.getHttp("pitanja").subscribe( (data:any) => {
            this.pitanja = data;
            this.infoService.getAutoskolaInfo();
        });
    }

    applyFilterGlobal(event : any, stringVal: any) {
        this.dt.filterGlobal((event.target as HTMLInputElement).value, stringVal);
    }

    hideDialog() {
      this.pitanjeDialog = false;
      this.submitted = false;
      this.clearOdabir();
  }

  editPitanje(pitanje: any) {
      this.pitanje = {...pitanje};
      this.odabiriPrijeEdita = [];
      for(var i = 0; i < this.pitanje.odabiri.length ; i++){
        this.odabiriPrijeEdita.push(this.pitanje.odabiri[i]);
      }

      if(this.pitanje.odabiri[0] != null){
        this.copyOdabir(this.odabir1, this.pitanje.odabiri[0]);
      }
      if(this.pitanje.odabiri[1] != null){
        this.copyOdabir(this.odabir2, this.pitanje.odabiri[1]);
      }
      if(this.pitanje.odabiri[2] != null){
        this.copyOdabir(this.odabir3, this.pitanje.odabiri[2]);
      }
      if(this.pitanje.odabiri[3] != null){
        this.copyOdabir(this.odabir4, this.pitanje.odabiri[3]);
      }

      this.slika = pitanje.slika;
      this.pitanjeDialog = true;
  }

  copyOdabir(odabir : Odabir, originalniOdabir : any){
    odabir.odabirId = originalniOdabir.odabirId;
    odabir.pitanjeId = originalniOdabir.piranjeId;
    odabir.tekst = originalniOdabir.tekst;
    odabir.tocanOdgovor = originalniOdabir.tocanOdgovor;
  }

  openNew() {
      this.pitanje = {};
      this.pitanje.raskrizje = false;
      this.slika = undefined;
      this.odabir1 = new Odabir();
      this.odabir1.sifra = 'a';
      this.odabir2 = new Odabir();
      this.odabir2.sifra = 'b';
      this.odabir3 = new Odabir();
      this.odabir3.sifra = 'c';
      this.odabir4 = new Odabir();
      this.odabir4.sifra = 'd';
      this.submitted = false;
      this.pitanjeDialog = true;
  }

  savePitanje() {
      this.submitted = true;

      this.saveOdabir();

      if(this.slika !== null && this.slika !== undefined  && this.slika !== ''){
        this.pitanje.slika = this.slika;
      }

      if (this.pitanje.tekstPitanja.trim()) {
        if (this.pitanje.pitanjeId) {
          console.log(this.pitanje);
          this.http.putHttp("updatePitanje",this.pitanje).subscribe( (data1:any) => {
            this.getPitanja();
            this.pitanje = {};
            this.hideDialog();
          });
        }
        else {
          this.http.postHttp("insertPitanje",this.pitanje).subscribe( (data1:any) => {
              this.getPitanja();
              this.pitanje = {};
              this.hideDialog();
            });
          }
      }
  }

  deletePitanje(pitanje: any) {
      this.confirmationService.confirm({
          message: this.translateService.instant('errorMessage.sureToDelete') + this.pitanje.tekstPitanja + '?',
          header: this.translateService.instant('buttons.confirm'),
          icon: 'pi pi-exclamation-triangle',
          acceptLabel: this.translateService.instant('buttons.yes'),
          rejectLabel: this.translateService.instant('buttons.no'),
          accept: () => {
              this.http.putHttp("deletePitanje", pitanje).subscribe( (data1:any) => {
                  this.getPitanja();
                  this.pitanje = {};
                  this.hideDialog();
              });
          }
      });
  }

  onUpload(event : any, fileUpload :any){
    let fileReader = new FileReader();
    for (let file of event.files) {
      fileReader.readAsDataURL(file);
      fileReader.onload = (e: any) => {
          this.slika = e.target.result;
      };
    }
    fileUpload.clear();
  }

  clearOdabir(){
    this.odabir1 = new Odabir();
    this.odabir2 = new Odabir();
    this.odabir3 = new Odabir();
    this.odabir4 = new Odabir();
  }

  saveOdabir(){
    this.odabir1.sifra = 'a';
    this.odabir2.sifra = 'b';
    this.odabir3.sifra = 'c';
    this.odabir4.sifra = 'd';
    this.odabir1.pitanjeId = this.pitanje.pitanjeId;
    this.odabir2.pitanjeId = this.pitanje.pitanjeId;
    this.odabir3.pitanjeId = this.pitanje.pitanjeId;
    this.odabir4.pitanjeId = this.pitanje.pitanjeId;
    
    if(this.odabir1.tekst === '' || this.odabir1.tekst === undefined){
      this.odabir1.tekst = "";
      this.odabir1.tocanOdgovor = false;
      this.odabir1.deleted = 1;
    } else {
      this.odabir1.deleted = 0;
    }
    if(this.odabir2.tekst === '' || this.odabir2.tekst === undefined){
      this.odabir2.tekst = "";
      this.odabir2.tocanOdgovor = false;
      this.odabir2.deleted = 1;
    } else {
      this.odabir2.deleted = 0;
    }
    if(this.odabir3.tekst === '' || this.odabir3.tekst === undefined){
      this.odabir3.tekst = "";
      this.odabir3.tocanOdgovor = false;
      this.odabir3.deleted = 1;
    } else {
      this.odabir3.deleted = 0;
    }
    if(this.odabir4.tekst === '' || this.odabir4.tekst === undefined){
      this.odabir4.tekst = "";
      this.odabir4.tocanOdgovor = false;
      this.odabir4.deleted = 1;
    } else {
      this.odabir4.deleted = 0;
    }

    this.pitanje.odabiri = [];
    this.pitanje.odabiri.push(this.odabir1);
    this.pitanje.odabiri.push(this.odabir2);
    this.pitanje.odabiri.push(this.odabir3);
    this.pitanje.odabiri.push(this.odabir4);
  }

}