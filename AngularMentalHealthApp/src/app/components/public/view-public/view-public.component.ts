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

  constructor(private cookie:CookieService, private route:Router,private patS:PatientService,private proS:ProfessionalService) { }

  ngOnInit(): void {


    //check if patient cookie exists
    if(!(this.cookie.check('username') && this.cookie.check('accountType'))){
      this.route.navigate(['/login']);
    }

    this.patS.getPublicEntries().subscribe(
      (allEntries:any)=>{
        this.entryArray = allEntries;
        let x = this.entryArray.sort((a:any, b:any) => (a.datePosted > b.datePosted) ? 1 : -1)
        console.log(x);
        this.entryArray.reverse();
        console.log(this.entryArray);
      },
      ()=>{
        console.log("No information");
      }
    );

  }
}
