import { Component, OnInit } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import * as http from "http";

@Component({
    selector: 'cm-about',
    templateUrl: './about.component.html'
})
export class AboutComponent implements OnInit {

    constructor(private http: HttpClient) { }

    ngOnInit() {
      console.log("test123")
      this.http.get<any>("/chess-game/start").subscribe(data => console.log(data));
    }

}
