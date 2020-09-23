import { HostListener } from '@angular/core';
import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'web-app';
  opened = false;
  innerWidth = 0;

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
