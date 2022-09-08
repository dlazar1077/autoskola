import { Component, OnInit } from "@angular/core";
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { Router } from "@angular/router";
import { MessageService } from "primeng/api";
import { AuthService } from "src/app/core/services/auth.service";
import { SignupRequest } from "../../models/singup-request";

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.scss'],
  providers : [MessageService]
})
export class SignupComponent implements OnInit {

  signupForm: FormGroup = new FormGroup({});
  signupRequest !: SignupRequest;

  constructor(private formBuilder: FormBuilder, private authService: AuthService,  private router: Router, private messageService: MessageService) { }

  ngOnInit(): void {
    this.setForm();
  }

  setForm(){
    const signupRequest = new SignupRequest();

    this.signupForm = this.formBuilder.group({
      ime: [signupRequest.ime, Validators.required],
      prezime: [signupRequest.prezime, Validators.required],
      email: [signupRequest.email, [Validators.email, Validators.required] ],
      korIme: [signupRequest.korIme, Validators.required],
      lozinka: [signupRequest.lozinka, Validators.required]
    });
  }

  saveFormData(){
    if (!this.signupForm.valid) return;

    const signupRequest = new SignupRequest();
    signupRequest.ime = this.signupForm.value.ime;
    signupRequest.prezime = this.signupForm.value.prezime;
    signupRequest.email = this.signupForm.value.email;
    signupRequest.korIme = this.signupForm.value.korIme;
    signupRequest.lozinka = this.signupForm.value.lozinka;
    signupRequest.ulogaId = 3;

    this.authService.signup(signupRequest).subscribe( (data : any ) => {
      if(!data?.message.toString().toLowerCase().includes("error")){
        this.showSuccess(data.message);
        this.router.navigate(["/home"]);
      } else{
        this.showError(data.message);
        //this.setForm();
      }
    });
  }

  signup(){
    this.saveFormData();
    
  }

  showSuccess(message : any) {
    this.messageService.add({severity:'success', summary: 'Success', detail: message});
  }

  showError(message : any) {
    this.messageService.add({severity:'error', summary: 'Error', detail: message});
  }

}