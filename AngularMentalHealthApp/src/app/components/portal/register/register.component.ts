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

  userArray:any[] = [];
  next(): void{
    this.portal.getUsernamesEmails().subscribe(
      (allUsers:any)=>{
        this.userArray = allUsers;
        this.userArray.reverse();
        let found:Boolean = false;
        for(let user of this.userArray[1]){
          console.log("user:" + user);
          if(user == this.username){
            found = true;
            break;
          }
        }
        if(this.username!="" && this.password!="" && !found){ //&& this.username not in db already
          this.show=false;
        }
        else if(found){
          this.error="Username is already in use."
        }
        else{
          this.error="Please Enter a Username and Password."
        }
      },
      ()=>{
        console.log("No information")
      }
    );
  }

  make(): void{
    if(this.fname!="" && this.lname!="" && this.email!=""){
      //check that email is unique
      // if email isn't unique, this.error = "Email already in use."
      //code to create new user, then go back to login
      let found:Boolean = false;
      for(let u of this.userArray[0]){
        console.log("user:" + u);
        if(u == this.email){
          found = true;
          break;
        }
      }
      if(!found){
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
          this.portal.professionalRegister(proUser).subscribe(
            (professional:any)=>{
              this.route.navigate(['/login']);
            },
            ()=>{
              this.error="Something went wrong."
            }
            
          );
        }
        else{
          this.portal.patientRegister(user).subscribe(
            (patient:any)=>{
              this.route.navigate(['/login']);
            },
            ()=>{
              this.error="Something went wrong."
            }
          );
        }
      }
      else{
        this.error="Email is already in use."
      }
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
