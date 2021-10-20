import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router'; 
import { CookieService } from 'ngx-cookie-service';
import { PortalService } from 'src/app/services/portal/portal.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  public username:String = "";
  public password:String = "";
  public error:String = "";

  constructor(private route:Router, private cookie:CookieService, private portalS:PortalService) { }

  resetError(): void{
    this.error = "";
  }

  login(): void{
    /*
    // code to make it functional until I have connection to the db
    if(this.username == "username" && this.password == "password"){
      console.log(this.cookie.get("username"),{expires:5});
      this.cookie.set("username",this.username.toString());
      this.cookie.set("accountType", 'patient');
      this.route.navigate(['/patient/history']);
    }
    else if(this.username == "professional" && this.password == "password"){
      this.cookie.set("username",this.username.toString(),{expires:5});
      this.route.navigate(['professional/my-patients']);
      this.cookie.set("accountType", 'professional');
    }
    else{
      this.error = "Username or Password not recognized.";
    }*/

    this.portalS.patientLogin(this.username,this.password).subscribe(
      (user:any)=>{
        console.log(user);
        this.route.navigate(['/patient/history']);
        
      },
      ()=>{
        //this.error = "Username or Password not recognized.";
        console.log("Not a patient")
        this.portalS.professionalLogin(this.username,this.password).subscribe(
          (user:any)=>{
            console.log(user);
            this.route.navigate(['/professional/my-patients']);
          },
          ()=>{
            this.error = "Username or Password not recognized.";
            console.log("Wrong Info")
          }
        );
      }
    );
/*
    this.portalS.professionalLogin(this.username,this.password).subscribe(
      (user:any)=>{
        console.log(user);
        this.route.navigate(['/professional/my-patients']);
      },
      ()=>{
        this.error = "Username or Password not recognized.";
        console.log("Wrong Info")
      }
    );*/
  }

  ngOnInit(): void {
  }

}
