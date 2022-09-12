import { NgModule } from '@angular/core';

import {SharedModule} from '../shared/shared.module';
import { ConfirmationService } from 'primeng/api';
import { PregledPolaznikaComponent } from './components/pregled-polaznika/pregled-polaznika.component';
import { PregledPolaznikaRoutingModule } from './pregled-polaznika-routing.module';


@NgModule({
  declarations: [
    PregledPolaznikaComponent
  ],
  imports: [
    PregledPolaznikaRoutingModule,
    SharedModule
  ],
  providers: [ 
    ConfirmationService
  ],
  exports : [
  ]
})
export class PregledPolaznikaModule { }