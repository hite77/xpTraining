import { Injectable } from '@angular/core';
import { HttpClient } from "@angular/common/http";
import { Observable} from "rxjs";
import { tap } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class RockpaperscissorsService {

    constructor(private http: HttpClient) {}

    list(): Observable<any> {
    //   const url =  "http://localhost:8080/api/gameResults/playerList";
      const url = "http://localhost:8080/api/round/Rock/Rock";
      return this.http.get<String[]>(url).pipe(
        tap(data => console.log('All: ' + JSON.stringify(data))),
    );;
    }
}
