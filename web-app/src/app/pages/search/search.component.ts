import { AnimeDisplayComponent } from './../../components/anime-display/anime-display.component';
import { DataType } from 'src/app/models/api/responses/responsesSchema';
import { ActivatedRoute } from '@angular/router';
import { AnimeService } from './../../services/api/anime.service';
import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { ViewContainerRef } from '@angular/core';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchPage implements OnInit {
  @ViewChild("display", { read: ViewContainerRef }) searchDisplay

  DataType = DataType
  query: string = ""
  constructor(
    private animeService: AnimeService,
    private route: ActivatedRoute
  ) {
    this.route.queryParams.subscribe(params => {
      if (params.search != null)
        this.query = params.search.replace(/_/g, ' ')
    })
  }


  ngOnInit(): void {

  }

  ngAfterViewInit() {

  }

}
