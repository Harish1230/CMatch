import { Component, OnInit } from '@angular/core';
import { FavouriteService } from '../services/favourite.service';
import { RouterService } from '../services/router.service';
import { MatSnackBar, MatDialog } from '@angular/material';
import { Favorites } from 'src/favourite';
import { AuthenticationService } from '../services/authentication.service';

@Component({
  selector: 'app-favourite',
  templateUrl: './favourite.component.html',
  styleUrls: ['./favourite.component.css']
})
export class FavouriteComponent implements OnInit {

  matches: Array<Favorites>;
  useFavouriteListApi: boolean = true;

  constructor(private service: FavouriteService, private router: RouterService,private authenticate : AuthenticationService,
     private snackbar: MatSnackBar, public dialog: MatDialog) {
    this.matches = [];
  }
 private userId = this.authenticate.getUserEmail();
  ngOnInit() {

    this.service.getFavouriteList(this.userId).subscribe(
      matches => {
      if (matches.length == 0) {
        this.snackbar.open('My favourites list is empty', 'So ADD', {
          duration: 3000
        });
      }
      this.matches = matches;
      console.log(matches);
    })
  }

  deleteFromFavouriteList(match) {
    this.service.deleteMatchFromFavouriteList(this.userId, match.uniqueId).subscribe((result) => {},
     
      err=>{
        if(err.status==200)
        {
          this.snackbar.open("Match deleted successfully", '', {
            duration: 1000
          });
          console.log("delete");
        }        
      }
    );
    const index = this.matches.indexOf(match);
    this.matches.splice(index, 1);
    this.ngOnInit();
  }
  displayedColumns = ['unique_id','team1','team2','Delete'];

}
