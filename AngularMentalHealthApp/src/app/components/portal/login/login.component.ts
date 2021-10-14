import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router'; 

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  public username:String = "";
  public password:String = "";
  public error:String = "";

  constructor(private route:Router) { }

  resetError(): void{
    this.error = "";
  }

  login(): void{
    // code to make it functional until I have connection to the db
    if(this.username == "username" && this.password == "password"){
      this.route.navigate(['/patient-history']);
    }
    else if(this.username == "professional" && this.password == "password"){
      this.route.navigate(['/my-patients']);
    }
    else{
      this.error = "Username or Password not recognized.";
    }

    //actual code will look more like this:
    /* 
    user = getUserByKeys(username,password)
    if(user != null){
      if(user.role == patient){
        this.route.navigate(['/patient-history']);
      }
      else if(user.role == professional){
        this.route.navigate(['/professional-all-patients']);
      }
    }
    else{
      error = "Username or password not recognized."
    }
    */

  }

  ngOnInit(): void {
  }

}
