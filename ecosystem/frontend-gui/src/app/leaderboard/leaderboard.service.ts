import { from, Observable } from 'rxjs';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Leaderboard } from './leaderboard'

@Injectable({
    providedIn: 'root'
})
export class LeaderboardService {

    constructor(private http: HttpClient) { }

    BASE_URL = 'http://localhost:80';
    LEADERBOARD_SERVICE = '/leaderboard-service';
    LEADERBOARD_URI = '/leaderboard';

    public getLeaderboards(): Observable<Leaderboard[]> {
        const url = this.BASE_URL + this.LEADERBOARD_SERVICE + this.LEADERBOARD_URI;
        console.log("making GET Request to : " + url);
        return this.http.get<Leaderboard[]>(url);
    }

}
