import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MaterialModule } from './material/material.module';
import { AnimeLayoutComponent } from './components/anime-layout/anime-layout.component';
import { AnimeItemComponent } from './components/anime-item/anime-item.component';



@NgModule({
  declarations: [
    AnimeLayoutComponent,
    AnimeItemComponent
  ],
  imports: [
    CommonModule,
    MaterialModule
  ],
  exports: [
    MaterialModule,
    AnimeLayoutComponent
  ]
})
export class SharedModule { }
