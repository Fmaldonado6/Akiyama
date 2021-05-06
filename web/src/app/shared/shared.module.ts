import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MaterialModule } from './material/material.module';
import { AnimeLayoutComponent } from './components/anime-layout/anime-layout.component';
import { AnimeItemComponent } from './components/anime-item/anime-item.component';
import { RouterModule } from '@angular/router';
import { IconButtonComponent } from './components/icon-button/icon-button.component';
import { AnimeDetailComponent } from './components/anime-detail/anime-detail.component';
import { ServersComponent } from './components/servers/servers.component';
import { AnimeItemListComponent } from './components/anime-item-list/anime-item-list.component';
import { EmptyComponent } from './components/empty/empty.component';
import { ErrorComponent } from './components/error/error.component';



@NgModule({
  declarations: [
    AnimeLayoutComponent,
    AnimeItemComponent,
    IconButtonComponent,
    AnimeDetailComponent,
    ServersComponent,
    AnimeItemListComponent,
    EmptyComponent,
    ErrorComponent
  ],
  imports: [
    CommonModule,
    MaterialModule,
    RouterModule
  ],
  exports: [
    MaterialModule,
    IconButtonComponent,
    AnimeLayoutComponent,
    AnimeDetailComponent,
    ErrorComponent,
    EmptyComponent,
    AnimeItemListComponent
  ]
})
export class SharedModule { }
