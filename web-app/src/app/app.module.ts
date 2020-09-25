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
import { FlexLayoutModule } from '@angular/flex-layout';
import { AnimeInfoModal } from './components/modals/anime-info/anime-info.component';
import { ServerLayoutComponent } from './components/modals/servers/server-layout/server-layout.component';
import { WatchPage } from './pages/watch/watch.component';
import { ServersListModal } from './components/modals/servers/servers-list/servers-list.component';
import { SearchPage } from './pages/search/search.component';
import { ErrorComponent } from './components/shared/error/error.component';
import { AboutPage } from './pages/about/about.component';
import { EmptyComponent } from './components/shared/empty/empty.component';

@NgModule({
  declarations: [
    AppComponent,
    HomePage,
    SidenavComponent,
    TitleBarComponent,
    AnimeDisplayComponent,
    AnimeInfoModal,
    ServerLayoutComponent,
    WatchPage,
    ServersListModal,
    SearchPage,
    ErrorComponent,
    AboutPage,
    EmptyComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FlexLayoutModule,
    HttpClientModule,
    MaterialModule,
    BrowserAnimationsModule
  ],
  providers: [
    AnimeService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
