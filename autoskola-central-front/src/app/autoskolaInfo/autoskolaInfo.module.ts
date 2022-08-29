import { NgModule } from '@angular/core';

import {SharedModule} from '../shared/shared.module';
import { ConfirmationService } from 'primeng/api';
import { AutoskolaInfoComponent } from './components/autoskolaInfo.component';
import { AutoskolaInfoRoutingModule } from './autoskolaInfo-routing.module';


@NgModule({
  declarations: [
    AutoskolaInfoComponent
  ],
  imports: [
    AutoskolaInfoRoutingModule,
    SharedModule
  ],
  providers: [ 
    ConfirmationService
  ]
})
export class AutoskolaInfoModule { }