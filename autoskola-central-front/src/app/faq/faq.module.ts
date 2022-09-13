import { NgModule } from '@angular/core';

import {SharedModule} from '../shared/shared.module';
import { ConfirmationService } from 'primeng/api';
import { FAQCrudComponent } from './components/faq-crud/faq-crud.component';
import { FAQRoutingModule } from './faq-routing.module';
import { FAQComponent } from './components/faq/faq.component';


@NgModule({
  declarations: [
    FAQCrudComponent,
    FAQComponent
  ],
  imports: [
    FAQRoutingModule,
    SharedModule
  ],
  providers: [ 
    ConfirmationService
  ]
})
export class FAQModule { }