import { Injectable } from '@angular/core';

declare var require: any;

@Injectable({
  providedIn: 'root'
})


export class DeepaiApiService {

  async trySentimentAnalysis(txtInput:String):Promise<void> {

    const deepai = require('deepai'); // OR include deepai.min.js as a script tag in your HTML

    deepai.setApiKey('1bb26184-f8a5-4309-a691-179c2e3f0e83');
    
    (async function() {
        var resp = await deepai.callStandardApi("sentiment-analysis", {
                text: txtInput,
        });
        console.log(resp);
        //return resp;
    })();

    //return null;

  }

  constructor() { }
}
