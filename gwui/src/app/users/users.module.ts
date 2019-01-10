import { NgModule } from '@angular/core';
import {UsersComponent} from './users.component';
import {CommonModule} from '@angular/common';
import {UsersRoutingModule} from './users-routing.module';
import {UserService} from '../services/user/user.service';
import {HttpClientModule} from '@angular/common/http';

@NgModule({
    imports: [
      CommonModule,
      UsersRoutingModule,
      HttpClientModule
    ],
    declarations: [
        UsersComponent
    ],
    providers: [
      UserService
    ],
})
export class UsersModule { }
