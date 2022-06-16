import { Component, Input, OnInit } from '@angular/core';
import { Anime, Episode } from 'src/app/core/models/modelst';
import { AnimeService } from 'src/app/core/services/anime/anime.service';

@Component({
  selector: 'anime-item',
  templateUrl: './anime-item.component.html',
  styleUrls: ['./anime-item.component.scss']
})
export class AnimeItemComponent implements OnInit {

  imageUrl

  @Input() anime: Anime
  @Input() episode: Episode

  constructor(
    private animeService: AnimeService
  ) { }

  ngOnInit(): void {
    
  }

}
