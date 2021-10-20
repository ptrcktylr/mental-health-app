import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ProfessionalService } from 'src/app/services/professional/professional.service';

@Component({
  selector: 'app-professional-all-patients',
  templateUrl: './professional-all-patients.component.html',
  styleUrls: ['./professional-all-patients.component.css']
})
export class ProfessionalAllPatientsComponent implements OnInit {

  patients:any = []

  constructor(private route:Router,private proS:ProfessionalService) { }

  addPatient(id:number){
    this.proS.addPatient(id).subscribe(
      (addPatients:any)=>{
        console.log("Added Successfully");
        this.route.navigate(['/professional/my-patients']);
      },
      ()=>{
        console.log("No information");
      }
    );
  }

  ngOnInit(): void {
    this.proS.getPatients().subscribe(
      (patientsList:any)=>{
        this.proS.getMyPatients().subscribe(
          (myPatients:any)=>{
            this.patients = this.notMyPatients(myPatients,patientsList);
          },
          ()=>{
            console.log("No information");
          }
        );
      },
      ()=>{
        console.log("No information");
      }
    );
  }

  notMyPatients(myArray:any, allArray:any): any{
    for(let item of myArray){
      allArray.forEach((value:any,index:number)=>{
        if(value.username==item.username) allArray.splice(index,1);
      });
    }
    return allArray;
  }

}
