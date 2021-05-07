import { DarkModeService } from 'src/app/core/services/darkMode/dark-mode.service';
import { MatDialog } from '@angular/material/dialog';
import { Component, OnInit } from '@angular/core';
import { Anime, Status } from 'src/app/core/models/modelst';
import { AnimeService } from 'src/app/core/services/anime/anime.service';
import { AnimeDetailComponent } from 'src/app/shared/components/anime-detail/anime-detail.component';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.scss']
})
export class SearchComponent implements OnInit {

  Status = Status
  currentStatus = Status.loading
  results: Anime[] = []
  constructor(
    private animeService: AnimeService,
    private dialog: MatDialog,
    private darkModeService: DarkModeService
  ) { }

  ngOnInit(): void {

    if (this.animeService.animes.length == 0)
      this.currentStatus = Status.empty

    this.animeService.animeObserver.asObservable().subscribe(e => {
      this.results = e
      this.currentStatus = Status.loaded
    })
  }
  search(query: string) {

    this.currentStatus = Status.loading
    this.animeService.search(query.toLowerCase()).subscribe(e => {
      this.results = e
      this.currentStatus = Status.loaded
    })
  }

  changeToDetail(anime: Anime) {
    this.dialog.open(AnimeDetailComponent,
      {
        data: { anime: anime },
        panelClass: this.darkModeService.enabled ? 'modal-dark' : 'modal',
      }
    )
  }

}
