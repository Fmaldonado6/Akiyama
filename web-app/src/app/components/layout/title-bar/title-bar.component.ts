import { Router, NavigationExtras, ActivatedRoute, NavigationEnd } from '@angular/router';
import { HostListener } from '@angular/core';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'title-bar',
  templateUrl: './title-bar.component.html',
  styleUrls: ['./title-bar.component.css']
})
export class TitleBarComponent implements OnInit {
  innerWidth = 0;
  searchPage = false
  constructor(
    private router: Router
  ) { }

  ngOnInit() {
    this.innerWidth = window.innerWidth
    this.router.events.subscribe(val => {
      if (val instanceof NavigationEnd) {
        let route = val.url.split("?").shift()
        if (route == '/search')
          this.searchPage = true
        else
          this.searchPage = false

      }
    })
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
