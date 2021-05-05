import { Component, Input, OnInit } from '@angular/core';
import { Anime, Episode } from 'src/app/core/models/modelst';

@Component({
  selector: 'anime-item',
  templateUrl: './anime-item.component.html',
  styleUrls: ['./anime-item.component.scss']
})
export class AnimeItemComponent implements OnInit {

  @Input() anime: Anime
  @Input() episode: Episode

  constructor() { }

  ngOnInit(): void {
  }

}
