import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AuthGuard } from '../core/guards/auth.guard';
import { PregledPolaznikaComponent } from './components/pregled-polaznika/pregled-polaznika.component';

const routes: Routes = [
  { path: 'participants', component: PregledPolaznikaComponent, canActivate:[AuthGuard]},
  //{ path: 'participant/:id', component: DnevnikVoznjeComponent, canActivate:[AuthGuard]}
];

@NgModule({
  declarations: [],
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class PregledPolaznikaRoutingModule { }