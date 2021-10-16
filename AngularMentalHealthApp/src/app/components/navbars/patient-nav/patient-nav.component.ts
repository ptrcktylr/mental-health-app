import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CookieService } from 'ngx-cookie-service';

@Component({
  selector: 'app-patient-nav',
  templateUrl: './patient-nav.component.html',
  styleUrls: ['./patient-nav.component.css']
})
export class PatientNavComponent implements OnInit {

  constructor(private route:Router,private cookie:CookieService) { }

  ngOnInit(): void {
  }

  logout():void{
    this.cookie.deleteAll();

    this.route.navigate(['/login']);
  }

}
