import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-professional-my-patients',
  templateUrl: './professional-my-patients.component.html',
  styleUrls: ['./professional-my-patients.component.css']
})
export class ProfessionalMyPatientsComponent implements OnInit {

  patients = [
    {
      id: 1,
      fname: "John",
      lname: "Doe",
      lastMood: "Positive"
    },
    {
      id: 2,
      fname: "Jane",
      lname: "Smith",
      lastMood: "Negative"
    },
    {
      id: 3,
      fname: "James",
      lname: "Johnson",
      lastMood: "Neutral"
    },
    {
      id: 4,
      fname: "Jennifer",
      lname: "Lopez",
      lastMood: "Positive"
    }
  ]
  constructor(private route:Router) { }

  changeToPatient(id:number){
    this.route.navigate(['/professional/patient-history/'+id]);
  }

  ngOnInit(): void {
  }

}
