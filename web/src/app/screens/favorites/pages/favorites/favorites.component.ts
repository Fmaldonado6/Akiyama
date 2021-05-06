import { MatDialog } from '@angular/material/dialog';
import { Subscription } from 'rxjs';
import { Component, OnInit, OnDestroy } from '@angular/core';
import { Anime, Status } from 'src/app/core/models/modelst';
import { FavoritesService } from 'src/app/core/services/favorites/favorites.service';
import { AnimeDetailComponent } from 'src/app/shared/components/anime-detail/anime-detail.component';
import { DarkModeService } from 'src/app/core/services/darkMode/dark-mode.service';

@Component({
  selector: 'app-favorites',
  templateUrl: './favorites.component.html',
  styleUrls: ['./favorites.component.scss']
})
export class FavoritesComponent implements OnInit, OnDestroy {
  Status = Status
  currentStatus = Status.empty
  favorites: Anime[] = []
  filteredFavorites: Anime[] = []
  subscription: Subscription
  constructor(
    private favoritesService: FavoritesService,
    private dialog: MatDialog,
    private darkModeService: DarkModeService
  ) { }
  ngOnDestroy(): void {
    if (!this.subscription)
      this.subscription.unsubscribe()
  }

  ngOnInit(): void {
    this.subscription = this.favoritesService.favorites.asObservable().subscribe(e => {
      this.favorites = e
      this.filteredFavorites = e
      if (this.favorites.length != 0)
        this.currentStatus = Status.loaded
      else
        this.currentStatus = Status.empty

    })
  }

  filter(query: string) {

    if (query == "" || !query)
      this.filteredFavorites = this.favorites
    else
      this.filteredFavorites = this.favorites.filter(e => e.title.toLowerCase().includes(query.toLowerCase()))

  }


  changeToDetail(anime: Anime) {
    this.dialog.open(AnimeDetailComponent,
      {
        data: { anime: anime },
        panelClass: this.darkModeService.enabled.value ? 'modal-dark' : 'modal',
      }
    )
  }


}
