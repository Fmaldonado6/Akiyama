import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

const routes: Routes = [
  {
    path: "",
    loadChildren: () => import('./screens/home/home.module').then(m => m.HomeModule)
  },
  {
    path: "anime",
    loadChildren: () => import('./screens/anime/anime.module').then(m => m.AnimeModule)
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
