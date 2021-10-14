import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-patient-progress',
  templateUrl: './patient-progress.component.html',
  styleUrls: ['./patient-progress.component.css']
})
export class PatientProgressComponent implements OnInit {
  
  dataSource: Object;
  constructor() {
    //STEP 2 - Chart Data
    const chartData = [
      {
        label: "10/14/2021",
        value: 1
      },
      {
        label: "10/15/2021",
        value: -1
      },
      {
        label: "10/16/2021",
        value: 0
      },
      {
        label: "10/17/2021",
        value: 0
      },
      {
        label: "10/18/2021",
        value: -1
      },
      {
        label: "10/19/2021",
        value: 1
      },
      {
        label: "10/20/2021",
        value: -1
      },
    ];
    // STEP 3 - Chart Configuration
    const dataSource = {
      chart: {
        //Set the chart caption
        // caption: "Countries With Most Oil Reserves [2017-18]",
        //Set the chart subcaption
        // subCaption: "In MMbbl = One Million barrels",
        //Set the x-axis name
        xAxisName: "Date",
        //Set the y-axis name
        yAxisName: "<--Negative_____Positive-->",
        showYAxisValues: '0',
        //Set the theme for your chart
        theme: "fusion"
      },
      // Chart Data - from step 2
      data: chartData
    };
    this.dataSource = dataSource;
  }

  ngOnInit(): void {
  }

}
