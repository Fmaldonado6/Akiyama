import { ServersComponent } from './../servers/servers.component';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { ActivatedRoute } from '@angular/router';
import { AnimeService } from 'src/app/core/services/anime/anime.service';
import { Anime, Status, Episode } from 'src/app/core/models/modelst';
import { Component, Inject, OnInit } from '@angular/core';
import { MatBottomSheet } from '@angular/material/bottom-sheet';
import { DarkMode } from 'src/app/app.component';

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
  constructor(
    private dialogRef: MatDialogRef<AnimeDetailComponent>,
    private bottomSheet: MatBottomSheet,
    @Inject(MAT_DIALOG_DATA) private modalData: ModalData
  ) { }

  ngOnInit(): void {
    this.anime = this.modalData.anime
    this.currentStatus = Status.loaded
  }

  openServersBottomSheet(episode: Episode) {
    if (episode.nextEpisodeDate)
      return
    const bottom = this.bottomSheet.open(ServersComponent, {
      panelClass: DarkMode.enabled ? 'bottomSheet-dark' : '',
      data: {
        episode: episode
      }
    })
    bottom.instance.serverSelected.subscribe(e => {
      bottom.dismiss()
      this.close()
    })
  }


  close() {
    this.dialogRef.close()
  }

}
