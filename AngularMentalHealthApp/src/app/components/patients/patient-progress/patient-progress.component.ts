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
        label: "Mon",
        value: 75
      },
      {
        label: "Tues",
        value: 20
      },
      {
        label: "Wed",
        value: 47
      },
      {
        label: "Thurs",
        value: 59
      },
      {
        label: "Fri",
        value: 15
      },
      {
        label: "Sat",
        value: 69
      },
      {
        label: "Sun",
        value: 40
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
        xAxisName: "Day",
        //Set the y-axis name
        yAxisMaxValue: 100.01,
        yAxisMinValue: 0,
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
