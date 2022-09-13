import { Component, OnInit } from "@angular/core";
import { InfoService } from "../../services/info.service";

@Component({
    selector: 'app-footer',
    templateUrl: './footer.component.html',
    styleUrls: ['./footer.component.scss']
  })
  export class FooterComponent implements OnInit {

    constructor(public infoService:InfoService) { }

    ngOnInit(): void {
        
    }

  }