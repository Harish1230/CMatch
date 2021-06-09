import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
@Injectable({
  providedIn: 'root'
})
export class MatchService {
  key: String
  url: String
  constructor(private httpClient: HttpClient) {
    this.key = "zFtYuUUk5IRcKvFDt9gW6vkqozl1"
    this.url = "http://cricapi.com/api/"
  }
  public getOldMatch(): Observable<Array<any>> {
    return this.httpClient.get<Array<any>>(this.url + "cricket?apikey=" + this.key)
  }
  public upcomingMatch(): Observable<any> {
    return this.httpClient.get(this.url + "matches?apikey=" + this.key)
  }
}
