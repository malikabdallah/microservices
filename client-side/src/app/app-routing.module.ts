import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {HomeComponent} from './components/home/home.component';
import {LoginComponent} from './components/login/login.component';
import {RegisterComponent} from './components/register/register.component';
import { ExpositionDetailsComponent } from './components/exposition-details/exposition-details.component';
import { FormulairepaiementComponent } from './components/formulairepaiement/formulairepaiement.component';
import { PaiementaccepteComponent } from './components/paiementaccepte/paiementaccepte.component';
import { ConsulteroeuvresComponent } from './components/consulteroeuvres/consulteroeuvres.component';

const routes: Routes = [
  {path: '', redirectTo:'login', pathMatch:'full'},
  {path: 'home', component: HomeComponent},
  {path: 'login', component: LoginComponent},
  {path: 'register', component: RegisterComponent},
  {path: 'detail/:id', component: ExpositionDetailsComponent},
  {path:'paiement/:id',component:FormulairepaiementComponent},
  {path:'succespaiement',component:PaiementaccepteComponent},
  {path:'oeuvres/exposition/:id',component:ConsulteroeuvresComponent}

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
