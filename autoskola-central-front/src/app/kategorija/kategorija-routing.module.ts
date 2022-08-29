import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { KategorijaComponent } from './components/kategorija.component';

const routes: Routes = [
  { path: 'category', component: KategorijaComponent}
];

@NgModule({
  declarations: [],
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class KategorijaRoutingModule { }