import { Component, Input, OnInit } from '@angular/core';
import { Anime } from 'src/app/core/models/modelst';

@Component({
  selector: 'anime-item-list',
  templateUrl: './anime-item-list.component.html',
  styleUrls: ['./anime-item-list.component.scss']
})
export class AnimeItemListComponent implements OnInit {


  @Input() anime: Anime

  constructor() { }

  ngOnInit(): void {
    if(!this.anime)
      throw new Error("Anime is required")
  }

}
