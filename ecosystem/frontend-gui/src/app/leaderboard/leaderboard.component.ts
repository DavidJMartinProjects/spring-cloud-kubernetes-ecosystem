import { Component, OnInit } from '@angular/core';
import { Leaderboard } from './leaderboard';
import { LeaderboardService } from './leaderboard.service';

@Component({
  selector: 'app-leaderboard',
  templateUrl: './leaderboard.component.html',
  styleUrls: ['./leaderboard.component.css']
})
export class LeaderboardComponent implements OnInit {

  dtOptions: DataTables.Settings = {};  
  leaderboardEntries = new Array<Leaderboard>();
  subscription: any;

  constructor(private leaderboardService: LeaderboardService) {
    this.subscription = 
    leaderboardService.getLeaderboards()
    .subscribe(response => {
      this.leaderboardEntries = response.map(item => {
        return new Leaderboard(          
          item.name,
          item.country,
          item.score,
          item.rank
        );
      });
    });
  
    this.dtOptions = {
      searching: true, 
      ordering: true, 
      info: false, 
      lengthChange: false, 
      paging: false
    };    
  }
  
  ngOnInit(): void {
    throw new Error('Method not implemented.');
  }
  
}

