import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { comments } from '../models/comments';

import {Observable} from 'rxjs';

let API_URL = "http://localhost:8765/api/musee/commentaires";

@Injectable({
  providedIn: 'root'
})
export class CommentService {
  
  envoyerCommentaire(id: string):Observable<any> {
    return this.http.post("http://localhost:8765/api/course/commentaires/"+1 ,{headers: {"Content-Type":"application/json; charset=UTF-8"}});
  }


  getCommentaire(id: string):Observable<any> {
    return this.http.get("http://localhost:8765/api/musee/commentaires/"+1 ,{headers: {"Content-Type":"application/json; charset=UTF-8"}});
  }
  
  


 enroll(id:string,comment:comments): Observable<any> {
  return this.http.post(API_URL+"/"+id  ,comment,
  {headers: {"Content-Type":"application/json; charset=UTF-8"}});

 }


 commentaire(id:string,comment:comments): Observable<any> {
  return this.http.get("http://localhost:8765/api/course/test"  ,
  {headers: {"Content-Type":"application/json; charset=UTF-8"}});

 }



createExposition(comment: comments): Observable<any> {
  alert("service lancer");
  return this.http.post("http://localhost:8765/api/course/commentaires/"+1, JSON.stringify(comment),{headers: {"Content-Type":"application/json; charset=UTF-8"}});
}


  constructor(private http: HttpClient) { }



}
