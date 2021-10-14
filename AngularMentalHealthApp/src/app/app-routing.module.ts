import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { PatientHistoryComponent } from './components/patients/patient-history/patient-history.component';
import { PatientNewComponent } from './components/patients/patient-new/patient-new.component';
import { PatientProgressComponent } from './components/patients/patient-progress/patient-progress.component';
import { LoginComponent } from './components/portal/login/login.component';
import { RegisterComponent } from './components/portal/register/register.component';
import { ProfessionalAllPatientsComponent } from './components/professionals/professional-all-patients/professional-all-patients.component';
import { ProfessionalMyPatientsComponent } from './components/professionals/professional-my-patients/professional-my-patients.component';
import { ViewPublicComponent } from './components/public/view-public/view-public.component';

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
    path: "patient-history",
    component: PatientHistoryComponent
  },
  {
    path: "patient-new",
    component: PatientNewComponent
  },
  {
    path: "patient-progress",
    component: PatientProgressComponent
  },
  {
    path: "my-patients",
    component: ProfessionalMyPatientsComponent
  },
  {
    path: "all-patients",
    component: ProfessionalAllPatientsComponent
  },
  {
    path: "view-public",
    component: ViewPublicComponent
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
