import { NgModule } from '@angular/core';

import {SharedModule} from '../shared/shared.module';
import { ConfirmationService } from 'primeng/api';
import { IspitComponent } from './components/ispit.component';
import { IspitRoutingModule } from './ispit-routing.module';


@NgModule({
  declarations: [
    IspitComponent
  ],
  imports: [
    IspitRoutingModule,
    SharedModule
  ],
  providers: [ 
    ConfirmationService
  ]
})
export class IspitModule { }