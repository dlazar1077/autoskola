import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AutoskolaInfoComponent } from './components/autoskolaInfo.component';

const routes: Routes = [
  { path: 'drivingSchoolInfo', component: AutoskolaInfoComponent}
];

@NgModule({
  declarations: [],
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AutoskolaInfoRoutingModule { }