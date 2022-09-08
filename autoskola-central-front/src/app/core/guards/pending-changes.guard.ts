import { CanDeactivate } from '@angular/router';
import { Injectable } from '@angular/core';
import { ConfirmationService } from 'primeng/api';
import { TranslateService } from '@ngx-translate/core';
import { IspitComponent } from 'src/app/ispit/components/ispit.component';

export interface ComponentCanDeactivate {
  canDeactivate: () => boolean | Promise<boolean>;
}

@Injectable()
export class PendingChangesGuard implements CanDeactivate<ComponentCanDeactivate> {

    constructor(private confirmationService : ConfirmationService, private translateService : TranslateService){}

  canDeactivate(component: IspitComponent): boolean | Promise<boolean> {
    // if there are no pending changes, just allow deactivation; else confirm first
    //return component.canDeactivate() ?
      //true :
      // NOTE: this warning message will only be shown when navigating elsewhere within your angular app;
      // when navigating away from your angular app, the browser will show a generic warning message
      // see http://stackoverflow.com/a/42207299/7307355
      //confirm('WARNING: You have unsaved changes. Press Cancel to go back and save these changes, or OK to lose these changes.');
      if(component.canLeaveExam) return true;
      return new Promise((resolve) => {
        this.confirmationService.confirm({
            message: this.translateService.instant('message.leavingExam'),
            header: this.translateService.instant('buttons.confirm'),
            icon: 'pi pi-exclamation-triangle',
            acceptLabel: this.translateService.instant('buttons.yes'),
            rejectLabel: this.translateService.instant('buttons.no'),
            accept: () => {
                component.zavrsi();
                resolve(true);
            },
            reject: () => {
             resolve(false);
            }
        });
    });
  }
}