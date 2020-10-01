import { Component, OnInit } from '@angular/core';
import { DomSanitizer, SafeResourceUrl } from '@angular/platform-browser';
import { ActivatedRoute, Router } from '@angular/router';
import { Location } from '@angular/common';
@Component({
  selector: 'app-watch',
  templateUrl: './watch.component.html',
  styleUrls: ['./watch.component.css']
})
export class WatchPage implements OnInit {
  url: SafeResourceUrl = this.sanitizer.bypassSecurityTrustResourceUrl("");



  constructor(
    private route: ActivatedRoute,
    private location: Location,
    private sanitizer: DomSanitizer,
    private router: Router) {

    route.queryParams.subscribe(params => {
      this.changeUrl(params.url)
    })

  }

  ngOnInit(): void {
  }

  changeUrl(url) {
    this.url = this.sanitizer.bypassSecurityTrustResourceUrl(url);

  }

  back() {
    this.location.back()
  }

}
