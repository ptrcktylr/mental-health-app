import { Component, OnInit } from '@angular/core';
import { Sentiment } from 'src/app/models/sentiment';
import { DeepaiApiService } from 'src/app/services/deepai/deepai-api.service';
import { SentpackService } from 'src/app/services/sentpack/sentpack.service';

@Component({
  selector: 'app-patient-new',
  templateUrl: './patient-new.component.html',
  styleUrls: ['./patient-new.component.css']
})

export class PatientNewComponent implements OnInit {

  public headerInput:String = "";
  public bodyInput:String = "";
  public tags:String = "";
  //newPost will proxy a Json class to POST send
  public newPost:any;


  constructor(private das:DeepaiApiService, private sps: SentpackService) { }

  ngOnInit(): void {
    this.newPost = new Sentiment(1,this.headerInput,this.bodyInput, this.tags, 0);
  }

  addEntry():void{
    this.newPost = new Sentiment(1,this.headerInput,this.bodyInput, this.tags, 0);
    console.log(this.newPost);
    this.getSentPackAnalysis();
    console.log(this.newPost);
  }


  //Sentimental analysis with DeepApi
  getDeepapiAnalysis():void{
    let score = this.das.trySentimentAnalysis(this.bodyInput);
  }

  //Sentimental analysis with SentPack service
  getSentPackAnalysis():void{

    //we need set the score value here
    let scoreJson = this.sps.trySentPack(this.bodyInput);
    let score = scoreJson.comparative;
    console.log(score);
    this.newPost.sentimentScore = score;
  }

}
//It has been a rough day at work.