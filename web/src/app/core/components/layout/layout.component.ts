import { NavigationEnd, Router } from '@angular/router';
import { Component, HostListener, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-layout',
  templateUrl: './layout.component.html',
  styleUrls: ['./layout.component.scss']
})
export class LayoutComponent implements OnInit {
  innerWidth = 0;
  opened: boolean = true;

  sidenavItems = [
    {
      icon: "home_outline",
      title: "Home",
      path: "/"
    },
    {
      icon: "favorite_border",
      title: "Favorites",
      path: "/favorites"
    },
    {
      icon: "more_horiz",
      title: "More",
      path: "/more"
    }
  ]


  constructor(
    private router: Router
  ) {
    router.events.subscribe(e => {
      if (e instanceof NavigationEnd) {
        const route = e.url.split("?").shift()
        this.opened = route != '/watch'
      }
    })
  }

  ngOnInit(): void {
    this.innerWidth = window.innerWidth;
  }

  @HostListener('window:resize', ['$event'])
  onResize(event) {
    this.innerWidth = window.innerWidth;

  }

}
