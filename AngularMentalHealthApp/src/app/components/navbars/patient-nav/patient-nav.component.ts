import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CookieService } from 'ngx-cookie-service';
import { PatientService } from 'src/app/services/patients/patient.service';
import { PortalService } from 'src/app/services/portal/portal.service';

@Component({
  selector: 'app-patient-nav',
  templateUrl: './patient-nav.component.html',
  styleUrls: ['./patient-nav.component.css']
})
export class PatientNavComponent implements OnInit {

  username:String = "get username"
  showMenu:Boolean = true;

  constructor(private route:Router,private cookie:CookieService,private portalS:PortalService, private patientS:PatientService) { }

  ngOnInit(): void {

    this.patientS.getMyInfo().subscribe(
      (myInfo:any)=>{
        this.username = myInfo.username;
      },
      ()=>{
        console.log("No user information");
      }
    );

  }

  logout():void{
    this.portalS.logout().subscribe(
      (logInfo:any)=>{
        console.log(logInfo);
        this.route.navigate(['/login']);
      },
      ()=>{
        console.log("Can't logout?")
      }
    );
  }

}
