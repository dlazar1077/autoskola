import { NgModule } from '@angular/core';

import {SharedModule} from '../shared/shared.module';
import { ConfirmationService } from 'primeng/api';
import { PitanjeComponent } from './components/pitanje.component';
import { PitanjeRoutingModule } from './pitanje-routing.module';


@NgModule({
  declarations: [
    PitanjeComponent
  ],
  imports: [
    PitanjeRoutingModule,
    SharedModule
  ],
  providers: [ 
    ConfirmationService
  ]
})
export class PitanjeModule { }