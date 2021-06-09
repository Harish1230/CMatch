import { Component, OnInit } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';
import { User } from '../user';
import { AuthenticationService } from '../services/authentication.service';
import { Router } from '@angular/router';
import {MatSnackBar} from '@angular/material';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  user: User;
  userId: string;
  userName:string;
  userPassword: string;
  public bearerToken: any;
  public submitMessage: string;
  passData: string;

  emailFormControl = new FormControl('', [
    Validators.required,
    Validators.email,
  ]);

  userEmail:string

  ngOnInit() {
    this.userEmail = this.authService.getUserEmail();
    this.userName = this.authService.getUserName();
  }
  constructor(private authService: AuthenticationService,private router: Router,public snackBar: MatSnackBar) {
    this.user = new User();
  }
  login() {
    console.log(' calling login method ');
    this.user.userId = this.userId;
    this.user.userPassword = this.userPassword;
    this.authService.authenticateUser(this.user).subscribe(
      data => {
        this.snackBar.open("Successfully Logged In", "Got it!", {
          duration: 2000
        });
        this.authService.setBearerToken(data['token']);
        this.authService.setUserEmail(this.userId);
        this.authService.setUserName(data['user'].userName);
        this.authService.setMobileNumber(data['user'].mobilenumber);
        this.router.navigate(['/', 'dashboard']);
        this.ngOnInit();
        
      },
      err => {        
        if (err.status === 401) {          
          this.snackBar.open("Password Mismatch", "Got it!", {
            duration: 2000
          });
        } else {
          this.snackBar.open("Please Start The Server","Got it!", {
            duration: 2000
          });
        }
      });
  }
  getInfo() {
    return this.passData;
  }
}
