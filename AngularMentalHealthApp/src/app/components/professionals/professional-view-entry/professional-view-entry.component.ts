import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { CookieService } from 'ngx-cookie-service';
import { ProfessionalService } from 'src/app/services/professional/professional.service';
import {Location} from '@angular/common';

@Component({
  selector: 'app-professional-view-entry',
  templateUrl: './professional-view-entry.component.html',
  styleUrls: ['./professional-view-entry.component.css']
})
export class ProfessionalViewEntryComponent implements OnInit {

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
  entry1 = {entryId:1, header: "First", body: "One of the best things you can do is care for yourself. I get that some people believe they are the center of the universe and that the earth revolves around them. But sometimes, we are right.\n\nWe good ways to get things done. And most of the time, it doesn’t include waking up at 5 am. Our methods are often unique. For example, I saw someone put a paper clip on the end of a roll of tape. I immediately felt as powerful as an atom.", tags: "me", sentimentScore: 69, publicPost: true };
  replyArray:any = [];
  
  
  constructor(private aRoute: ActivatedRoute, private cookie:CookieService, private route:Router, private _location: Location,private proS:ProfessionalService) { }

  ngOnInit(): void {

    if(!(this.cookie.check('username') && this.cookie.check('accountType'))){
      this.route.navigate(['/login']);
    }

    //Add security features to go back to /patient/history if returned entry is null

    this.sub = this.aRoute.params.subscribe((params:any) => {
      this.entryId = params['id'];
        this.proS.getEntry(this.entryId).subscribe(
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
    this.replyArray = this.newPost.replies.reverse();
    console.log(this.replyArray);
  }

  submitReply(){
    if(this.replyBody == ""){
      //this.message = "Can not reply with an empty body";
      return;
    }

    this.proS.replyEntry(this.entryId, this.replyBody).subscribe(
      (reply:any)=>{
        console.log("reply sent");
        location.reload();
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
