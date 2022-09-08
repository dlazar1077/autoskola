import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { MojProfilComponent } from './components/moj-profil/moj-profil.component';

const routes: Routes = [
  { path: 'myProfile', component: MojProfilComponent}
];

@NgModule({
  declarations: [],
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class MojProfilRoutingModule { }