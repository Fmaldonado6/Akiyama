import { AnimeService } from './services/api/anime.service';
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MaterialModule } from './material/material.module';
import { HomePage } from './pages/home/home.component';
import { SidenavComponent } from './components/layout/sidenav/sidenav.component';
import { TitleBarComponent } from './components/layout/title-bar/title-bar.component';
import { AnimeDisplayComponent } from './components/anime-display/anime-display.component';
import { HttpClientModule } from '@angular/common/http';
import { FlexLayoutModule } from '@angular/flex-layout/typings/module';

@NgModule({
  declarations: [
    AppComponent,
    HomePage,
    SidenavComponent,
    TitleBarComponent,
    AnimeDisplayComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FlexLayoutModule,
    MaterialModule,
    BrowserAnimationsModule
  ],
  providers: [
    AnimeService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
