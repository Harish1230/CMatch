import { Component, OnInit } from '@angular/core';
import {UpdateUserService} from '../services/update-user.service';
import {AuthenticationService} from '../services/authentication.service';
import { FormControl, Validators } from '@angular/forms';
import { User } from '../user';
import { Router } from '@angular/router';
import { MatSnackBar } from '@angular/material';

@Component({
  selector: 'app-update-user',
  templateUrl: './update-user.component.html',
  styleUrls: ['./update-user.component.css']
})
export class UpdateUserComponent implements OnInit {

  user:User[];
  constructor( private profileService: UpdateUserService,private router: Router
    ,private logInService:AuthenticationService,public snackBar: MatSnackBar) { }
   
    userName = new FormControl('', [Validators.required]);
    userPassword = new FormControl('', [Validators.required, Validators.minLength(6)]);
    confirmPassword = new FormControl('', [Validators.required]);
    mobilenumber = new FormControl('', [Validators.required]);
    
  ngOnInit() {
  }
  

      updateUserDetails(){
        if(this.userPassword == this.confirmPassword){
        const user: any = { userId: this.logInService.getUserEmail(),
          userName: this.userName, userPassword: this.userPassword, mobilenumber: this.mobilenumber};
        console.log(this.user);
        this.profileService.updateUser(user)
            .subscribe(
              data => {
                console.log(data);
                console.log('updated');
                this.logInService.setUserName(this.userName);
                this.router.navigate(['/', 'dashboard']);
                alert('User Successfully Updated');
                },
                error =>{
                  if(error.status == 200){
                    // this.routeService.routeToLogin();
                  }else{
                    console.log('not updated');
                  }
                }
            );
              }
              else{
                this.snackBar.open("Password Mismatch", "Got it!", {
                  duration: 2000
                });
              }
    }
}
