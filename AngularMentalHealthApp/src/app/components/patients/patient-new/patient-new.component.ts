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
  public publicPost:boolean = false;
  public message:String = "";
  //newPost will proxy a Json class to POST send
  public newPost:any;


  constructor(private das:DeepaiApiService, private sps: SentpackService) { }

  ngOnInit(): void {
    this.newPost = new Sentiment(1,"","", "", 0,false);
  }

  addEntry():void{
    //Check if fields are filled
    if(this.validatePost() == false){
      this.message = "Body and Text fields must be filled"
      return;
    }

    //Fill the header and body fields

    this.newPost.author = 1;
    //this.newPost.header = this.headerInput;

    this.newPost.header = this.headerInput;
    this.newPost.body = this.bodyInput;
    this.newPost.tag = this.tags.toLowerCase();
    this.newPost.publicPost = this.tags;

    //Fill the sentimentScore field
    //this.getSentPackAnalysis();
    this.getDeepapiAnalysis();

    //Renew the sections of the Page
    this.renewPost();
    console.log(this.newPost);
  }

  validatePost():boolean{
    if(this.headerInput === ""){
      return false;
    }
    if(this.bodyInput === ""){
      return false;
    }
    return true;
  }

  renewPost():void{
    this.message = "Success Post";
    this.headerInput = "";
    this.bodyInput = "";
    this.tags = "";
    this.publicPost = false;
  }

  changePrivacy():void{
    this.publicPost = !this.publicPost;
  }

  //Sentimental analysis with DeepApi
  async getDeepapiAnalysis():Promise<void>{
    let scoreJson:any = await this.das.trySentimentAnalysis(this.bodyInput);
    //console.log(scoreJson);

    let total = 0;
    for(let i = 0; i < scoreJson.output.length; i++){
      //console.log(scoreJson.output[i]);
      if(scoreJson.output[i].includes("Positive")){
        total += 1;
      }
      if(scoreJson.output[i].includes("Negative")){
        total += .01;
      }
      if(scoreJson.output[i] === "Neutral"){
        total += .5;
      }
    }
    //console.log(total);
    this.newPost.sentimentScore = total/scoreJson.output.length;
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