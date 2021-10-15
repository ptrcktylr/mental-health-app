import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router'
import { Entry } from 'src/app/models/entry';
import { Sentiment } from 'src/app/models/sentiment';


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
  public newPost:any;
  
//reply properties
  public newReply:any = {};
  public replyBody:String = "";

  //ghost properties
  public sub: any;
  entry1 = {entryId:1, header: "First", body: "One of the best things you can do is care for yourself. I get that some people believe they are the center of the universe and that the earth revolves around them. But sometimes, we are right.\n\nWe good ways to get things done. And most of the time, it doesn’t include waking up at 5 am. Our methods are often unique. For example, I saw someone put a paper clip on the end of a roll of tape. I immediately felt as powerful as an atom.", tags: "me", sentimentScore: 69, publicPost: true };
  reply1 = {datePosted:"10/15/2021", author:{username:"Mr.Funsocks", isProfessional:true}, body:"Wow! Impressive..."}
  reply2 = {datePosted:"10/16/2021", author:{username:"Peter", isProfessional:false}, body:"Interesting post you have here. I disagree with your opinions on Banana matters."}
  replyArray = [this.reply1, this.reply2];
  
  
  constructor(private route: ActivatedRoute) { }

  ngOnInit(): void {

    //Add security features to go back to /patient/history if returned entry is null

    this.sub = this.route.params.subscribe((params:any) => {
      this.entryId = params['id'];
      });
    console.log(this.entryId);

    //replace with HTTP client request
    this.newPost = new Entry(1,1,"", "","", 0,false,"");
    this.newPost.title = this.entry1.header;
    this.newPost.body = this.entry1.body;
    this.newPost.tags = this.entry1.tags;
    this.newPost.sentiment = this.entry1.sentimentScore;
    this.newPost.datePosted = "10/30/2020";
    this.newPost.author = 1;

    this.getEntryInfo();
  }

  ngOnDestroy() {
    this.sub.unsubscribe();
  }

  getEntryInfo(){

    this.headerInput = this.newPost.header;
    this.bodyInput = this.newPost.body;
    this.tags = this.newPost.tags;
    this.sentimentScore = this.newPost.sentiment;
    this.author = this.newPost.author;
    this.date = this.newPost.datePosted;

  }

  submitReply(){
    this.newReply.body = this.replyBody;
    console.log(this.newReply)
  }



}
