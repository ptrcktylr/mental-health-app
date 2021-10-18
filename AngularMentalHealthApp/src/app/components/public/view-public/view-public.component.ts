import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CookieService } from 'ngx-cookie-service';
import { PatientService } from 'src/app/services/patients/patient.service';
import { ProfessionalService } from 'src/app/services/professional/professional.service';

@Component({
  selector: 'app-view-public',
  templateUrl: './view-public.component.html',
  styleUrls: ['./view-public.component.css']
})
export class ViewPublicComponent implements OnInit {

  //proxy entries
  entryArray: any[] = [];
  allEntryArray:any [] = [];

  constructor(private cookie:CookieService, private route:Router,private patS:PatientService,private proS:ProfessionalService) { }

  ngOnInit(): void {


    //check if patient cookie exists
    if(!(this.cookie.check('username') && this.cookie.check('accountType'))){
      this.route.navigate(['/login']);
    }

    this.patS.getPublicEntries().subscribe(
      (allEntries:any)=>{
        this.allEntryArray = allEntries;
        let x = this.allEntryArray.sort((a:any, b:any) => (a.datePosted > b.datePosted) ? 1 : -1)
        console.log(x);
        this.allEntryArray.reverse();
        this.entryArray = this.allEntryArray;
        console.log(this.entryArray);
      },
      ()=>{
        console.log("No information");
      }
    );

  }
}
