import { Component, OnInit } from '@angular/core';
import { MatchService } from '../services/match.service'
import { FavouriteService } from '../services/favourite.service';
import { Favorites } from 'src/favourite';
import { MatSnackBar, MatDialog } from '@angular/material';
import { ActivatedRoute } from '@angular/router';
import { AuthenticationService } from '../services/authentication.service';

@Component({
  selector: 'app-upcomingmatches',
  templateUrl: './upcomingmatches.component.html',
  styleUrls: ['./upcomingmatches.component.css']
})

export class UpcomingmatchesComponent implements OnInit {

  matches: Array<any>;
  upcomingMatches: Array<any>;
  fmatches: Array<any>;
  favorites: Favorites = new Favorites();
  paletteColour = 'primary';
  show: Array<any>;
  window: Window;
  location: Location;
  count = 0;

  ngOnInit() {
    this.matchService.upcomingMatch().subscribe(
      data => {
        this.matches = data['matches'];
        console.log(this.matches)
      }
    );
  }
  displayedColumns = ['unique_id', 'dateTimeGMT', 'matchStarted', 'squad', 'team1',
    'team2', 'toss_winner_team', 'type', 'recommand'];

  constructor(private matchService: MatchService, private favouriteService: FavouriteService,
    private authenticate: AuthenticationService, private snackBar: MatSnackBar, public dialog: MatDialog, private route: ActivatedRoute) {
    this.matches = [];
  }

  addToFavouriteList(match) {
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
  }
}
