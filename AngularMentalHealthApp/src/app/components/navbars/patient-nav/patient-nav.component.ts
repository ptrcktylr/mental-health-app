import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CookieService } from 'ngx-cookie-service';
import { PortalService } from 'src/app/services/portal/portal.service';

@Component({
  selector: 'app-patient-nav',
  templateUrl: './patient-nav.component.html',
  styleUrls: ['./patient-nav.component.css']
})
export class PatientNavComponent implements OnInit {

  constructor(private route:Router,private cookie:CookieService,private portalS:PortalService) { }

  ngOnInit(): void {
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
