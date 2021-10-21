import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PatientService {

  constructor(private http:HttpClient) { }

  getAllMyEntries():Observable<any>{
    return this.http.get("http://localhost:8080/patient/entries",{withCredentials: true}) as Observable<any>;
  }

  getEntry(id:number):Observable<any>{
    return this.http.get("http://localhost:8080/patient/entry/" + id,{withCredentials: true}) as Observable<any>;
  }
  replyEntry(id:number, body:any):Observable<any>{
    return this.http.post("http://localhost:8080/patient/entry/" + id + "/reply",{body:body},{withCredentials: true}) as Observable<any>;
  }
  
  getPublicEntries():Observable<any>{
    return this.http.get("http://localhost:8080/patient/public/entries",{withCredentials: true}) as Observable<any>;
  }

  postEntry(body:any):Observable<any>{
    return this.http.post("http://localhost:8080/patient/entry",body,{withCredentials: true}) as Observable<any>;
  }

  getMyInfo():Observable<any>{
    return this.http.get("http://localhost:8080/patient/my-info",{withCredentials: true}) as Observable<any>;
  }

  getMyProfessional():Observable<any>{
    return this.http.get("http://localhost:8080/patient/my-professional",{withCredentials: true}) as Observable<any>;
  }
}
