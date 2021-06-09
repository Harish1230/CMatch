import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { LoginComponent } from './login/login.component';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { DashboardComponent } from './dashboard/dashboard.component';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatDialogModule, MatSidenavModule } from '@angular/material';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { OldmatchesComponent } from './oldmatches/oldmatches.component';
import { MatCardModule } from '@angular/material/card';
import { RegistrationComponent } from './registration/registration.component';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatTableModule } from '@angular/material/table';
import { UpcomingmatchesComponent } from './upcomingmatches/upcomingmatches.component';
import { MatSlideToggleModule } from '@angular/material/slide-toggle';
import { AuthenticationService } from './services/authentication.service';
import { RouterService } from './services/router.service';
import { MatToolbarModule } from '@angular/material/toolbar';
import { UpdateUserComponent } from './update-user/update-user.component';
import { FavouriteComponent } from './favourite/favourite.component'
import { MatchService } from './services/match.service';
import { HeaderComponent } from './header/header.component';
import {MatListModule} from '@angular/material/list';
import {MatMenuModule} from '@angular/material/menu';
import {MatIconModule} from '@angular/material/icon';
import { ProfileImageComponent } from './profile-image/profile-image.component';
import { Guard } from './guard';
import { ViewProfileComponent } from './view-profile/view-profile.component';
import {MatGridListModule} from '@angular/material/grid-list';
import { BasicAuthHtppInterceptorServiceService } from './basic-auth-htpp-interceptor-service.service';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import {MatCheckboxModule} from '@angular/material/checkbox';
import { LogInGuard } from './loginGuard';



@NgModule({
  declarations: [
    AppComponent,
    DashboardComponent,
    LoginComponent,
    OldmatchesComponent,
    RegistrationComponent,
    UpcomingmatchesComponent,
    FavouriteComponent,
    UpdateUserComponent,
    HeaderComponent,
    ProfileImageComponent,
    ViewProfileComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatInputModule,
    MatButtonModule,
    MatSnackBarModule,
    FormsModule,
    MatGridListModule,
    HttpClientModule,
    MatDialogModule,
    ReactiveFormsModule,
    MatFormFieldModule,
    MatCardModule,
    MatTableModule,
    MatToolbarModule,
    MatSlideToggleModule,
    MatSidenavModule,
    MatListModule,
    MatMenuModule,
    MatIconModule,
    MatCheckboxModule
  ],
  providers: [AuthenticationService, RouterService, MatchService,Guard,LogInGuard,
    { provide: HTTP_INTERCEPTORS, useClass: BasicAuthHtppInterceptorServiceService, multi: true }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
