import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CookieService } from 'ngx-cookie-service';
import { Entry } from 'src/app/models/entry';
import { DeepaiApiService } from 'src/app/services/deepai/deepai-api.service';
import { PatientService } from 'src/app/services/patients/patient.service';
import { SentpackService } from 'src/app/services/sentpack/sentpack.service';

@Component({
  selector: 'app-patient-new',
  templateUrl: './patient-new.component.html',
  styleUrls: ['./patient-new.component.css']
})

export class PatientNewComponent implements OnInit {

  public entryId = 0;

  //fields of sentiment
  public headerInput:String = "";
  public bodyInput:String = "";
  public tags:String = "";
  public publicPost:boolean = false;

  public message:String = "";
  //newPost will proxy a Json class to POST send
  public newPost:any = {};


  constructor(private das:DeepaiApiService, private sps: SentpackService, private cookie:CookieService, private route:Router,private patS:PatientService) { }

  ngOnInit(): void {
    //check if patient cookie exists
    if(!(this.cookie.check('username') && this.cookie.get('accountType') == 'patient')){
      this.route.navigate(['/login']);
    }
    console.log(this.newPost);
  }

  async addEntry():Promise<void>{

    if(!(this.cookie.check('username') && this.cookie.get('accountType') == 'patient')){
      this.route.navigate(['/login']);
    }
    //Check if fields are filled
    if(this.validatePost() == false){
      this.message = "Body and Text fields must be filled"
      return;
    }

    //Fill the header and body fields

    this.newPost.author = 1;
    //this.newPost.header = this.headerInput;

    this.newPost.title = this.headerInput;
    this.newPost.body = this.bodyInput;
    this.newPost.tags = this.tags.toLowerCase();
    this.newPost.public = this.publicPost;

    //Fill the sentimentScore field
    //this.getSentPackAnalysis();
    await this.getDeepapiAnalysis();

    //Renew the sections of the Page
    console.log(this.newPost.sentiment);

    this.patS.postEntry(this.newPost).subscribe(
      (allEntries:any)=>{
        console.log("Sent info")
      },
      ()=>{
        console.log("No information")
      }
    );

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
    this.newPost.sentiment = Math.round(total/scoreJson.output.length * 100);
  }

  //Sentimental analysis with SentPack service
  getSentPackAnalysis():void{

    //we need set the score value here
    let scoreJson = this.sps.trySentPack(this.bodyInput);

    //console.log(scoreJson);

    let total = scoreJson.tokens.length * 10;
    let score = scoreJson.tokens.length * 5 + scoreJson.score; 
    this.newPost.sentiment = Math.round(score/total * 100);
  }

}
//It has been a rough day at work.