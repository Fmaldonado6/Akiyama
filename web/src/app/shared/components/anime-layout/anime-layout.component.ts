import { Component, Input, OnInit } from '@angular/core';
import { Anime, Episode, InfoItem, Status, Type } from 'src/app/core/models/modelst';

@Component({
  selector: 'anime-layout',
  templateUrl: './anime-layout.component.html',
  styleUrls: ['./anime-layout.component.scss']
})
export class AnimeLayoutComponent implements OnInit {
  @Input() info: InfoItem
  @Input() title: string = ""
  Status = Status
  Type = Type

  constructor() { }

  ngOnInit(): void { }

  scrollLeft(view: HTMLElement) {
    console.log(view)
    view.scrollLeft -= 500
  }

  scrollRight(view: HTMLElement) {
    console.log(view)
    view.scrollLeft += 500
  }

}
