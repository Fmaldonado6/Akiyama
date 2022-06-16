import { AnimeDetailComponent } from 'src/app/shared/components/anime-detail/anime-detail.component';
import { ActivatedRoute, Router, NavigationEnd } from '@angular/router';
import { MatDialog } from '@angular/material/dialog';
import { DarkModeService } from './core/services/darkMode/dark-mode.service';
import { AfterContentChecked, AfterContentInit, AfterViewInit, Component, HostListener, OnInit } from '@angular/core';



@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {
  title = 'web';
  darkMode = false

  private hasOpenedModal = false

  constructor(
    private darkModeService: DarkModeService,
    private dialog: MatDialog,
    private route: ActivatedRoute,
    private router: Router
  ) {

  }


  ngOnInit(): void {

    this.darkModeService.enabled.asObservable().subscribe(e => {
      this.darkMode = e
    })

    const animeId = window.location.search.split("id=").pop()
    if (animeId != null && animeId != "")
      this.dialog.open(AnimeDetailComponent, {
        data: {
          animeId: animeId
        },
        panelClass: this.darkModeService.enabled.value ? 'modal-dark' : 'modal',

      })




  }





}
