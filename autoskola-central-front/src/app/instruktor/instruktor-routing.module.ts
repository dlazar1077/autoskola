import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { InstruktorComponent } from './components/instruktor.component';

const routes: Routes = [
  { path: 'instructor', component: InstruktorComponent}
];

@NgModule({
  declarations: [],
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class InstruktorRoutingModule { }