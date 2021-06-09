import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ProfileImageService {

  constructor(private httpClient:HttpClient) { } 

  updateImage(data):Observable<String>{
    return this.httpClient.post<String>('http://localhost:8085/upload',data);
  }
}
