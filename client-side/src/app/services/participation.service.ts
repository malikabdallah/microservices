import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Participation } from '../models/participation';
import { Observable } from 'rxjs';


let API_URL = "http://localhost:8765/api/musee/participations";

@Injectable({
  providedIn: 'root'
})
export class ParticipationService {
  

  constructor(private http: HttpClient) { }

  enroll(participation: Participation): Observable<any> {
    return this.http.post(API_URL  , JSON.stringify(participation),
    {headers: {"Content-Type":"application/json; charset=UTF-8"}});
  }}
