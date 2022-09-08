import { NgModule } from '@angular/core';

import {SharedModule} from '../shared/shared.module';
import { ConfirmationService } from 'primeng/api';
import { StatusComponent } from './components/status.component';
import { StatusRoutingModule } from './status-routing.module';


@NgModule({
  declarations: [
    StatusComponent
  ],
  imports: [
    StatusRoutingModule,
    SharedModule
  ],
  providers: [ 
    ConfirmationService
  ]
})
export class StatusModule { }