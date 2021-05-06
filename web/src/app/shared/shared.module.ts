import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MaterialModule } from './material/material.module';
import { AnimeLayoutComponent } from './components/anime-layout/anime-layout.component';
import { AnimeItemComponent } from './components/anime-item/anime-item.component';
import { RouterModule } from '@angular/router';
import { IconButtonComponent } from './components/icon-button/icon-button.component';



@NgModule({
  declarations: [
    AnimeLayoutComponent,
    AnimeItemComponent,
    IconButtonComponent
  ],
  imports: [
    CommonModule,
    MaterialModule,
    RouterModule
  ],
  exports: [
    MaterialModule,
    IconButtonComponent,
    AnimeLayoutComponent
  ]
})
export class SharedModule { }
