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
  }/*
  getAllMyEntries():Observable<any>{
    return this.http.get("http://localhost:8080/patient/entries",{withCredentials: true}) as Observable<any>;
  }
  getAllMyEntries():Observable<any>{
    return this.http.get("http://localhost:8080/patient/entries",{withCredentials: true}) as Observable<any>;
  }*/


}
