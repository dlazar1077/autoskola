import { HttpErrorResponse, HttpEvent, HttpHandler, HttpInterceptor, HttpRequest, HttpResponse } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { tap } from "rxjs/operators";
import { SpinnerService } from "../services/spinner.service";

@Injectable()
export class HttpErrorInterceptor implements HttpInterceptor{

    constructor(private spinnerService : SpinnerService){}

    intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        this.spinnerService.requestStarted();
        return this.handler(next, req);
    }

    handler(next : HttpHandler, request : any){
        return next.handle(request)
            .pipe(
                tap(
                    (event : any) => {
                        if(event instanceof HttpResponse){
                            this.spinnerService.requestEnded();
                        }
                    },
                    (error : HttpErrorResponse) => {
                        this.spinnerService.requestEnded();
                        throw error;
                    }
                ),
            );
    }

}