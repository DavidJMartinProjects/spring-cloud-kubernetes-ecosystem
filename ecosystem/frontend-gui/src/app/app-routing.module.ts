import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LeaderboardComponent } from './leaderboard/leaderboard.component';
import { PlayComponent } from './play/play.component';
import { RegisterComponent } from './register/register.component';

const routes: Routes = [  
  {path: 'register' , component: RegisterComponent},  
  {path: 'play' , component: PlayComponent},  
  {path: 'leaderboard' , component: LeaderboardComponent}  
];


@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
