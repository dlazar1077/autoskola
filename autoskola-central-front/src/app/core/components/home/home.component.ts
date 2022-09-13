import { Component, OnDestroy, OnInit } from "@angular/core";
import { TranslateService } from "@ngx-translate/core";
import { Instruktor } from "../../models/instruktor";
import { Vozilo } from "../../models/vozilo";
import { HttpService } from "../../services/http.service";
import { InfoService } from "../../services/info.service";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit, OnDestroy {

  instruktori !: Instruktor[];
  vozila!:Vozilo[];

  currentLanguage !: string;
  currentLangSubscription : any;

  codebooks: any;

  kategorije !: any[];

  kategorijeInstruktora !: any[];


  constructor(
    private http: HttpService, 
    private translateService : TranslateService,
    public infoService : InfoService) { }

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
        console.log(data)
    });

    
    this.http.getHttp("vozila").subscribe( (vehicles:any) => {
        this.vozila = vehicles.tableData;
        console.log(vehicles.tableData)
    });
  }

  ngOnDestroy(): void {
    this.currentLangSubscription.unsubscribe();
  }

  getInstruktori(){
    this.http.getHttp("instruktori").subscribe( (data:any) => {
        this.instruktori = data;
        console.log(data)
    });
  }

  categoryConverter(categoryId : any) : String{
    return this.codebooks.kategorije.find((x:any) => x.id.toString() === categoryId.toString()).name;
  }

}