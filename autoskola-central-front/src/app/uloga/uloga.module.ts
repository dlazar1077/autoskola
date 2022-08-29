import { NgModule } from '@angular/core';

import {SharedModule} from '../shared/shared.module';
import { ConfirmationService } from 'primeng/api';
import { UlogaComponent } from './components/uloga.component';
import { UlogaRoutingModule } from './uloga-routing.module';


@NgModule({
  declarations: [
    UlogaComponent
  ],
  imports: [
    UlogaRoutingModule,
    SharedModule
  ],
  providers: [ 
    ConfirmationService
  ]
})
export class UlogaModule { }