import { Component, OnInit } from "@angular/core";
import { Router } from "@angular/router";
import { MessageService } from "primeng/api";
import { first } from "rxjs/operators";
import { AuthService } from "src/app/core/services/auth.service";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss'],
  providers : [MessageService]
})
export class LoginComponent implements OnInit {

  public username !: string ;
  public password !: string ;

  constructor(private authService: AuthService, private router: Router, private messageService: MessageService) { }

  ngOnInit(): void {}

  login(){
    this.authService.login(this.username, this.password)
    .pipe(first())
    .subscribe((data: any) => {
      //this.message = data;
      this.showSuccess("Uspješna prijava!");
      this.router.navigate(["/home"]);
    }, (error : any) => {
      this.showError("Neuspješna prijava!");
    }
    )
  }

  signup(){
    this.router.navigate(["/signup"]);
  }

  showSuccess(message : any) {
    this.messageService.add({severity:'success', summary: 'Success', detail: message});
  }

  showError(message : any) {
    this.messageService.add({severity:'error', summary: 'Error', detail: message});
  }
}