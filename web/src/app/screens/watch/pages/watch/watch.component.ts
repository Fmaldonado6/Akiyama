import { DarkModeService } from './../../../../core/services/darkMode/dark-mode.service';
import { AnimeDetailComponent } from 'src/app/shared/components/anime-detail/anime-detail.component';
import { AnimeService } from 'src/app/core/services/anime/anime.service';
import { MatDialog } from '@angular/material/dialog';
import { Component, OnInit } from '@angular/core';
import { DomSanitizer, SafeResourceUrl } from '@angular/platform-browser';
import { Location } from '@angular/common';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-watch',
  templateUrl: './watch.component.html',
  styleUrls: ['./watch.component.scss']
})
export class WatchComponent implements OnInit {
  url: SafeResourceUrl = this.sanitizer.bypassSecurityTrustResourceUrl("");

  constructor(
    private dialog: MatDialog,
    private router: Router,
    private route: ActivatedRoute,
    private animeService: AnimeService,
    private darkModeService: DarkModeService,
    private sanitizer: DomSanitizer,
  ) {
    route.queryParams.subscribe(params => {
      this.changeUrl(params.url)
    })

  }

  changeUrl(url: string) {
    this.url = this.sanitizer.bypassSecurityTrustResourceUrl(url);
  }

  ngOnInit(): void {
  }

  back() {

    const last = this.animeService.lastRoute

    console.log(last)

    if (last)
      this.router.navigate([last])
    else
      this.router.navigate(["/"])

    if (this.animeService.lastAnimeOpened)
      this.dialog.open(AnimeDetailComponent, {
        data: {
          anime: this.animeService.lastAnimeOpened,
          animeId:this.animeService.lastAnimeOpened.id,
        },
        panelClass: this.darkModeService.enabled.value ? 'modal-dark' : 'modal',
      })
  }

}
