import { Injectable } from '@angular/core';

declare var require: any;

@Injectable({
  providedIn: 'root'
})
export class SentpackService {

  trySentPack(txtInput:String):any{
    var Sentiment = require('sentiment');
    var sentiment = new Sentiment();
    var result = sentiment.analyze(txtInput);
    //console.log(result);

    return result;
  }

  constructor() { }
}
