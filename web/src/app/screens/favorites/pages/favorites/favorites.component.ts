import { MatDialog } from '@angular/material/dialog';
import { Subscription } from 'rxjs';
import { Component, OnInit, OnDestroy } from '@angular/core';
import { Anime, Status } from 'src/app/core/models/modelst';
import { FavoritesService } from 'src/app/core/services/favorites/favorites.service';
import { DarkMode } from 'src/app/app.component';
import { AnimeDetailComponent } from 'src/app/shared/components/anime-detail/anime-detail.component';

@Component({
  selector: 'app-favorites',
  templateUrl: './favorites.component.html',
  styleUrls: ['./favorites.component.scss']
})
export class FavoritesComponent implements OnInit, OnDestroy {
  Status = Status
  currentStatus = Status.empty
  favorites: Anime[] = []
  subscription: Subscription
  constructor(
    private favoritesService: FavoritesService,
    private dialog: MatDialog
  ) { }
  ngOnDestroy(): void {
    if (!this.subscription)
      this.subscription.unsubscribe()
  }

  ngOnInit(): void {
    this.subscription = this.favoritesService.favorites.asObservable().subscribe(e => {
      this.favorites = e
      if (this.favorites.length != 0)
        this.currentStatus = Status.loaded
      else
        this.currentStatus = Status.empty

    })
  }


  changeToDetail(anime: Anime) {
    this.dialog.open(AnimeDetailComponent,
      {
        data: { anime: anime },
        panelClass: DarkMode.enabled ? 'modal-dark' : 'modal',
      }
    )
  }


}
