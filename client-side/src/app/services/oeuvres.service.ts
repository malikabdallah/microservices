import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

let API_URL = "http://localhost:8765/api/musee/";

@Injectable({
  providedIn: 'root'
})
export class OeuvresService {

  constructor(private http: HttpClient) { }




  
  findAllExposition(id:string): Observable<any> {
    return this.http.get(API_URL+"expositions/"+id+"/oeuvres",{headers: {"Content-Type":"application/json; charset=UTF-8"}});
  }

}
