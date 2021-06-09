import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, Router } from '@angular/router';
import { AuthenticationService } from './services/authentication.service';



@Injectable()
export class Guard implements CanActivate {

  constructor(private authService: AuthenticationService, private router: Router) {}

  canActivate(
    next: ActivatedRouteSnapshot,
    state: RouterStateSnapshot):boolean {
        const temp = !!this.authService.getBearerToken();
        if(temp){
            return true;
        }
        else{
            this.router.navigate(['/', 'login']);
            return false;
        }
    }
}