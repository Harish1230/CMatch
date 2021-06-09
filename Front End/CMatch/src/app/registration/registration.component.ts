import { Component, OnInit } from '@angular/core';
import {FormGroup,FormControl,Validators} from '@angular/forms';
import{User} from '../user';
import { RegistrationService } from '../registration.service';
import { Router } from '@angular/router';
import {MatSnackBar} from '@angular/material';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {
  registerForm: FormGroup;
  
  
 //name=new FormControl();
 
   constructor(private registrationService: RegistrationService,private router: Router,public snackBar: MatSnackBar)
     { }

    userId = new FormControl('', [Validators.required,Validators.email]);
    userName = new FormControl('', [Validators.required]);
    userPassword = new FormControl('', [Validators.required, Validators.minLength(6)]);
    confirmPassword = new FormControl('', [Validators.required,Validators.minLength(6)]);
    mobilenumber = new FormControl('', [Validators.required,Validators.minLength(10)]);

  ngOnInit() {
    this.userId = null;
    this.userName=null;
    this.userPassword = null;
    this.confirmPassword = null;
    this.mobilenumber = null;                
    } 
    
  Submit()
  {
    //console.log('Sucessfully registered');
    const user: any = { userId: this.userId, userName: this.userName, userPassword: this.userPassword, confirmPassword: this.confirmPassword, mobilenumber: this.mobilenumber};
    if(this.userName != null && this.userPassword != null && this.confirmPassword != null && this.mobilenumber != null && this.userPassword == this.confirmPassword)
    {
      this.registrationService.register(user)
 ​
        .subscribe(
         data => {
           // this.alertService.success('Registration successful', true);
           // this.routeService.routeToLogin();
       },
       error => {
         if (error.status == 201) 
          {
           console.log('success');
           //this.routeService.routeToLogin();
           //alert('User Successfully registered');
           this.snackBar.open("User Successfully registered", "Got it!", {
            duration: 2000
          });
           this.router.navigate(['/', 'login']);
              }
         else if (error.status === 409) {
           //alert('user already exists');
           this.snackBar.open("user already exists", " ", {
            duration: 2000
          });
         }         
    else {
           //alert('could not register'); ​
           this.snackBar.open("could not register", " ", {
            duration: 2000
          });
           console.log('Could not register');
         }
       }); 
    }else if(this.userPassword != this.confirmPassword){
      this.snackBar.open("passwords do not match", " ", {
        duration: 2000
      });
    }else{
      this.snackBar.open("All the fields required", " ", {
        duration: 2000
      });
    }

    
}
}
