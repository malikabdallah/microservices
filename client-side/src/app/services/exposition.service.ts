import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Exposition } from '../models/Exposition';
import { Observable } from 'rxjs';



let API_URL = "http://localhost:8765/api/musee/";


@Injectable({
  providedIn: 'root'
})
export class ExpositionService {
 
 
 
  trouverExpositionParId(expositionId: number): Observable<Exposition> {
    API_URL=API_URL+"expositions/"+expositionId;
    alert("api url "+API_URL);
    return this.http.get<any>(API_URL,{headers: {"Content-Type":"application/json; charset=UTF-8"}});
  }
  constructor(private http: HttpClient) { }


  
  findAllExposition(): Observable<any> {
    return this.http.get(API_URL+"expositions/",{headers: {"Content-Type":"application/json; charset=UTF-8"}});
  }

  createExposition(exposition: Exposition): Observable<any> {
    return this.http.post(API_URL + "expositions", JSON.stringify(exposition),{headers: {"Content-Type":"application/json; charset=UTF-8"}});
  }


  
  findtest(courseId: number): Observable<any> {
    return this.http.get("  http://localhost:8765/api/musee/expositions/commentaire/25", {headers: {"Content-Type":"application/json; charset=UTF-8"}});
  }

  findExpositionDetail(courseId: number): Observable<any> {
    return this.http.get(API_URL + "expositions/" + courseId, {headers: {"Content-Type":"application/json; charset=UTF-8"}});
  }


  findExpositionCommentes(courseId: number): Observable<any> {
    return this.http.get(API_URL + "expositions/commentaire/" + courseId, {headers: {"Content-Type":"application/json; charset=UTF-8"}});
  }


  
  findexpositionbyid(expoid: number): Observable<any> {
    return this.http.get(API_URL + "expositions/" + expoid, {headers: {"Content-Type":"application/json; charset=UTF-8"}});
  }


}
