import { Component, OnInit } from '@angular/core';
import { DomSanitizer, SafeResourceUrl } from '@angular/platform-browser';
import { Location } from '@angular/common';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-watch',
  templateUrl: './watch.component.html',
  styleUrls: ['./watch.component.scss']
})
export class WatchComponent implements OnInit {
  url: SafeResourceUrl = this.sanitizer.bypassSecurityTrustResourceUrl("");

  constructor(
    private route: ActivatedRoute,
    private location: Location,
    private sanitizer: DomSanitizer,
  ) {
    route.queryParams.subscribe(params => {
      this.changeUrl(params.url)
    })

  }

  changeUrl(url: string) {
    this.url = this.sanitizer.bypassSecurityTrustResourceUrl(url);
  }

  ngOnInit(): void {
  }

  back() {
    this.location.back()
  }

}
