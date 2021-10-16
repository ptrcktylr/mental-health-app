import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-professional-patient-history',
  templateUrl: './professional-patient-history.component.html',
  styleUrls: ['./professional-patient-history.component.css']
})
export class ProfessionalPatientHistoryComponent implements OnInit {

  entryArray = [
    {
      id: 1,
      title: "Monday",
      date: new Date(),
      body: "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Fusce sed nisi sit amet leo commodo mollis ornare nec sapien. Nam nec est id lorem semper dictum sit amet quis risus. Suspendisse mattis condimentum ligula, et egestas erat sagittis sed. Nam iaculis, neque sed venenatis bibendum, elit nisi dignissim ex, id rutrum quam libero at velit. Suspendisse mattis est turpis, in malesuada sapien imperdiet quis. Vivamus in enim sit amet metus egestas vehicula sit amet a lorem. Mauris varius erat dui, eget malesuada augue lacinia ac. Integer tellus lorem, laoreet quis fringilla ut, scelerisque ultrices nunc. Suspendisse eu faucibus ex.",
      tags: "nsfw, sa",
      sentiment: 75
    },
    {
      id: 2,
      title: "Tuesday",
      date: new Date(),
      body: "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Fusce sed nisi sit amet leo commodo mollis ornare nec sapien. Nam nec est id lorem semper dictum sit amet quis risus. Suspendisse mattis condimentum ligula, et egestas erat sagittis sed. Nam iaculis, neque sed venenatis bibendum, elit nisi dignissim ex, id rutrum quam libero at velit. Suspendisse mattis est turpis, in malesuada sapien imperdiet quis. Vivamus in enim sit amet metus egestas vehicula sit amet a lorem. Mauris varius erat dui, eget malesuada augue lacinia ac. Integer tellus lorem, laoreet quis fringilla ut, scelerisque ultrices nunc. Suspendisse eu faucibus ex.",
      tags: "",
      sentiment: 20
    },
    // {
    //   label: "Wed",
    //   value: 47
    // },
  ];

  constructor(private route:Router) { }

  ngOnInit(): void {
  }

  goToProgress(){
    this.route.navigate(['/professional/progress']);
  }

  goToEntry(id:number){
    this.route.navigate(['/professional/entry/'+id]);
  }

}
