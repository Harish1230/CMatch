import { Injectable } from '@angular/core';
import { HttpInterceptor, HttpRequest, HttpHandler } from '@angular/common/http';
/**
@Injectable({
  providedIn: 'root'
}) */
@Injectable()
export class BasicAuthHtppInterceptorServiceService implements HttpInterceptor{

  constructor() { }
  intercept(req: HttpRequest<any>, next: HttpHandler) {
    console.log(req.url.substring(7,14))
if(req.url.substring(7,14)=='cricapi')
{
  return next.handle(req);
}

    if (sessionStorage.getItem('userName') && sessionStorage.getItem('authToken')) {
      req = req.clone({
        setHeaders: {
          Authorization: 'Bearer ' +sessionStorage.getItem('authToken')
        }
      })
    }
    return next.handle(req);
  }

  
}
