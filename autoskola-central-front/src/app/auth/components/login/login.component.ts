import { Component, OnInit } from "@angular/core";
import { Router } from "@angular/router";
import { first } from "rxjs/operators";
import { AuthService } from "src/app/core/services/auth.service";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  public username !: string ;
  public password !: string ;

  constructor(private authService: AuthService, private router: Router) { }

  ngOnInit(): void {}

  login(){
    this.authService.login(this.username, this.password)
    .pipe(first())
    .subscribe((data: any) => {
      //this.message = data;
      this.router.navigate(["/home"]);
    })
  }

  signup(){
    this.router.navigate(["/signup"]);
  }
}