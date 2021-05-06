import { Component, Input, OnInit, Output, EventEmitter } from '@angular/core';

@Component({
  selector: 'icon-button',
  templateUrl: './icon-button.component.html',
  styleUrls: ['./icon-button.component.scss']
})
export class IconButtonComponent implements OnInit {

  @Input() icon = ""
  @Input() label = ""

  constructor() { }

  ngOnInit(): void {
    if (!this.icon || !this.label)
      throw new Error("Missing attributes")
  }

}
