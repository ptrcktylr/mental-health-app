import { ThisReceiver } from '@angular/compiler';
import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';
import { PortalService } from 'src/app/services/portal/portal.service';
import { ProfessionalService } from 'src/app/services/professional/professional.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  public username:String = "";
  public password:String = "";
  public fname:String = "";
  public lname:String="";
  public email:String="";
  public error:String = "";
  public show:Boolean = true;
  public isP:Boolean = false;

  constructor(private route:Router, private portal:PortalService) { }

  next(): void{
    if(this.username!="" && this.password!=""){ //&& this.username not in db already
      this.show=false;
    }
    else{
      this.error="Please Enter a Username and Password."
    }
  }

  make(): void{
    if(this.fname!="" && this.lname!="" && this.email!=""){
      //check that email is unique
      // if email isn't unique, this.error = "Email already in use."
      //code to create new user, then go back to login
      let proUser ={
        email:this.email,
        firstName: this.fname, 
        lastName: this.lname,
        password: this.password,
        username: this.username
      }
      let user ={
        username: this.username,
        password: this.password,
        firstName: this.fname,
        lastName: this.lname,
        email: this.email
      }
      if(this.isP){
        this.portal.professionalRegister(proUser).subscribe();
      }
      else{
        this.portal.patientRegister(user).subscribe();
      }
      this.route.navigate(['/login']);
    }
    else{
      this.error="Please complete all fields."
    }
  }

  resetError(): void{
    this.error = "";
  }

  isProfessional(): void{
    this.isP = !this.isP;
  }

  ngOnInit(): void {
  }

}
