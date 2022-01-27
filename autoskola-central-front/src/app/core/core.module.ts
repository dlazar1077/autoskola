import { NgModule } from '@angular/core';

import {SharedModule} from '../shared/shared.module';
import { AboutComponent } from './components/about/about.component';
import { HomeComponent } from './components/home/home.component';
import { CoreRoutingModule } from './core-routing.module';

@NgModule({
  declarations: [
    AboutComponent,
    HomeComponent
  ],
  imports: [
    CoreRoutingModule,
    SharedModule
  ],
  providers: [ 
  ]
})
export class CoreModule { }