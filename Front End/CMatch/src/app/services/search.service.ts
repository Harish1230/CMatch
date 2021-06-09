import { Injectable} from '@angular/core';
import {Match} from '../match';

@Injectable({
  providedIn: 'root'
})
export class SearchService {
  match:Match= new Match();

 results:Match[]=[];
//  result:Match= new Match();
 myMatch:Match=new Match();
 unique_id:number;
 count=0;
indCount=0;
constructor() { }
  
  public search(searchText:String,matches:Array<any>):Match[]
  {
    this.results=[];
    this.count=0;
    for(let i=0;i<matches.length;i++)
    {
      this.myMatch=matches[i];
      
      if(matches[i].type.toLowerCase()==searchText.toLowerCase().trim()||matches[i].type==searchText.trim())
      {
        this.count++;
        this.results.push(matches[i]);
      }
      
      if(matches[i].unique_id==searchText.trim())
      {
        this.count++;
        this.results.push(matches[i]);
      }

      if(this.myMatch["team-1"].toLowerCase()==searchText.toLowerCase().trim()||this.myMatch["team-2"].toLowerCase()==searchText.toLowerCase().trim() ||  this.myMatch["team-1"]==searchText.trim()||this.myMatch["team-2"]==searchText.trim())
      {
        this.count++;
        this.results.push(matches[i]);
      }

    }
    alert(""+this.count+" Results Found");
    return this.results
  }

}
