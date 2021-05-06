import { SharedModule } from './../../shared/shared.module';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AnimeRoutingModule } from './anime-routing.module';
import { AnimeDetailComponent } from './page/anime-detail/anime-detail.component';


@NgModule({
  declarations: [
    AnimeDetailComponent
  ],
  imports: [
    CommonModule,
    SharedModule,
    AnimeRoutingModule
  ]
})
export class AnimeModule { }
