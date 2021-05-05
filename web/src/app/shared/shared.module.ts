import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MaterialModule } from './material/material.module';
import { AnimeLayoutComponent } from './components/anime-layout/anime-layout.component';



@NgModule({
  declarations: [
    AnimeLayoutComponent
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
