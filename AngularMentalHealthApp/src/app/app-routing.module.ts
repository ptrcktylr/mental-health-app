import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { PatientEntryComponent } from './components/patients/patient-entry/patient-entry.component';
import { PatientHistoryComponent } from './components/patients/patient-history/patient-history.component';
import { PatientNewComponent } from './components/patients/patient-new/patient-new.component';
import { PatientProgressComponent } from './components/patients/patient-progress/patient-progress.component';
import { LoginComponent } from './components/portal/login/login.component';
import { RegisterComponent } from './components/portal/register/register.component';
import { ProfessionalAllPatientsComponent } from './components/professionals/professional-all-patients/professional-all-patients.component';
import { ProfessionalMyPatientsComponent } from './components/professionals/professional-my-patients/professional-my-patients.component';
import { ViewPublicEntryComponent } from './components/public/view-public-entry/view-public-entry.component';
import { ViewPublicComponent } from './components/public/view-public/view-public.component';
import { ProfessionalPatientHistoryComponent } from './components/professionals/professional-patient-history/professional-patient-history.component';
import { ProfessionalViewEntryComponent } from './components/professionals/professional-view-entry/professional-view-entry.component';
import { ProfessionalViewProgressComponent } from './components/professionals/professional-view-progress/professional-view-progress.component';

const routes: Routes = [
  {
    path: "login",
    component: LoginComponent
  },
  {
    path: "",
    pathMatch: 'prefix',
    redirectTo: "login"
  },
  {
    path: "register",
    component: RegisterComponent
  },
  {
    path: "patient/history",
    component: PatientHistoryComponent
  },
  {
    path: "patient/new",
    component: PatientNewComponent
  },
  {
    path: "patient/history/:id",
    component: PatientEntryComponent
  },
  {
    path: "patient/progress",
    component: PatientProgressComponent
  },
  {
    path: "professional/my-patients",
    component: ProfessionalMyPatientsComponent
  },
  {
    path: "professional/all-patients",
    component: ProfessionalAllPatientsComponent
  },
  {
    path: "professional/progress",
    component: ProfessionalViewProgressComponent
  },
  {
    path: "professional/patient-history/:id",
    component: ProfessionalPatientHistoryComponent
  },
  {
    path: "professional/entry/:id",
    component: ProfessionalViewEntryComponent
  },
  {
    path: "view-public",
    component: ViewPublicComponent
  },
  {
    path: "view-public/:id",
    component: ViewPublicEntryComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
