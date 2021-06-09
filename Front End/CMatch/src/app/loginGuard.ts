import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, Router } from '@angular/router';
import { AuthenticationService } from './services/authentication.service';
import { MatSnackBar } from '@angular/material';



@Injectable()
export class LogInGuard implements CanActivate {

    constructor(private authService: AuthenticationService, private router: Router, private snackBar: MatSnackBar) { }

    canActivate(
        next: ActivatedRouteSnapshot,
        state: RouterStateSnapshot): boolean {
        const temp = !!this.authService.getBearerToken();
        if (!temp) {
            return true;
        }
        else {
            //    alert("Already logged in")
            this.snackBar.open("Already logged in", "Got it!", {
                duration: 2000
            });
            return false;
        }
    }
}