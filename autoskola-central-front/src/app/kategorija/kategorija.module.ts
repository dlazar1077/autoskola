import { NgModule } from '@angular/core';

import {SharedModule} from '../shared/shared.module';
import { KategorijaComponent } from './components/kategorija.component';
import { KategorijaRoutingModule } from './kategorija-routing.module';
import { ConfirmationService } from 'primeng/api';


@NgModule({
  declarations: [
    KategorijaComponent
  ],
  imports: [
    KategorijaRoutingModule,
    SharedModule
  ],
  providers: [ 
    ConfirmationService
  ]
})
export class KategorijaModule { }