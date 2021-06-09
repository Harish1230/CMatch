import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { User } from '../user';
import { Observable } from 'rxjs';
import { AuthenticationService } from './authentication.service';

@Injectable({
  providedIn: 'root'
})
export class UpdateUserService {

  constructor(private httpClient: HttpClient,private autherisation:AuthenticationService) { }
  userDetailsById(userId: string):Observable<User> {
    console.log("user details by id in service")
    return this.httpClient.get<User>('http://localhost:8088//user/secure/'+ userId, {
      headers : new HttpHeaders().set('content-type', 'application/json')
    });
  }
  
  updateUser(user:User):Observable<User>{
    // const tempToken = "Bearer " + this.autherisation.getBearerToken()
    return this.httpClient.put<User>('http://localhost:8088//user/secure/'+ user.userId, user,{
      headers : new HttpHeaders().set('content-type', 'application/json;charset=utf-8')
    });
  }
}
