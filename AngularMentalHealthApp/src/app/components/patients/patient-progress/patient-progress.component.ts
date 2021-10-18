import { getLocaleMonthNames } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CookieService } from 'ngx-cookie-service';
import { PatientService } from 'src/app/services/patients/patient.service';

@Component({
  selector: 'app-patient-progress',
  templateUrl: './patient-progress.component.html',
  styleUrls: ['./patient-progress.component.css']
})
export class PatientProgressComponent implements OnInit {
  dataSource: Object;
  entryArray: any[] = [];
  chartData:any = [];

  constructor(private cookie:CookieService, private route:Router, private patS:PatientService) {
    //STEP 2 - Chart Data
    
    // STEP 3 - Chart Configuration
    const dataSource = {
      chart: {
        //Set the chart caption
        // caption: "Countries With Most Oil Reserves [2017-18]",
        //Set the chart subcaption
        // subCaption: "In MMbbl = One Million barrels",
        //Set the x-axis name
        xAxisName: "Day",
        //Set the y-axis name
        yAxisMaxValue: 100.01,
        yAxisMinValue: 0,
        showYAxisValues: '0',
        connectNullData: "1",
        //Set the theme for your chart
        theme: "fusion"
      },
      // Chart Data - from step 2
      data: this.chartData
    };
    this.dataSource = dataSource;
  }

  ngOnInit(): void {
    if(!(this.cookie.check('username') && this.cookie.get('accountType') == 'patient')){
      this.route.navigate(['/login']);
    }

    this.patS.getAllMyEntries().subscribe(
      (allEntries:any)=>{
        this.entryArray = allEntries;
        this.entryArray.sort((a:any, b:any) => (a.datePosted < b.datePosted) ? 1 : -1);
        console.log(this.entryArray);
        let today = new Date();
        console.log(today);
        console.log(this.getDateString(today));
        this.chartData.push({
          label: this.getDateString(today),
          value: null
        })
        for(let i = 1; i < 7; i++){
          this.chartData.push({
            label: this.getDateString(new Date(today.getTime() - (i * 24 * 60 * 60 * 1000))),
            value: null
          });
        }
        for(let entry of this.entryArray){
          let date = entry.datePosted.substring(0,10);
          let sentiment = entry.sentiment;
          for(let data of this.chartData){
            if(date == data.label){
              if(data.value == null){
                data.value = [sentiment];
              }
              else{
                data.value.push(sentiment);
              }
              break;
            }
          }
        }
        for(let data of this.chartData){
          if(data.value!=null){
            let avg = 0;
            let len = data.value.length;
            for(let value of data.value){
              avg+= value;
            }
            data.value = avg/len;
          }
        }
        this.chartData.reverse();
      },
      ()=>{
        console.log("No information")
      }
    );

  }

  getDateString(date:Date): String{
    let m = parseInt(""+date.getMonth()) + 1;
    let month: string = "" + m;
    if(month.length <2){
      month = "0"+month;
    }
    let day: string = "" + date.getDate();
    if(day.length<2){
      day="0"+day;
    }
    return date.getFullYear() + "-" + month + "-" + day;

  }

}
