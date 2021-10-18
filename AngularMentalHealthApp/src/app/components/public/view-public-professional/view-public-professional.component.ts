import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CookieService } from 'ngx-cookie-service';
import { PatientService } from 'src/app/services/patients/patient.service';
import { ProfessionalService } from 'src/app/services/professional/professional.service';

@Component({
  selector: 'app-view-public-professional',
  templateUrl: './view-public-professional.component.html',
  styleUrls: ['./view-public-professional.component.css']
})
export class ViewPublicProfessionalComponent implements OnInit {

  entryArray: any[] = [];

  constructor(private cookie:CookieService, private route:Router,private patS:PatientService,private proS:ProfessionalService) { }

  ngOnInit(): void {


    //check if patient cookie exists
    if(!(this.cookie.check('username') && this.cookie.check('accountType'))){
      this.route.navigate(['/login']);
    }

    this.proS.getPublicEntries().subscribe(
      (allEntries:any)=>{
        this.entryArray = allEntries;
        console.log(this.entryArray);
      },
      ()=>{
        console.log("No information");
      }
    );
  }

}
