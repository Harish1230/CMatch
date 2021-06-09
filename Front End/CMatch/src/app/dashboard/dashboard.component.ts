import { Component, OnInit } from '@angular/core';
import {MatchService} from '../services/match.service';
import {SearchService} from '../services/search.service';
import { AuthenticationService } from '../services/authentication.service';
import { Favorites } from 'src/favourite';
import { MatSnackBar, MatDialog } from '@angular/material';
import { FavouriteService } from '../services/favourite.service';
@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {
  matches:Array<any>;
  result=false;
  constructor(private service:MatchService,private searchService:SearchService, private favouriteService: FavouriteService,
    private authenticate: AuthenticationService, private snackBar: MatSnackBar, public dialog: MatDialog) { }
  searchText:String;
  favorites: Favorites = new Favorites();
  
  resultMatches:Array<any>;
  userEmail:string
  userName:string
  ngOnInit() {
    // this.header.ngOnInit()
    this.service.upcomingMatch().subscribe(
      (matchData)=>{
        this.matches =matchData['matches'];
      }
    );
    }
    displayedColumns = ['unique_id','dateTimeGMT','team1',
    'team2','toss_winner_team','recommand'];
  search(){
    this.resultMatches =this.searchService.search(this.searchText,this.matches)
    if(this.resultMatches.length>0){
      this.result = true;
    }
  }
  addToFavourite(match){
    const temp = this.authenticate.getUserEmail()
    if (temp == null) {
      this.snackBar.open('Please Login To Add Favourite', 'Got It', {
        duration: 3000
      });
    }
    else {
      this.favorites.userId = temp;
      this.favorites.uniqueId = match.unique_id;
      this.favorites.team1 = match["team-1"];
      this.favorites.team2 = match["team-2"];
      console.log("favourite added");
      this.favouriteService.addFavouriteList(this.favorites).subscribe(
        response => {
          this.snackBar.open('Match added To List', '', {
            duration: 3000
          });
          console.log(response)
        },
        error => {
          if (error.status == 201)
            alert("Added favourite");
          else
            alert("favourite already exists");
        });

    }
    // this.upcoming.addToFavouriteList(match);
  }
  
}
