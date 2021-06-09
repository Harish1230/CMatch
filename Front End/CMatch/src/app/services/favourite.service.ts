import { Injectable } from '@angular/core';
import { HttpClient,HttpHeaders } from '@angular/common/http';
import { Favorites } from 'src/favourite';
import { Observable } from 'rxjs';
import { MatchService } from './match.service';
import { AuthenticationService } from './authentication.service';

@Injectable({
  providedIn: 'root'
})
export class FavouriteService {

  favorites: Favorites;
 // headers: HttpHeaders;
  userId: string;
  matches: Array<any>;
  allMatches: Array<any>;
  recommendations: Array<any>;

  constructor(private httpclient: HttpClient, private service: MatchService,
    private autherisation:AuthenticationService ) { 
      let tempToken = "Bearer " + this.autherisation.getBearerToken()
      }

  addFavouriteList(favorites: Favorites): Observable<String> {
    console.log("match");
    const headers = new HttpHeaders().set('Content-Type', 'application/json');
    return this.httpclient.post<String>('http://localhost:8083/api/v1/favorites', favorites,
    {
  headers: headers
    });
  }
  getFavouriteList(userId): Observable<Array<Favorites>> {
    return this.httpclient.get<Array<Favorites>>("http://localhost:8083/api/v1/favorites/" + userId,);
  }

  deleteMatchFromFavouriteList(userId, uniqueId) {
    console.log("delete button");
    return this.httpclient.delete('http://localhost:8083/api/v1/favorites/' + userId + '/' + uniqueId);
  }

}
