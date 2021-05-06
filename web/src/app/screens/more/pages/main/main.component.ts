import { Subscription } from 'rxjs';
import { Component, OnInit, OnDestroy } from '@angular/core';
import { environment } from 'src/environments/environment';
import { DarkModeService } from 'src/app/core/services/darkMode/dark-mode.service';

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.scss']
})
export class MainComponent implements OnInit, OnDestroy {
  version = environment.version
  darkMode = false
  subscription: Subscription
  constructor(
    private darkModeService: DarkModeService
  ) { }
  ngOnDestroy(): void {
    this.subscription.unsubscribe()
  }


  ngOnInit(): void {
    this.subscription = this.darkModeService.enabled.asObservable().subscribe(e => {
      this.darkMode = e
    })
  }

  toggleDarkMode(value) {
    this.darkModeService.toggleDarkMode(value)
  }


}
