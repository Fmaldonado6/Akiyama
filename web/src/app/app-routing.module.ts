import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

const routes: Routes = [
  {
    path: "",
    loadChildren: () => import('./screens/home/home.module').then(m => m.HomeModule)
  },
  {
    path: "watch",
    loadChildren: () => import('./screens/watch/watch.module').then(m => m.WatchModule)
  },

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
