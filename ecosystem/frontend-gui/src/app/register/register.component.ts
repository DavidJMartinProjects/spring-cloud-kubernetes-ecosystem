import { NgModule } from '@angular/core';
import { Component, OnInit } from '@angular/core';


@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})

export class RegisterComponent implements OnInit {

  constructor() { 
  }

  dtOptions: DataTables.Settings = {
    searching: false,
    paging: false,
    info: false
  };

  ngOnInit(): void {
  }

}
