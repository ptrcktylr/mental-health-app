import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './components/security/login/login.component';
import { SignupComponent } from './components/security/signup/signup.component';
import { PatientHistoryComponent } from './components/patients/patient-history/patient-history.component';
import { PatientNewComponent } from './components/patients/patient-new/patient-new.component';
import { PatientProgressComponent } from './components/patients/patient-progress/patient-progress.component';
import { ViewPublicComponent } from './components/view-public/view-public.component';
import { ProfessionalMyPatientsComponent } from './components/professionals/professional-my-patients/professional-my-patients.component';
import { ProfessionalViewProgressComponent } from './components/professionals/professional-view-progress/professional-view-progress.component';
import { ProfessionalViewEntryComponent } from './components/professionals/professional-view-entry/professional-view-entry.component';
import { ProfessionalAllPatientsComponent } from './components/professionals/professional-all-patients/professional-all-patients.component';
import { ProfessionalAddPatientComponent } from './components/professionals/professional-add-patient/professional-add-patient.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    SignupComponent,
    PatientHistoryComponent,
    PatientNewComponent,
    PatientProgressComponent,
    ViewPublicComponent,
    ProfessionalMyPatientsComponent,
    ProfessionalViewProgressComponent,
    ProfessionalViewEntryComponent,
    ProfessionalAllPatientsComponent,
    ProfessionalAddPatientComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
