import { BehaviorSubject } from 'rxjs';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class DarkModeService {
  DARK_MODE_KEY = "akiyama_dark_mode_enabled"
  enabled = new BehaviorSubject(false)

  constructor() {
    this.getDarkMode()
  }

  toggleDarkMode(value: boolean) {
    this.enabled.next(value)
    localStorage.setItem(this.DARK_MODE_KEY, JSON.stringify(this.enabled.value))
  }

  getDarkMode() {
    const val = localStorage.getItem(this.DARK_MODE_KEY)

    if (!val)
      return this.enabled.next(window.matchMedia('(prefers-color-scheme:dark)').matches)


    this.enabled.next(val == true.toString())
  }

}
