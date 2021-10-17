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
  entry1 = { entryId: 1, author: 1, title: "First", body: "Took time off to recuperate and recover my energy. Now back from paradise, rejuvenated and ready to take on the beast again. All eyes on the prize, one more trip to deliver the coup de grace to that other beast in the west. Once that's done, its just a matter of time and protocol until the next step is reached.\n", tags: "me", sentiment: 25.75, isPublic: false, datePosted: "" };
  entry2 = { entryId: 2, author: 1, title: "Second", body: "Don't give up on medicine. Even though you may take a lot of abuse, the money and hours are not that great, and the perceived public worth of the physician is ever-diminishing; it can still be a very rewarding profession. Nothing is more fulfilling than helping another human being out when they need it the most. A lot of people go out of their way to do stuff like that for free out of their own time. You get paid to do it, so count yourself lucky.", tags: "", sentiment: 50.39999999999999, isPublic: true, datePosted: "" };
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

  viewDetailEntry():void{

  }



}
