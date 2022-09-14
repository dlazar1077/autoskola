import { Component, EventEmitter, Input, OnInit, Output, ViewChild } from "@angular/core";
import { TranslateService } from "@ngx-translate/core";
import { ConfirmationService } from "primeng/api";
import { Instruktor } from "src/app/core/models/instruktor";
import { Polaznik } from "src/app/core/models/polaznik";
import { Sat } from "src/app/core/models/sat";
import { Vozilo } from "src/app/core/models/vozilo";
import { AuthService } from "src/app/core/services/auth.service";
import { HttpService } from "src/app/core/services/http.service";
import { InfoService } from "src/app/core/services/info.service";
import { SharedService } from "src/app/core/services/shared.service";

@Component({
    selector: 'app-dnevnik-voznje',
    templateUrl: './dnevnik-voznje.component.html',
    styleUrls: ['./dnevnik-voznje.component.scss']
  })
  export class DnevnikVoznjeComponent implements OnInit {

    @ViewChild('dt') dt : any;
    @Output() getData = new EventEmitter<any>();


    @Input() polaznik !: Polaznik;
    @Input() instruktor !: Instruktor;
    @Input() vozila !: Vozilo[];

    sat !: Sat;
    satDialog : boolean = false;
    submitted : boolean = false;
    date1!: Date;
    

    constructor(
        private http: HttpService, 
        private confirmationService: ConfirmationService, 
        private translateService : TranslateService,
        public authService : AuthService,
        public infoService : InfoService,
        private sharedService : SharedService
        ) { }
    
    ngOnInit(): void {
    }

    getPolazniciData(){
        if(this.authService.isAdminRoleRight()){
            var user = this.authService.getUser();
        } else if(this.authService.isModerator()){
            var user = this.authService.getUser();
            this.http.getHttp("instruktor", {id: user.id}).subscribe( (data : any) => {
                this.instruktor = data;
                this.http.getHttp("polazniciInstruktora", {id: this.instruktor.instruktorId}).subscribe( (data1 : any) => {
                    this.polaznik = data1.find((x:any) => x.polaznikId === this.polaznik.polaznikId);
                });
            });
        } else{
        }
    }

    applyFilterGlobal(event : any, stringVal: any) {
        this.dt.filterGlobal((event.target as HTMLInputElement).value, stringVal);
    }

    openNew(){
        this.sat = new Sat();
        this.satDialog = true;
        this.submitted = false;
        this.sat.datum = new Date().toString();
        this.date1 = new Date();
    }

    editSat(sat : Sat){

        this.sat = {...sat};
        this.submitted = false;
        this.satDialog = true;
        if(this.sat.datum !== null) this.date1 = new Date(this.sat.datum);
    }

    hideDialog(){
        this.satDialog = false;
    }

    saveSat(){
        this.submitted = true;
        this.sat.polaznikId = this.polaznik.polaznikId;
        this.sat.datum = this.date1.toJSON();
        if (this.checkForm()) {
            if (this.sat.satVoznjeId) {
                this.http.putHttp("updateSatVoznje",this.sat).subscribe( (data1:any) => {
                    this.getPolazniciData();
                    this.getData.emit();
                    this.sat = new Sat();
                    this.hideDialog();
                });
            }
            else {
                this.http.postHttp("insertSatVoznje",this.sat).subscribe( (data1:any) => {
                    this.getPolazniciData();
                    this.getData.emit();
                    this.sat = new Sat();
                    this.hideDialog();
                });
            }
        }
    }

    deleteSat(sat: Sat) {
        this.confirmationService.confirm({
            message: this.translateService.instant('errorMessage.sureToDelete') + sat.brojSata + " - " + sat.opis + '?',
            header: this.translateService.instant('buttons.confirm'),
            icon: 'pi pi-exclamation-triangle',
            acceptLabel: this.translateService.instant('buttons.yes'),
            rejectLabel: this.translateService.instant('buttons.no'),
            accept: () => {
                this.http.putHttp("deleteSatVoznje", sat).subscribe( (data1:any) => {
                    this.getPolazniciData();
                    this.getData.emit();
                    this.sat = new Sat();
                    this.hideDialog();
                });
            }
        });
    }

    checkForm():boolean{
        if(this.sat.brojSata !== null && this.sat.brojSata !== undefined){
            if(this.sat.opis !== '' && this.sat.opis !== undefined){
                if(this.sat.datum !== '' && this.sat.datum !== undefined){
                    return true;
                }
            }
        }
        return false;
    }
  }