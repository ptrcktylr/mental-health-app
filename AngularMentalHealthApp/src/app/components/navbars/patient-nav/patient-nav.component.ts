import { Component, HostListener, OnInit } from '@angular/core';
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

  name:String = "";
  professional:String = "";
  showMenu:Boolean = false;

  constructor(private route:Router,private cookie:CookieService,private portalS:PortalService, private patS:PatientService) { }

  ngOnInit(): void {
    this.patS.getMyProfessional().subscribe(
      (pro:any)=>{
        console.log(pro);
      },
      ()=>{
        console.log("Professional No information")
      }
    );
    this.patS.getMyInfo().subscribe(
      (user:any)=>{
        this.name = user.firstName + " " + user.lastName;
      },
      ()=>{
        console.log("No information")
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
