import { Component, OnInit } from '@angular/core';
import { AuthenticationService } from '../services/authentication.service';
import { Router } from '@angular/router';
// import { Subscription, interval } from 'rxjs';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
  // private updateSubscription: Subscription
  constructor(private authenticationService:AuthenticationService,private router: Router) { }
  userEmail:string
  userName:string
  ngOnInit() {
//     this.updateSubscription = interval(1000).subscribe(
//       (val) => { this.getToken()
//     }
// );
this.getName()
    this.userEmail = this.authenticationService.getUserEmail();
   // this.userName = this.authenticationService.getUserName();
  }
//   ngOnDestroy() {
//     this.updateSubscription.unsubscribe();
// }
  updateUser(){
    this.router.navigate(['/', 'update-user']);
   // this.userName = this.authenticationService.getUserName();
  }
  temp = sessionStorage.getItem('authToken');
  logout(){
    this.authenticationService.userLogout().subscribe(
      data=>{
        console.log(data);
      },
      error =>{
        if(error.status == 200){
          console.log(error.status);
        }
        if(error.status == 404){
          console.log("not logged out");
        }
      }
    );
    this.router.navigate(['/', 'dashboard']);
    this.ngOnInit()
  }
  getName(){
    if(this.userEmail==null){
      return false;
    }
    return true;
  }
}
