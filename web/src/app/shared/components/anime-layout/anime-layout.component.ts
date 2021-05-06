import { NavigationExtras, Router } from '@angular/router';
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

  constructor(
    private router: Router
  ) { }

  ngOnInit(): void { }

  changeToDetail(anime: Anime) {

    const id = anime.id.split("/").pop()

    const extras: NavigationExtras = {
      state: {
        data: anime
      }
    }

    this.router.navigate([`anime/${id}/${anime.title}`], extras)
  }


  scrollLeft(view: HTMLElement) {
    view.scrollLeft -= view.clientWidth - 150
  }

  scrollRight(view: HTMLElement) {
    view.scrollLeft += view.clientWidth - 150
  }

}
