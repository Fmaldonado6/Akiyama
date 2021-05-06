import { Anime } from './../../models/modelst';
import { BehaviorSubject } from 'rxjs';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class FavoritesService {
  private FAVORITES_KEY = "akiyama_favorites"
  private _favorites: Anime[] = []
  favorites = new BehaviorSubject<Anime[]>([])

  constructor() {
    this.getFavorites()
  }


  addFavorite(anime: Anime) {
    this._favorites.push(anime)
    this.favorites.next(this._favorites)
    localStorage.setItem(this.FAVORITES_KEY, JSON.stringify(this._favorites))
  }


  isFavorite(anime: Anime): boolean {
    for (let fav of this._favorites) {
      if (fav.id == anime.id)
        return true
    }
    return false
  }

  removeFavorite(anime: Anime) {
    this._favorites = this._favorites.filter(e => e.id != anime.id)
    this.favorites.next(this._favorites)
    localStorage.setItem(this.FAVORITES_KEY, JSON.stringify(this._favorites))

  }

  getFavorites() {
    const jsonFavorites = localStorage.getItem(this.FAVORITES_KEY)
    if (!jsonFavorites)
      return

    const favorites = JSON.parse(jsonFavorites) as Anime[]
    this._favorites = favorites
    this.favorites.next(this._favorites)
  }

}
