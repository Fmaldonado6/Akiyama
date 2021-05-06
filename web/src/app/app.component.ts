import { Component, HostListener, OnInit } from '@angular/core';

export class DarkMode {
  static enabled = false
}

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {
  title = 'web';
  darkMode = false
  ngOnInit(): void {
    this.darkMode = DarkMode.enabled = window.matchMedia('(prefers-color-scheme:dark)').matches
    window.matchMedia('(prefers-color-scheme:dark)').addEventListener("change", e => {
      this.darkMode = DarkMode.enabled = e.matches
    })

  }



}
