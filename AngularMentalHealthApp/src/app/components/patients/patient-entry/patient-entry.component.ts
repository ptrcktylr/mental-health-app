import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router'
import { CookieService } from 'ngx-cookie-service';
import { Entry } from 'src/app/models/entry';
import { Sentiment } from 'src/app/models/sentiment';
import {Location} from '@angular/common';
import { PatientService } from 'src/app/services/patients/patient.service';


@Component({
  selector: 'app-patient-entry',
  templateUrl: './patient-entry.component.html',
  styleUrls: ['./patient-entry.component.css']
})
export class PatientEntryComponent implements OnInit {

  public entryId = 0;
  public headerInput:String = "";
  public bodyInput:String = "";
  public tags:String = "";
  public sentimentScore:number = 0;
  public author:String = "";
  public date:String = "";

  public message:String = "";
  //newPost will proxy a Json class to POST send
  public newPost:any = {};
  
//reply properties
  public newReply:any = {};
  public replyBody:String = "";

  //ghost properties
  public sub: any;
  replyArray:any = [];
  
  
  constructor(private aRoute: ActivatedRoute, private cookie:CookieService, private route:Router, private _location: Location,private patS:PatientService) { }

  ngOnInit(): void {

    this.sub = this.aRoute.params.subscribe((params:any) => {
      this.entryId = params['id'];
        this.patS.getEntry(this.entryId).subscribe(
          (allEntries:any)=>{
            this.newPost = allEntries;
            this.getEntryInfo();
            console.log(this.newPost);
          },
          ()=>{
            console.log("No information")
          }
        );
      });
  }

  ngOnDestroy() {
    this.sub.unsubscribe();
  }

  getEntryInfo(){

    this.headerInput = this.newPost.title;
    this.bodyInput = this.newPost.body;
    this.tags = this.newPost.tag;
    this.sentimentScore = this.newPost.sentiment;
    this.author = this.newPost.patient.username;
    this.date = this.newPost.datePosted.substring(0,10);
    this.replyArray = this.newPost.replies;
    console.log(this.replyArray);
  }

  submitReply(){
    this.patS.replyEntry(this.entryId, this.replyBody).subscribe(
      (reply:any)=>{
        console.log("reply sent");
      },
      ()=>{
        console.log("No information");
      }
    );
  }

  backClicked() {
    this._location.back();
  }


}
