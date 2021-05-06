import { Component, Input, OnInit, Output, EventEmitter } from '@angular/core';

@Component({
  selector: 'error',
  templateUrl: './error.component.html',
  styleUrls: ['./error.component.scss']
})
export class ErrorComponent implements OnInit {
  @Output() buttonClicked = new EventEmitter()

  constructor() { }

  ngOnInit(): void {
  }

  onClick() {
    this.buttonClicked.emit()
  }

}
