import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './components/login/login.component';
import { RegisterComponent } from './components/register/register.component';
import { HomeComponent } from './components/home/home.component';

import {HttpClientModule} from '@angular/common/http';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import { ExpositionDetailsComponent } from './components/exposition-details/exposition-details.component';
import { FormulairepaiementComponent } from './components/formulairepaiement/formulairepaiement.component';
import { PaiementaccepteComponent } from './components/paiementaccepte/paiementaccepte.component';
import { ConsulteroeuvresComponent } from './components/consulteroeuvres/consulteroeuvres.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegisterComponent,
    HomeComponent,
    ExpositionDetailsComponent,
    FormulairepaiementComponent,
    PaiementaccepteComponent,
    ConsulteroeuvresComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
