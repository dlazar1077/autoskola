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

  public forgetDialog !: boolean;
  public submitted !: boolean;
  public forgetUsername !: string;

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

  hideDialog(){
    this.forgetDialog = false;
    this.forgetUsername = "";
  }

  forgetPasswordPassword(){
    this.submitted = true;

    if(this.forgetUsername !== ""){
      this.authService.forgetPassword(this.forgetUsername)
      .pipe(first())
      .subscribe((data: any) => {
        if(data.message.includes("Error")){
          this.showError("Korisničko ime ne posotji!");
          this.hideDialog();
        } else{
          this.showSuccess("Provijerite e-mail!");
          this.hideDialog();
        }
      }, (error : any) => {
        this.showError("Korisničko ime ne posotji!");
        this.hideDialog();
      }
      )
    }
  }

  openDialog(){
    this.forgetDialog = true;
    this.submitted = false;
    this.forgetUsername = "";
  }
}