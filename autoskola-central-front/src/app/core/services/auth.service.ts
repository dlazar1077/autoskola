import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Router } from "@angular/router";
import { map } from 'rxjs/operators';
import { SignupRequest } from "src/app/auth/models/singup-request";
import { environment } from "src/environments/environment";

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private expiration = 7200000;
  private currentUser : string  = 'currentUser';
  private expiresIn : string = '_expiresIn';
  private role : string = '_role';
  private user : any;

  constructor(private http: HttpClient, private router: Router) { }

  login(username: string, password: string) {
    return this.http.post<any>(`${environment.LOGIN_URL}/login`, { username, password }).pipe(map(user => {
      if (user && user.token) {
        localStorage.setItem(this.currentUser, JSON.stringify(user));
        localStorage.setItem(this.currentUser + this.expiresIn, JSON.stringify(new Date()));
        localStorage.setItem(this.currentUser + this.role, JSON.stringify(user?.role));
        this.user = user;
      }
      return user;
    }));
  }

  isLoggedIn() {
    const user = this.getCurrentUser();
    return user && user.token;
  }

  getUser(){
    const user = this.getCurrentUser();
    return user;
  }

  isAdminRoleRight(){
    const role = JSON.parse(localStorage.getItem(this.currentUser + this.role) || '{}');
    if(role === 'ROLE_ADMIN') return true;
    return false;
  }

  isModeratorRoleRight(){
    const role = JSON.parse(localStorage.getItem(this.currentUser + this.role) || '{}');
    if(role === 'ROLE_MODERATOR' || role === 'ROLE_ADMIN') return true;
    return false;
  }

  isModerator(){
    const role = JSON.parse(localStorage.getItem(this.currentUser + this.role) || '{}');
    if(role === 'ROLE_MODERATOR') return true;
    return false;
  }

  logout() {
    localStorage.removeItem(this.currentUser);
    localStorage.removeItem(this.currentUser + this.expiresIn);
    localStorage.removeItem(this.currentUser + this.role);
    this.router.navigate(["/login"]);
  }

  private getCurrentUser() {
    const user = JSON.parse(localStorage.getItem(this.currentUser)  || '{}');
    const expiresIn = JSON.parse(localStorage.getItem(this.currentUser + this.expiresIn)  || '{}');

    if (expiresIn && Math.abs(new Date().getTime() - new Date(expiresIn).getTime()) > this.expiration) {
      this.logout();
      return null;
    }

    return user;
  }

  signup(signupRequest : SignupRequest){
    return this.http.post<any>(`${environment.LOGIN_URL}/signup`, signupRequest);
  }

}