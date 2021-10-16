import { ɵparseCookieValue } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CookieService } from 'ngx-cookie-service';

@Component({
  selector: 'app-professional-nav',
  templateUrl: './professional-nav.component.html',
  styleUrls: ['./professional-nav.component.css']
})
export class ProfessionalNavComponent implements OnInit {

  constructor(private route:Router,private cookie:CookieService) { }

  ngOnInit(): void {
  }

  logout():void{
    this.cookie.deleteAll();
    this.route.navigate(['/login']);
  }

}
