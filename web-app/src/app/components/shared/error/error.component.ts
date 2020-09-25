import { Component, OnInit, Output, EventEmitter } from '@angular/core';

@Component({
  selector: 'error',
  templateUrl: './error.component.html',
  styleUrls: ['./error.component.css']
})
export class ErrorComponent implements OnInit {

  @Output() retry = new EventEmitter()

  constructor() { }

  ngOnInit(): void {
  }

  retryClicked(){
    this.retry.emit()
  }

}
