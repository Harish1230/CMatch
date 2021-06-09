import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
@Injectable(
)
export class AuthenticationService {
constructor(private httpClient: HttpClient) {
}
headers: HttpHeaders;
authenticateUser(data): Observable <string> {
     return this.httpClient.post<string>('http://localhost:8089/api/v1/auth/login', data, {
       headers : new HttpHeaders().set('content-type', 'application/json')
     });
   }
   userLogout():Observable<String>{
    // const tempToken = "Bearer " + this.getBearerToken()
    // this.headers = new HttpHeaders().set('Content-Type', 'application/json').set('Authorization',tempToken);
     sessionStorage.removeItem('emailId')
     sessionStorage.removeItem('userName')
     sessionStorage.removeItem('authToken')
    return this.httpClient.get<string>('http://localhost:8089/logout');
   }
   setUserName(userName){
     sessionStorage.setItem('userName',userName);
   }
   getUserName():string{
    // console.log('getting user name ' + sessionStorage.getItem('userName'));
 return sessionStorage.getItem('userName');
   }
setBearerToken(token) {
 sessionStorage.setItem('authToken', token);
}
getBearerToken(): string {
 console.log('getting bear token ' + sessionStorage.getItem('authToken'));
 return sessionStorage.getItem('authToken');
}
register(data){
return this.httpClient.post( 'http://localhost:8088/user', data, {
       headers : new HttpHeaders().set('content-type', 'application/json')
     });
}

setUserEmail(userId) {
  sessionStorage.setItem('emailId', userId);
}

getUserEmail(): string {

 return sessionStorage.getItem('emailId');
}


setMobileNumber(number){
  sessionStorage.setItem('number', number);
}
getMobileNumber(){
  return sessionStorage.getItem('number');
}

}
