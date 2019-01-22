import { NgModule } from '@angular/core';
import { CommonModule } from "@angular/common";

import { NgxDatatableModule } from '@swimlane/ngx-datatable';
import { AdminGatewayRoutingModule } from "./admin-gateway-routing.module";

import { UsersComponent} from "./users/users.component";
import { RolesComponent} from "./roles/roles.component";
import { DepartementsComponent} from "./departements/departements.component";
import { RightsComponent} from "./rights/rights.component";
import { JobsComponent} from "./jobs/jobs.component";
import {UserService} from "../services/user/user.service";
import {HttpClientModule} from "@angular/common/http";
import {DepartmentService} from "../services/department/department.service";
import {RoleService} from "../services/role/role.service";

@NgModule({
    imports: [
        CommonModule,
        HttpClientModule,
        AdminGatewayRoutingModule,
        NgxDatatableModule
    ],
    exports: [],
    declarations: [
      UsersComponent,
      RolesComponent,
      DepartementsComponent,
      RightsComponent,
      JobsComponent,
    ],
    providers: [UserService, DepartmentService, RoleService],
})
export class AdminGatewayModule { }
