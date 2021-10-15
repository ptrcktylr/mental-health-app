import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-patient-history',
  templateUrl: './patient-history.component.html',
  styleUrls: ['./patient-history.component.css']
})
export class PatientHistoryComponent implements OnInit {

//proxy entries
  entry1 = {entryId:1, header: "First", body: "One of the best things you can do is care for yourself. I get that some people believe they are the center of the universe and that the earth revolves around them. But sometimes, we are right.\n\nWe good ways to get things done. And most of the time, it doesn’t include waking up at 5 am. Our methods are often unique. For example, I saw someone put a paper clip on the end of a roll of tape. I immediately felt as powerful as an atom.", tags: "me", sentimentScore: 69, publicPost: true };
  entry2 = {entryId:2, header: "Second", body: "This ties into another phenomenon I have been witnessing. At times during the night, around 11pm and onwards, people would choose to ignore red lights located around the kingdom. They would slow down, see that the intersection was not busy, and would just drive off like nothing happened. It drives me insane seeing this, and even though they might justify it by saying that they've slowed down and saw no other cars coming.. there simply is no jusitfication.", tags: "two", sentimentScore: 9, publicPost: false };
  
  entryArray: any[] = [];

  constructor() { }

  ngOnInit(): void {
    this.entryArray = [this.entry1, this.entry2];
  }

  viewDetailEntry():void{

  }



}
