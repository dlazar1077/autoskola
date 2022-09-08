import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AuthGuard } from '../core/guards/auth.guard';
import { PendingChangesGuard } from '../core/guards/pending-changes.guard';
import { IspitComponent } from './components/ispit.component';

const routes: Routes = [
  { path: 'exam', component: IspitComponent, canActivate:[AuthGuard], canDeactivate: [PendingChangesGuard]}
];

@NgModule({
  declarations: [],
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class IspitRoutingModule { }