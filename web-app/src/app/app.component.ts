import { HostListener } from '@angular/core';
import { Component } from '@angular/core';
import { NavigationEnd, Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'web-app';
  opened = false;
  innerWidth = 0;
  watchPage = false;

  constructor(private router: Router) {

    router.events.subscribe(val => {
      if (val instanceof NavigationEnd) {
        let route = val.url.split("?").shift()
        if (route == '/watch')
          this.watchPage = true
        else
          this.watchPage = false

      }
    })

  }

  ngOnInit() {

    this.innerWidth = window.innerWidth

    if (this.isDesktop()) {
      this.opened = true;
    }
  }

  @HostListener('window:resize', ['$event'])
  onResize(event) {
    this.innerWidth = window.innerWidth;

    if (this.isDesktop()) {
      this.opened = true
    }
  }

  isDesktop(): boolean {
    return this.innerWidth > 960
  }

}
