import { Component, OnInit } from '@angular/core';
import { AuthenticationService } from '../services/authentication.service';

@Component({
  selector: 'app-view-profile',
  templateUrl: './view-profile.component.html',
  styleUrls: ['./view-profile.component.css']
})
export class ViewProfileComponent implements OnInit {
  imageName: string;
  profilePic: string;

  constructor(private authenticateService:AuthenticationService) { }
userName:string;
userEmail:string;
mobile:string;
result = false;
  ngOnInit() {
    this.userName = this.authenticateService.getUserName();
    this.userEmail = this.authenticateService.getUserEmail();
    this.mobile = this.authenticateService.getMobileNumber();
    this.imageName=this.userEmail.split("@")[0];
  this.profilePic="../../assets/"+this.imageName+".jpg";
  if(this.imageName !=null){
    this.result = true;
  }


}
}
