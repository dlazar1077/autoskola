import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';

import { CoreModule } from './core/core.module';
import { HeaderComponent } from './core/components/header/header.component';

import {TableModule} from 'primeng/table';

import { HttpBackend, HttpClient, HttpClientModule } from '@angular/common/http';
import { TranslateLoader, TranslateModule } from '@ngx-translate/core';
import { TranslateHttpLoader } from '@ngx-translate/http-loader';
import { SharedModule } from './shared/shared.module';
import { AuthModule } from './auth/auth.module';
import { KategorijaModule } from './kategorija/kategorija.module';
import { UlogaModule } from './uloga/uloga.module';
import { AutoskolaInfoModule } from './autoskolaInfo/autoskolaInfo.module';
import { VoziloModule } from './vozilo/vozilo.module';

// drugi loader factory za translate, koji se ne triggera na poziv interceptora jer se inace bugaju prijevodi
export function translateHttpLoaderFactory(httpBackend: HttpBackend): TranslateHttpLoader {
  return new TranslateHttpLoader(new HttpClient(httpBackend), './assets/i18n/');
}

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    CoreModule,
    AuthModule,
    KategorijaModule,
    UlogaModule,
    AutoskolaInfoModule,
    VoziloModule,
    HttpClientModule,
    TableModule,
    SharedModule,
    TranslateModule.forRoot({
      loader: {
        provide: TranslateLoader,
        useFactory: translateHttpLoaderFactory,
        deps: [HttpBackend]
      }
    })
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
