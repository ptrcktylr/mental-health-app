import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import {HttpClientModule} from '@angular/common/http'

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { PatientHistoryComponent } from './components/patients/patient-history/patient-history.component';
import { PatientNewComponent } from './components/patients/patient-new/patient-new.component';
import { PatientProgressComponent } from './components/patients/patient-progress/patient-progress.component';
import { ProfessionalMyPatientsComponent } from './components/professionals/professional-my-patients/professional-my-patients.component';
import { ProfessionalViewProgressComponent } from './components/professionals/professional-view-progress/professional-view-progress.component';
import { ProfessionalViewEntryComponent } from './components/professionals/professional-view-entry/professional-view-entry.component';
import { ProfessionalAllPatientsComponent } from './components/professionals/professional-all-patients/professional-all-patients.component';
import { ProfessionalAddPatientComponent } from './components/professionals/professional-add-patient/professional-add-patient.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { PatientEntryComponent } from './components/patients/patient-entry/patient-entry.component';
import { ViewPublicEntryComponent } from './components/public/view-public-entry/view-public-entry.component';
import { PatientNavComponent } from './components/navbars/patient-nav/patient-nav.component';
import { ProfessionalNavComponent } from './components/navbars/professional-nav/professional-nav.component';
import { LoginComponent } from './components/portal/login/login.component';
import { FormsModule } from '@angular/forms';
import { RegisterComponent } from './components/portal/register/register.component';
import { ProfessionalPatientHistoryComponent } from './components/professionals/professional-patient-history/professional-patient-history.component';
import { ViewPublicComponent } from './components/public/view-public/view-public.component';

import { FusionChartsModule } from "angular-fusioncharts";
// Import FusionCharts library and chart modules
import * as FusionCharts from "fusioncharts";
import * as charts from "fusioncharts/fusioncharts.charts";
import * as FusionTheme from "fusioncharts/themes/fusioncharts.theme.fusion";
import { CookieService } from 'ngx-cookie-service';


// Pass the fusioncharts library and chart modules
FusionChartsModule.fcRoot(FusionCharts, charts, FusionTheme);

@NgModule({
  declarations: [
    AppComponent,
    PatientHistoryComponent,
    PatientNewComponent,
    PatientProgressComponent,
    ProfessionalMyPatientsComponent,
    ProfessionalViewProgressComponent,
    ProfessionalViewEntryComponent,
    ProfessionalAllPatientsComponent,
    ProfessionalAddPatientComponent,
    PatientEntryComponent,
    ViewPublicEntryComponent,
    ViewPublicComponent,
    PatientNavComponent,
    ProfessionalNavComponent,
    LoginComponent,
    RegisterComponent,
    ProfessionalPatientHistoryComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    FusionChartsModule,
    BrowserAnimationsModule,
    HttpClientModule
  ],
  providers: [
    CookieService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
