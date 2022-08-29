import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { UlogaComponent } from './components/uloga.component';

const routes: Routes = [
  { path: 'roles', component: UlogaComponent}
];

@NgModule({
  declarations: [],
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class UlogaRoutingModule { }