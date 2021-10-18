import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ProfessionalService } from 'src/app/services/professional/professional.service';

@Component({
  selector: 'app-professional-patient-history',
  templateUrl: './professional-patient-history.component.html',
  styleUrls: ['./professional-patient-history.component.css']
})
export class ProfessionalPatientHistoryComponent implements OnInit {

  entryArray:any = [];
  sub: any;
  patientId: any;

  constructor(private route:Router,private aRoute: ActivatedRoute,private proS:ProfessionalService) { }

  ngOnInit(): void {

    this.sub = this.aRoute.params.subscribe((params:any) => {
      this.patientId = params['id'];
        this.proS.getPatientEntries(this.patientId).subscribe(
          (patEntries:any)=>{
            this.entryArray = patEntries;
            this.entryArray.reverse();
            console.log(patEntries);
          },
          ()=>{
            console.log("No information")
          }
        );
      });
  }

  goToProgress(){
    this.route.navigate(['/professional/progress/' + this.patientId]);
  }

  goToEntry(id:number){
    this.route.navigate(['/professional/entry/' + id]);
  }

}
