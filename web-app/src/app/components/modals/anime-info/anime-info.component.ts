import { ServerModel } from './../../../models/api/apiContent/ServerModel';
import { AnimeService } from './../../../services/api/anime.service';
import { AnimeModel } from './../../../models/api/apiContent/AnimeModel';
import { Component, Inject, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { trigger, state, style, transition, animate } from '@angular/animations';

interface ModalData {
  animeInfo: AnimeModel,
  search: Boolean
}

@Component({
  selector: 'app-anime-info',
  templateUrl: './anime-info.component.html',
  styleUrls: ['./anime-info.component.css'],
  animations: [
    trigger('panelState', [
      state('closed', style({ height: '36px', overflow: 'hidden' })),
      state('open', style({ height: '*' })),
      transition('closed <=> open', animate('300ms ease-in-out')),
    ]),
  ]
})
export class AnimeInfoModal implements OnInit {

  Pages = Pages
  currentPage = Pages.info
  Status = Status
  currentStatus = Status.loading
  servers: ServerModel[] = []
  selectedEpisode
  folded = 'closed';
  buttonText = {
    text: "More",
    icon: "keyboard_arrow_down"
  }
  constructor(
    private dialogRef: MatDialogRef<AnimeInfoModal>,
    private animeService: AnimeService,
    @Inject(MAT_DIALOG_DATA) public modalData: ModalData) { }

  ngOnInit(): void {


    if (this.modalData.search)
      this.getAnimeInfo()
    else {
      this.currentStatus = Status.loaded
    }
  }

  scroll(element: HTMLElement, event) {
    event.preventDefault()
    let scrollValue = element.scrollLeft
    let scrollAmount = event.deltaY < 0 ? scrollValue + 150 : scrollValue - 150
    element.scroll({ left: scrollAmount })
  }

  getServers(id) {

    this.currentPage = Pages.servers
    this.selectedEpisode = id

  }

  serverSelected() {
    this.dialogRef.close()
  }

  back() {
    this.currentPage = Pages.info
  }

  getAnimeInfo() {
    this.currentStatus = Status.loading
    this.animeService.getAnimeInfo(this.modalData.animeInfo.title).subscribe(anime => {
      this.modalData.animeInfo = anime.info[0]
      this.currentStatus = Status.loaded
    })
  }

  toggleFold() {

    this.buttonText.text = this.folded === 'open' ? 'More' : 'Less'

    this.buttonText.icon = this.folded === 'open' ? 'keyboard_arrow_down' : 'keyboard_arrow_up'

    this.folded = this.folded === 'open' ? 'closed' : 'open';
  }

  getImage() {

    if (this.modalData.animeInfo.poster != null)
      return this.modalData.animeInfo.poster

    let start: string = this.modalData.animeInfo.image.split('/').shift()
    if (start.includes("http"))
      return this.modalData.animeInfo.image

    return 'data:image/png;base64,' + this.modalData.animeInfo.image

  }

}

enum Status {
  loading,
  loaded,
  error
}


enum Pages {
  info = 0,
  servers
}
