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
  allEntryArray:any[] = [];
    //fields for static tag helper
  public tag = "";
  public introTag = "introduction";
  public coroTag = "coronavirus";
  public helpTag = "healthhelp";
  public experienceTag = "healthexperience";


  constructor(private cookie:CookieService, private route:Router,private patS:PatientService,private proS:ProfessionalService) { }

  ngOnInit(): void {


    //check if patient cookie exists
    if(!(this.cookie.check('username') && this.cookie.check('accountType'))){
      this.route.navigate(['/login']);
    }

    this.proS.getPublicEntries().subscribe(
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


  //Changing tags
  changeIntroTag(){
    this.tag = this.introTag;
  }
  changeCoroTag(){
    this.tag = this.coroTag;
  }
  changeExpTag(){
    this.tag = this.experienceTag;
  }
  changeHelpTag(){
    this.tag = this.helpTag;
  }

  filterByTag(){
    if(this.tag ==""){
      this.entryArray = this.allEntryArray;
      return;
    }
    let tempArray: any[] = [];
    this.allEntryArray.forEach(element => {
      console.log(element.tag);
      if(element.tag == this.tag){
        tempArray.push(element);
      }
    });
    console.log(tempArray);
    this.entryArray = tempArray;
  }
  

}
