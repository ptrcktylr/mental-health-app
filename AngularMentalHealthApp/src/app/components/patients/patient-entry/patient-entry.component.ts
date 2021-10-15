import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router'
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
  
  //ghost properties
  public sub: any;

  constructor(private route: ActivatedRoute) { }

  ngOnInit(): void {

    //Add security features to go back to /patient/history if returned entry is null

    this.sub = this.route.params.subscribe((params:any) => {
      this.entryId = params['id'];
      });
    console.log(this.entryId);

    //replace with HTTP client request
    this.newPost = new Sentiment(1,"","", "", 0,false,"");
    this.newPost.header = "My Title";
    this.newPost.body = "";
    this.newPost.tags = "nsfw";
    this.newPost.sentimentScore = .45;
    this.newPost.date = "10/30/2020";
    this.author = "Paul Chang"

    this.getEntryInfo();
  }

  getEntryInfo(){

    this.author = "Success Post";
    this.headerInput = this.newPost.header;
    this.bodyInput = this.newPost.body;
    this.tags = this.newPost.tag;
    this.sentimentScore = this.newPost.sentimentScore;
    this.author = this.newPost.author;

  }

}
