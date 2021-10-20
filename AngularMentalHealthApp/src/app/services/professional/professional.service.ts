import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ProfessionalService {



  constructor(private http:HttpClient) { }

  getPublicEntries():Observable<any>{
    return this.http.get("http://localhost:8080/professional/public/entries",{withCredentials: true}) as Observable<any>;
  }
  getEntry(id:number):Observable<any>{
    return this.http.get("http://localhost:8080/professional/entry/" + id,{withCredentials: true}) as Observable<any>;
  }
  replyEntry(id:number, body:any):Observable<any>{
    return this.http.post("http://localhost:8080/professional/entry/" + id + "/reply",{body:body},{withCredentials: true}) as Observable<any>;
  }
  getPatients():Observable<any>{
    return this.http.get("http://localhost:8080/professional/patients",{withCredentials: true}) as Observable<any>;
  }
  getMyPatients():Observable<any>{
    return this.http.get("http://localhost:8080/professional/patients/assigned",{withCredentials: true}) as Observable<any>;
  }
  getUnassignedPatients():Observable<any>{
    return this.http.get("http://localhost:8080/professional/patients/unassigned",{withCredentials: true}) as Observable<any>;
  }
  getPatientEntries(id:number):Observable<any>{
    return this.http.get("http://localhost:8080/professional/patient/" + id + "/entries",{withCredentials: true}) as Observable<any>;
  }
  addPatient(id:number):Observable<any>{
    return this.http.post("http://localhost:8080/professional/patient/" + id + "/add",{},{withCredentials: true}) as Observable<any>;
  }
  removePatient(id:number){
    return this.http.post("http://localhost:8080/professional/patient/" + id + "/remove",{},{withCredentials: true}) as Observable<any>;
  }
  getMyInfo():Observable<any>{
    return this.http.get("http://localhost:8080/professional/my-info",{withCredentials: true}) as Observable<any>;
  }

}
