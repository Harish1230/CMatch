import { Component, OnInit } from '@angular/core';
import {MatchService} from '../services/match.service'
import { Match } from '../match';
@Component({
  selector: 'app-oldmatches',
  templateUrl: './oldmatches.component.html',
  styleUrls: ['./oldmatches.component.css']
})
export class OldmatchesComponent implements OnInit {
  matches:Array<Match>;
  ngOnInit() {
    this.matchService.getOldMatch().subscribe(
      date =>{
        this.matches = date['data'];
        console.log(this.matches)
      }
    );
  }
    displayedColumns = ['unique_id','description','title'];
  constructor(private matchService:MatchService) {
    this.matches=[];
   }
}
