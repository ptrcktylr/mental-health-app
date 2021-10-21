import { ɵparseCookieValue } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CookieService } from 'ngx-cookie-service';
import { PatientService } from 'src/app/services/patients/patient.service';
import { PortalService } from 'src/app/services/portal/portal.service';
import { ProfessionalService } from 'src/app/services/professional/professional.service';

@Component({
  selector: 'app-professional-nav',
  templateUrl: './professional-nav.component.html',
  styleUrls: ['./professional-nav.component.css']
})
export class ProfessionalNavComponent implements OnInit {

  name:String = "";
  showMenu:Boolean = false;

  constructor(private route:Router,private cookie:CookieService,private portalS:PortalService, private proS:ProfessionalService) { }

  ngOnInit(): void {
    this.proS.getMyInfo().subscribe(
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