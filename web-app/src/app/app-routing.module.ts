import { WatchPage } from './pages/watch/watch.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HomePage } from './pages/home/home.component';
import { SearchPage } from './pages/search/search.component';
import { AboutPage } from './pages/about/about.component';


const routes: Routes = [
  {
    path: '',
    component:HomePage,
    
  },
  {
    path: 'watch',
    component:WatchPage,
    
  },
  {
    path: 'search',
    component:SearchPage,
    
  },
  {
    path: 'about',
    component:AboutPage,
    
  }


];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
