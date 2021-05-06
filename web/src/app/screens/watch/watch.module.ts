import { SharedModule } from './../../shared/shared.module';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { WatchRoutingModule } from './watch-routing.module';
import { WatchComponent } from './pages/watch/watch.component';


@NgModule({
  declarations: [
    WatchComponent
  ],
  imports: [
    CommonModule,
    WatchRoutingModule,
    SharedModule
  ]
})
export class WatchModule { }
