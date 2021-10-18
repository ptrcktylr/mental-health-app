import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CookieService } from 'ngx-cookie-service';
import { PatientService } from 'src/app/services/patients/patient.service';

@Component({
  selector: 'app-patient-history',
  templateUrl: './patient-history.component.html',
  styleUrls: ['./patient-history.component.css']
})
export class PatientHistoryComponent implements OnInit {

//proxy entries
  entryArray: any[] = [];

  constructor(private cookie:CookieService, private route:Router, private patS:PatientService) { }

  ngOnInit(): void {

  //check if patient cookie exists
    if(!(this.cookie.check('username') && this.cookie.get('accountType') == 'patient')){
      this.route.navigate(['/login']);
    }

    this.patS.getAllMyEntries().subscribe(
      (allEntries:any)=>{
        this.entryArray = allEntries;
        console.log(this.entryArray);
      },
      ()=>{
        console.log("No information")
      }
    );
  }



}
