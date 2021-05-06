import { SharedModule } from './../../shared/shared.module';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { MoreRoutingModule } from './more-routing.module';
import { MainComponent } from './pages/main/main.component';
import { AboutComponent } from './pages/about/about.component';


@NgModule({
  declarations: [
    MainComponent,
    AboutComponent
  ],
  imports: [
    CommonModule,
    MoreRoutingModule,
    SharedModule
  ]
})
export class MoreModule { }
