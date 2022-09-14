import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { TranslateService } from '@ngx-translate/core';
import { environment } from 'src/environments/environment';
import { InfoService } from './core/services/info.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit{
  title = 'autoskola-central-front';

  constructor(private httpClient : HttpClient, private translate: TranslateService, private router: Router, private infoService : InfoService){

  }

  ngOnInit(): void {
      this.translate.setDefaultLang("hr");
      this.translate.use("hr");
      this.infoService.getAutoskolaInfo();
  }

  onClick(){
  }
}
