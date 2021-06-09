import { Injectable } from '@angular/core';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class RouterService {

  constructor(private router: Router) { }

  routeToDashboard() {
    console.log('entering into dashboard');
    this.router.navigate(['/matches/dashboard']);
  }
  routeToFavourites() {
    console.log('entering into favourites');
    this.router.navigate(['/matches/favorites']);
  }
  routeToLogin() {
    this.router.navigate(['/login']);
  }
  routeToUpcoming() {
    this.router.navigate(['/matches/upcomingmatches']);
  } 

}
