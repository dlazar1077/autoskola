import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { FAQCrudComponent } from './components/faq-crud/faq-crud.component';
import { FAQComponent } from './components/faq/faq.component';

const routes: Routes = [
  { path: 'faqcrud', component: FAQCrudComponent},
  { path: 'faq', component: FAQComponent}
];

@NgModule({
  declarations: [],
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class FAQRoutingModule { }