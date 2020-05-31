import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PaiementService {

  constructor(private http: HttpClient) { }

  
  paiement(arg0: number):Observable<any> {
    
   return this.http.post("http://localhost:8765/api/paiement/make/payment?sum="+arg0, {})
   ;
  }

}
