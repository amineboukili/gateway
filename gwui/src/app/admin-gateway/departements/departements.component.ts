import { Component, OnInit } from '@angular/core';
import {UserService} from "../../services/user/user.service";
import {DepartmentService} from "../../services/department/department.service";

@Component({
  selector: 'app-departements',
  templateUrl: './departements.component.html',
  styleUrls: ['./departements.component.scss']
})
export class DepartementsComponent implements OnInit {

  public departments;

  constructor(private departmentService: DepartmentService) { }

  ngOnInit() {
    this.getDepartments();
  }

  getDepartments() {
    this.departmentService.getDepartments().subscribe(data => {this.departments = data});
  }

}
