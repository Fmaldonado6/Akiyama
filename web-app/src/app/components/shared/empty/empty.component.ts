import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'empty',
  templateUrl: './empty.component.html',
  styleUrls: ['./empty.component.css']
})
export class EmptyComponent implements OnInit {

  @Input() text = "No results"

  constructor() { }

  ngOnInit(): void {
  }

}
