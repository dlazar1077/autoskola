import { NgModule } from '@angular/core';

import {SharedModule} from '../shared/shared.module';
import { ConfirmationService } from 'primeng/api';
import { InstruktorComponent } from './components/instruktor.component';
import { InstruktorRoutingModule } from './instruktor-routing.module';


@NgModule({
  declarations: [
    InstruktorComponent
  ],
  imports: [
    InstruktorRoutingModule,
    SharedModule
  ],
  providers: [ 
    ConfirmationService
  ]
})
export class InstruktorModule { }