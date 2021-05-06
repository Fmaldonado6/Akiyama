import { FavoritesService } from './../../../core/services/favorites/favorites.service';
import { ServersComponent } from './../servers/servers.component';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { ActivatedRoute } from '@angular/router';
import { AnimeService } from 'src/app/core/services/anime/anime.service';
import { Anime, Status, Episode } from 'src/app/core/models/modelst';
import { Component, Inject, OnInit } from '@angular/core';
import { MatBottomSheet } from '@angular/material/bottom-sheet';
import { DarkModeService } from 'src/app/core/services/darkMode/dark-mode.service';

interface ModalData {
  anime: Anime
}

@Component({
  selector: 'app-anime-detail',
  templateUrl: './anime-detail.component.html',
  styleUrls: ['./anime-detail.component.scss']
})
export class AnimeDetailComponent implements OnInit {

  anime: Anime
  Status = Status
  currentStatus = Status.loading
  isFavorite = false
  expandedSynopsis = false
  label = {
    favorite: "Remove from favorites",
    notFavorite: "Add to favorites"
  }
  icons = {
    favorite: "favorite",
    notFavorite: "favorite_border"
  }
  constructor(
    private dialogRef: MatDialogRef<AnimeDetailComponent>,
    private bottomSheet: MatBottomSheet,
    private darkModeService: DarkModeService,
    private favoritesService: FavoritesService,
    @Inject(MAT_DIALOG_DATA) private modalData: ModalData
  ) { }

  ngOnInit(): void {
    this.anime = this.modalData.anime
    this.currentStatus = Status.loaded
    this.isFavorite = this.favoritesService.isFavorite(this.anime)
  }

  openServersBottomSheet(episode: Episode) {
    if (episode.nextEpisodeDate)
      return
    const bottom = this.bottomSheet.open(ServersComponent, {
      panelClass: this.darkModeService.enabled.value ? 'bottomSheet-dark' : '',
      data: {
        episode: episode
      }
    })
    bottom.instance.serverSelected.subscribe(e => {
      bottom.dismiss()
      this.close()
    })
  }

  favoritesToggle() {
    if (this.isFavorite)
      this.favoritesService.removeFavorite(this.anime)
    else
      this.favoritesService.addFavorite(this.anime)

    this.isFavorite = this.favoritesService.isFavorite(this.anime)
  }

  expand() {
    this.expandedSynopsis = !this.expandedSynopsis
  }

  close() {
    this.dialogRef.close()
  }

}
