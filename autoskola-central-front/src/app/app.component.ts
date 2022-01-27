import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { TranslateService } from '@ngx-translate/core';
import { environment } from 'src/environments/environment';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit{
  title = 'autoskola-central-front';

  constructor(private httpClient : HttpClient, private translate: TranslateService ){

  }

  ngOnInit(): void {
      this.translate.setDefaultLang("hr");
      this.translate.use("hr");
  }

  onClick(){
    this.httpClient.get<any>(`${environment.API_URL}/test/getData`).subscribe( (data) => {
      console.log(data);
      console.log(1);
    });
  }
}
