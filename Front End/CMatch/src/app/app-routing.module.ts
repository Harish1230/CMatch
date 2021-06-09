import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { OldmatchesComponent } from './oldmatches/oldmatches.component';
import { UpcomingmatchesComponent } from './upcomingmatches/upcomingmatches.component';
import { LoginComponent } from './login/login.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { FavouriteComponent } from './favourite/favourite.component';
import { RegistrationComponent } from './registration/registration.component'
import { UpdateUserComponent } from './update-user/update-user.component';
import { Guard } from './guard';
import { ProfileImageComponent } from './profile-image/profile-image.component';
import { ViewProfileComponent } from './view-profile/view-profile.component';
import { LogInGuard } from './loginGuard';

const routes: Routes = [
  { path: 'favourite', component: FavouriteComponent, canActivate: [Guard] },
  { path: 'update-user', component: UpdateUserComponent, canActivate: [Guard] },
  { path: 'oldmatches', component: OldmatchesComponent },
  { path: 'upcomingmatches', component: UpcomingmatchesComponent },
  { path: 'login', component: LoginComponent ,canActivate: [LogInGuard]},
  { path: 'dashboard', component: DashboardComponent },
  { path: 'registration', component: RegistrationComponent },
  { path: 'profileimage', component: ProfileImageComponent , canActivate: [Guard]},
  { path: 'viewProfile', component: ViewProfileComponent, canActivate: [Guard]},
  { path: '**', component: DashboardComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
// export consr routingComponents = []
