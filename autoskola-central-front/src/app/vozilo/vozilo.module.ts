import { NgModule } from '@angular/core';

import {SharedModule} from '../shared/shared.module';
import { ConfirmationService } from 'primeng/api';
import { VoziloComponent } from './components/vozilo.component';
import { VoziloRoutingModule } from './vozilo-routing.module';


@NgModule({
  declarations: [
    VoziloComponent
  ],
  imports: [
    VoziloRoutingModule,
    SharedModule
  ],
  providers: [ 
    ConfirmationService
  ]
})
export class VoziloModule { }