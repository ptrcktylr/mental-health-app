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
    return this.http.post("http://localhost:8080/patient/login",{username:username, password:password},{withCredentials: true}) as Observable<any>;
  }

  professionalLogin(username:String, password:String):Observable<any>{
    //console.log({username:username, password:password});
    return this.http.post("http://localhost:8080/professional/login",{username:username, password:password},{withCredentials: true}) as Observable<any>;
  }

  patientRegister(bodyR:any):Observable<any>{
    //console.log({username:username, password:password});
    return this.http.post("http://localhost:8080/professional/login",bodyR,{withCredentials: true}) as Observable<any>;
  }

  professionalRegister(bodyR:any):Observable<any>{
    //console.log({username:username, password:password});
    return this.http.post("http://localhost:8080/professional/login",bodyR,{withCredentials: true}) as Observable<any>;
  }


}
