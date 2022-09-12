import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';

import { CoreModule } from './core/core.module';
import { HeaderComponent } from './core/components/header/header.component';

import {TableModule} from 'primeng/table';

import { HttpBackend, HttpClient, HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { TranslateLoader, TranslateModule } from '@ngx-translate/core';
import { TranslateHttpLoader } from '@ngx-translate/http-loader';
import { SharedModule } from './shared/shared.module';
import { AuthModule } from './auth/auth.module';
import { KategorijaModule } from './kategorija/kategorija.module';
import { UlogaModule } from './uloga/uloga.module';
import { AutoskolaInfoModule } from './autoskolaInfo/autoskolaInfo.module';
import { VoziloModule } from './vozilo/vozilo.module';
import { PitanjeModule } from './pitanje/pitanje.module';
import { InstruktorModule } from './instruktor/instruktor.module';
import { MojProfilModule } from './moj-profil/moj-profil.module';
import { StatusModule } from './status/status.module';
import { IspitModule } from './ispit/ispit.module';
import { SpinnerComponent } from './core/components/spinner/spinner.component';
import { HttpErrorInterceptor } from './core/interceptors/spinner.intercepotr';
import { PendingChangesGuard } from './core/guards/pending-changes.guard';
import { PregledPolaznikaModule } from './pregled-polaznika/pregled-polaznika.module';

// drugi loader factory za translate, koji se ne triggera na poziv interceptora jer se inace bugaju prijevodi
export function translateHttpLoaderFactory(httpBackend: HttpBackend): TranslateHttpLoader {
  return new TranslateHttpLoader(new HttpClient(httpBackend), './assets/i18n/');
}

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    SpinnerComponent
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
    PitanjeModule,
    InstruktorModule,
    MojProfilModule,
    StatusModule,
    IspitModule,
    PregledPolaznikaModule,
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
  providers: [{
    provide: HTTP_INTERCEPTORS, 
    useClass: HttpErrorInterceptor, 
    multi: true
  },
  PendingChangesGuard
],
  bootstrap: [AppComponent]
})
export class AppModule { }
