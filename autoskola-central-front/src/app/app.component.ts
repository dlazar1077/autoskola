import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'autoskola-central-front';

  constructor(private httpClient : HttpClient ){

  }

  onClick(){
    this.httpClient.get<any>("http://localhost:30163/getData").subscribe( (data) => {
      console.log(data);
      console.log(1);
    });
  }
}
