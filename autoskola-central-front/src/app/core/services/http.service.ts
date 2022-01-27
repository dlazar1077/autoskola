import { environment } from './../../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class HttpService {

  constructor(private http: HttpClient) { }

  getHttp(apiUrl: string, data?: any) {
    return this.http.get<any>(`${environment.API_URL}/${apiUrl}`, { params: data });
  }

  postHttp(apiUrl: string, data?: any, options?: any) {
    return this.http.post<any>(`${environment.API_URL}/${apiUrl}`, data, options);
  }

  putHttp(apiUrl: string, data?: any, options?: any) {
    return this.http.put<any>(`${environment.API_URL}/${apiUrl}`, data, options);
  }

  deleteHttp(apiUrl: string, data?: any) {
    return this.http.delete<any>(`${environment.API_URL}/${apiUrl}`, { body: data });
  }

}
