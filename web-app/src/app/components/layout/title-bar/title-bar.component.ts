import { Router, NavigationExtras } from '@angular/router';
import { HostListener } from '@angular/core';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'title-bar',
  templateUrl: './title-bar.component.html',
  styleUrls: ['./title-bar.component.css']
})
export class TitleBarComponent implements OnInit {
  innerWidth = 0;

  constructor(
    private router: Router
  ) { }

  ngOnInit() {
    this.innerWidth = window.innerWidth
  }

  makeSearch(search: string) {
    let extras: NavigationExtras = {
      queryParams: {
        search: search.replace(/ /g, '_')
      }
    }

    this.router.navigate(["/search"], extras)

  }

  @HostListener('window:resize', ['$event'])
  onResize(event) {
    this.innerWidth = window.innerWidth;
  }

  isDesktop(): boolean {
    return this.innerWidth > 960
  }

}
