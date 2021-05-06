import { DarkModeService } from './core/services/darkMode/dark-mode.service';
import { Component, HostListener, OnInit } from '@angular/core';



@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {
  title = 'web';
  darkMode = false

  constructor(private darkModeService: DarkModeService) {

  }

  ngOnInit(): void {

    this.darkModeService.enabled.asObservable().subscribe(e => {
      this.darkMode = e
    })

  }



}
