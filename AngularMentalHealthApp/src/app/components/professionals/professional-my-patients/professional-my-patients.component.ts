import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ProfessionalService } from 'src/app/services/professional/professional.service';

@Component({
  selector: 'app-professional-my-patients',
  templateUrl: './professional-my-patients.component.html',
  styleUrls: ['./professional-my-patients.component.css']
})
export class ProfessionalMyPatientsComponent implements OnInit {

  patients:any = []
  constructor(private route:Router, private proS:ProfessionalService) { }

  changeToPatient(id:number){
    this.route.navigate(['/professional/patient-history/', id]);
  }

  ngOnInit(): void {


    this.proS.getMyPatients().subscribe(
      (myPatients:any)=>{
        this.patients = myPatients;
        console.log(this.patients);
      },
      ()=>{
        console.log("No information");
      }
    );
  }

}
