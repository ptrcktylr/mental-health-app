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
    this.newPost = new Sentiment(1,"","", "", 0);
  }

  addEntry():void{
    //Check if fields are filled

    //Fill the header and body fields
    this.newPost = new Sentiment(1,this.headerInput,this.bodyInput, this.tags, 0);

    //Fill the sentimentScore field
    //this.getSentPackAnalysis();
    this.getDeepapiAnalysis();

    //Fill the tag section
    
    console.log(this.newPost);
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
    console.log(total);
    this.newPost.sentimentScore = total/scoreJson.output.length;
  }

  //Sentimental analysis with SentPack service
  getSentPackAnalysis():void{

    //we need set the score value here
    let scoreJson = this.sps.trySentPack(this.bodyInput);

    //console.log(scoreJson);

    let total = scoreJson.tokens.length * 10;
    let score = scoreJson.tokens.length * 5 + scoreJson.score; 
    this.newPost.sentimentScore = score/total;
  }

}
//It has been a rough day at work.