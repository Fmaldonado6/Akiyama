import { Component, OnInit } from '@angular/core';
import { DataType } from 'src/app/models/api/responses/responsesSchema';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomePage implements OnInit {

  DataType = DataType

  constructor() { }

  ngOnInit() {
  }

}
