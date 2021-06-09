import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';


@Injectable({
  providedIn: 'root'
})
export class RegistrationService {

  constructor(private httpClient:HttpClient) { }

  register(data):Observable<String>{

   
    return this.httpClient.post<String>('http://lo  calhost:8088/user', data, {
      headers : new HttpHeaders().set('content-type', 'application/json')
    });
         
  }
}
