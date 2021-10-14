import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';

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

  constructor(private route:Router) { }

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
      this.route.navigate(['/login']);
    }
    else{
      this.error="Please complete all fields."
    }
  }

  resetError(): void{
    this.error = "";
  }

  ngOnInit(): void {
  }

}
