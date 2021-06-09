import { Component, OnInit } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {ProfileImageService} from '../services/profile-image.service';
import {AuthenticationService} from '../services/authentication.service';
import {MatSnackBar} from '@angular/material';

@Component({
  selector: 'app-profile-image',
  templateUrl: './profile-image.component.html',
  styleUrls: ['./profile-image.component.css']
})
export class ProfileImageComponent implements OnInit {
  userEmail:string;
  fileData: File = null;
  image:File;
  selectedFile: File;
  name:string;
  imageName:string;
  profilePic:string;

  onFileChanged(event) {
    this.selectedFile = event.target.files[0];
    this.name=event.target.files[0].name;
     
  }

  constructor(private http: HttpClient,private profileimageService: ProfileImageService,private authService: AuthenticationService,
    public snackBar: MatSnackBar) { }

  ngOnInit() {
    this.userEmail = this.authService.getUserEmail();
  this.imageName=this.userEmail.split("@")[0];
  this.profilePic="../../assets/"+this.imageName+".jpg";
  }

  fileProgress(fileInput: any) {
    this.fileData = <File>fileInput.target.files[0];
  }
 
  onSubmit() {
    var formData = new FormData();
    //this.fileData = this.image;
    var temp=this.imageName+'.jpg';
    //formData.append('file', this.selectedFile,this.selectedFile.name);
    formData.append('file', this.selectedFile,temp);
    //console.log(formData.get('file')); 
    //console.log(formData.get('file').toString); 
    formData.get('file').toString;
    this.profileimageService.updateImage(formData)
      .subscribe(data=>{
        
      },
        
        error =>{
           if(error.status == 200){
             console.log(error.status);
             this.snackBar.open("image successfully uploaded", " ", {
               duration: 5000
             });
           }
      });
  }

}
