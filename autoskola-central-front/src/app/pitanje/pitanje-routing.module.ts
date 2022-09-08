import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { PitanjeComponent } from './components/pitanje.component';

const routes: Routes = [
  { path: 'question', component: PitanjeComponent}
];

@NgModule({
  declarations: [],
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class PitanjeRoutingModule { }