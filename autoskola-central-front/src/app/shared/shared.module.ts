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


@NgModule({
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
    DialogModule,
    ConfirmDialogModule,
    ToolbarModule
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
    DialogModule,
    ConfirmDialogModule,
    ToolbarModule
  ]
})
export class SharedModule { }