import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-professional-all-patients',
  templateUrl: './professional-all-patients.component.html',
  styleUrls: ['./professional-all-patients.component.css']
})
export class ProfessionalAllPatientsComponent implements OnInit {

  patients = [
    {
      id: 1,
      fname: "John",
      lname: "Doe",
    },
    {
      id: 2,
      fname: "Jane",
      lname: "Smith",
    },
    {
      id: 3,
      fname: "James",
      lname: "Johnson",
    }
  ]

  constructor(private route:Router) { }

  addPatient(id:number){
    console.log(id);
    this.route.navigate(['/professional/my-patients']);
  }

  ngOnInit(): void {
  }

}
