import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CookieService } from 'ngx-cookie-service';
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
  public tag:String = "";
  public publicPost:boolean = false;
  public message:String = "";
  //newPost will proxy a Json class to POST send
  public newPost:any = {};

  //fields for static tag helper
  public introTag = "introduction";
  public coroTag = "coronavirus";
  public helpTag = "healthhelp";
  public experienceTag = "healthexperience";


  constructor(private das:DeepaiApiService, private sps: SentpackService, private cookie:CookieService, private route:Router,private patS:PatientService) { }

  ngOnInit(): void {
  }

  async addEntry():Promise<void>{

    //Check if fields are filled
    if(this.validatePost() == false){
      this.message = "Body and Text fields must be filled"
      return;
    }

    //this.newPost.header = this.headerInput;

    this.newPost.title = this.headerInput;
    this.newPost.body = this.bodyInput;
    this.newPost.tag = this.tag.toLowerCase();
    this.newPost.public = this.publicPost;

    //Fill the sentimentScore field
    //this.getSentPackAnalysis();
    await this.getDeepapiAnalysis();

    //Renew the sections of the Page
    console.log(this.newPost.sentiment);

    this.patS.postEntry(this.newPost).subscribe(
      (allEntries:any)=>{
        console.log("Sent info");
        this.renewPost();
        console.log(this.newPost);
        this.route.navigate(['/patient/history']);
      },
      ()=>{
        console.log("No information")
      }
    );


    
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
    this.message = "Successful Post";
    this.headerInput = "";
    this.bodyInput = "";
    this.tag = "";
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
      
      if(scoreJson.output[i].includes("Verypositive")){
        total += 1;
      }
      if(scoreJson.output[i].includes("Positive")){
        total += 0.85;
      }
      if(scoreJson.output[i].includes("Negative")){
        total += .15;
      }
      if(scoreJson.output[i].includes("Verynegative")){
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


  //Changing tags
  changeIntroTag(){
    this.tag = this.introTag;
  }
  changeCoroTag(){
    this.tag = this.coroTag;
  }
  changeExpTag(){
    this.tag = this.experienceTag;
  }

  changeHelpTag(){
    this.tag = this.helpTag;
  }


}