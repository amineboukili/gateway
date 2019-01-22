import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import {UsersComponent} from "./users/users.component";
import {DepartementsComponent} from "./departements/departements.component";
import {RightsComponent} from "./rights/rights.component";
import {RolesComponent} from "./roles/roles.component";
import {JobsComponent} from "./jobs/jobs.component";

const routes: Routes = [
  {
    path: '',
    children: [
      {
        path: 'users',
        component: UsersComponent,
        data: {
          title: 'Users'
        }
      },
      {
        path: 'roles',
        component: RolesComponent,
        data: {
          title: 'Roles'
        }
      },
      {
        path: 'departements',
        component: DepartementsComponent,
        data: {
          title: 'Departements'
        }
      },
      {
        path: 'rights',
        component: RightsComponent,
        data: {
          title: 'Rights'
        }
      },
      {
        path: 'jobs',
        component: JobsComponent,
        data: {
          title: 'Jobs'
        }
      },
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class AdminGatewayRoutingModule { }
