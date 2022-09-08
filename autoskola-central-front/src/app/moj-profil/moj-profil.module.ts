import { NgModule } from '@angular/core';

import {SharedModule} from '../shared/shared.module';
import { ConfirmationService } from 'primeng/api';
import { MojProfilRoutingModule } from './moj-profil-routing.mogule';
import { PregledIspitaComponent } from './components/pregled-ispita/pregled-ispita.component';
import { MojProfilComponent } from './components/moj-profil/moj-profil.component';


@NgModule({
  declarations: [
    MojProfilComponent,
    PregledIspitaComponent
  ],
  imports: [
    MojProfilRoutingModule,
    SharedModule
  ],
  providers: [ 
    ConfirmationService
  ]
})
export class MojProfilModule { }