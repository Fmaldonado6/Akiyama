import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-layout',
  templateUrl: './layout.component.html',
  styleUrls: ['./layout.component.scss']
})
export class LayoutComponent implements OnInit {

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


  constructor() { }

  ngOnInit(): void {
  }

}
