import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { PatientNewComponent } from './components/patients/patient-new/patient-new.component';

const routes: Routes = [
  {
    path:"patient/new",
    component: PatientNewComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
