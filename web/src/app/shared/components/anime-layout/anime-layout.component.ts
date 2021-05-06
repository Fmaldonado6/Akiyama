import { AnimeDetailComponent } from '../anime-detail/anime-detail.component';
import { MatDialog } from '@angular/material/dialog';
import { NavigationExtras, Router } from '@angular/router';
import { Component, Input, OnInit } from '@angular/core';
import { Anime, Episode, InfoItem, Status, Type } from 'src/app/core/models/modelst';
import { DarkMode } from 'src/app/app.component';

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
    private router: Router,
    private dialog: MatDialog,
  ) { }

  ngOnInit(): void { }

  changeToDetail(anime: Anime) {

    this.dialog.open(AnimeDetailComponent,
      {
        data: { anime: anime },
        panelClass: DarkMode.enabled ? 'modal-dark' : 'modal',
      }
    )

  }


  scrollLeft(view: HTMLElement) {
    view.scrollLeft -= view.clientWidth - 150
  }

  scrollRight(view: HTMLElement) {
    view.scrollLeft += view.clientWidth - 150
  }

}
