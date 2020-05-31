import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Billet } from '../models/billet';
import { Observable } from 'rxjs';


let API_URL = "http://localhost:8765/api/musee/billets";

@Injectable({
  providedIn: 'root'
})
export class BilletService {
  constructor(private http: HttpClient) { }

  envoyerBillet(billet: Billet):Observable<any> {
    alert("envoye billet");
    return this.http.post(API_URL , JSON.stringify(billet),{headers: {"Content-Type":"application/json; charset=UTF-8"}});
  }

  envoieQr(id:number,email:string):Observable<any> {

    alert("email "+email);
    
    return this.http.get("http://localhost:8765/api/musee/qrcode/billet/"+id+"/send/"+email ,{headers: {"Content-Type":"application/json; charset=UTF-8"}});
  }
}
