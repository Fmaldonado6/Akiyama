import { AppError } from './../../services/common/errors/app-error';
import { ServersListModal } from './../modals/servers/servers-list/servers-list.component';
import { AnimeInfoModal } from './../modals/anime-info/anime-info.component';
import { AnimeModel } from './../../models/api/apiContent/AnimeModel';
import { AnimeService } from './../../services/api/anime.service';
import { Component, HostListener, Input, OnInit } from '@angular/core';
import { EpisodeModel } from 'src/app/models/api/apiContent/EpisodeModel';
import { MatDialog } from '@angular/material/dialog';
import { DataType } from 'src/app/models/api/responses/responsesSchema';

@Component({
  selector: 'anime-display',
  templateUrl: './anime-display.component.html',
  styleUrls: ['./anime-display.component.css']
})
export class AnimeDisplayComponent implements OnInit {
  @Input() title = ""
  @Input() dataType
  @Input() query

  DataType = DataType
  Status = Status
  currentStatus = Status.loading
  data: EpisodeModel[] | AnimeModel[]


  constructor(private animeService: AnimeService, private dialog: MatDialog) {
  }

  ngOnInit() {

    if (this.dataType != DataType.search)
      this.fetchData()
  }

  fetchData(force: Boolean = false) {
    this.currentStatus = Status.loading
    let data = this.getData()

    if (!this.shouldFetch(data) && !force) {
      this.currentStatus = Status.loaded
      return this.data = data.data
    }

    this.animeService.getData(this.dataType).subscribe((data: any) => {
      //gets the data of the first key
      this.data = data[Object.keys(data)[0]] as any
      this.saveData(this.data)
      this.currentStatus = Status.loaded
    }, () => {
      this.currentStatus = Status.error
    })

  }



  public makeSearch() {
    this.currentStatus = Status.loading

    this.animeService.searchResult(this.query).subscribe(result => {
      this.data = result.search
      this.currentStatus = Status.loaded

    }, (error) => {
      if (error instanceof AppError)
        return this.currentStatus = Status.empty
      this.currentStatus = Status.error
  })

}

scroll(element: HTMLElement, event) {
  event.preventDefault()
  let scrollValue = element.scrollLeft
  let scrollAmount = event.deltaY < 0 ? scrollValue + 150 : scrollValue - 150
  element.scroll({ left: scrollAmount })
}

openModal(data) {
  if (this.dataType == DataType.episodes) {
    let episode = data as EpisodeModel
    this.dialog.open(ServersListModal,
      {
        data:
        {
          servers: episode.servers
        },
        panelClass: "animeModal"
      })
  }
  else {
    this.dialog.open(AnimeInfoModal,
      {
        data:
        {
          animeInfo: data, search: this.query != null
        },
        panelClass: "animeModal"
      })
  }
}

getImage(image: string) {

  let start: string = image.split('/').shift()
  if (start.includes("http"))
    return image

  return 'data:image/png;base64,' + image

}

shouldFetch(data) {
  if (!data)
    return true

  let now = Date.now()

  let hoursPassed = Math.abs(now - data.savedTime) / 36e5

  if (hoursPassed > 1)
    return true

  return false

}

saveData(data) {
  let savedData = {
    data: data,
    savedTime: Date.now()
  }
  localStorage.setItem(this.dataType, JSON.stringify(savedData))

}

getData() {
  return JSON.parse(localStorage.getItem(this.dataType))
}

ngOnChanges(change){
  if (change.query)
    this.makeSearch()
}


}

export enum Status {
  loading,
  loaded,
  empty,
  error
}


