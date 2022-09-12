import { Component, OnInit } from "@angular/core";
import { InfoService } from "../../services/info.service";

@Component({
  selector: 'app-about',
  templateUrl: './about.component.html',
  styleUrls: ['./about.component.scss']
})
export class AboutComponent implements OnInit {

  constructor(public infoService:InfoService) { }

  ngOnInit(): void {
  }
}