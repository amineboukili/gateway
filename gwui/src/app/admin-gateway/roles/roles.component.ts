import { Component, OnInit } from '@angular/core';
import {RoleService} from "../../services/role/role.service";

@Component({
  selector: 'app-roles',
  templateUrl: './roles.component.html',
  styleUrls: ['./roles.component.scss']
})
export class RolesComponent implements OnInit {

  public roles;

  constructor(private roleService: RoleService) { }

  ngOnInit() {
    this.getRoles();
  }

  getRoles() {
    this.roleService.getRoles().subscribe(data => {this.roles = data});
  }

}
