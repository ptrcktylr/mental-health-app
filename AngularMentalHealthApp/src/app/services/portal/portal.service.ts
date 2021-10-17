import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PortalService {

  constructor(private http:HttpClient) { }

  patientLogin(username:String, password:String):Observable<any>{
    //console.log({username:username, password:password});
    return this.http.post("http://localhost:8080/patient/login",{username:username, password:password}) as Observable<any>;
  }
}
