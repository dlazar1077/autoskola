import { NgModule } from '@angular/core';

import { MenubarModule } from 'primeng/menubar';
import { ButtonModule } from 'primeng/button';
import {InputTextModule} from 'primeng/inputtext';
import { DropdownModule } from 'primeng/dropdown';
import { TranslateModule } from '@ngx-translate/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import {PasswordModule} from 'primeng/password';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {TableModule} from 'primeng/table';
import {DialogModule} from 'primeng/dialog';
import { ConfirmDialogModule } from 'primeng/confirmdialog';
import {ToolbarModule} from 'primeng/toolbar';
import {PanelModule} from 'primeng/panel';
import {FileUploadModule} from 'primeng/fileupload';
import {HttpClientModule} from '@angular/common/http';
import {InputNumberModule} from 'primeng/inputnumber';
import {CheckboxModule} from 'primeng/checkbox';
import {RadioButtonModule} from 'primeng/radiobutton';
import {MultiSelectModule} from 'primeng/multiselect';
import {AccordionModule} from 'primeng/accordion';
import {ToastModule} from 'primeng/toast';
import {CalendarModule} from 'primeng/calendar';
import { DnevnikVoznjeComponent } from '../pregled-polaznika/components/dnevnik-voznje/dnevnik-voznje.component';


@NgModule({
  declarations : [
    DnevnikVoznjeComponent
  ],
  imports: [
    MenubarModule,
    InputTextModule,
    ButtonModule,
    PasswordModule,
    DropdownModule,
    FormsModule,
    ReactiveFormsModule,
    BrowserAnimationsModule,
    TranslateModule,
    TableModule,
    PanelModule,
    DialogModule,
    ConfirmDialogModule,
    ToolbarModule,
    FileUploadModule,
    HttpClientModule,
    InputNumberModule,
    CheckboxModule,
    CalendarModule,
    RadioButtonModule,
    MultiSelectModule,
    AccordionModule,
    ToastModule
  ],
  exports : [
    MenubarModule,
    InputTextModule,
    ButtonModule,
    PasswordModule,
    DropdownModule,
    FormsModule,
    ReactiveFormsModule,
    BrowserAnimationsModule,
    TranslateModule,
    TableModule,
    PanelModule,
    DialogModule,
    ConfirmDialogModule,
    ToolbarModule,
    FileUploadModule,
    HttpClientModule,
    InputNumberModule,
    CheckboxModule,
    CalendarModule,
    RadioButtonModule,
    MultiSelectModule,
    AccordionModule,
    ToastModule,
    DnevnikVoznjeComponent
  ]
})
export class SharedModule { }