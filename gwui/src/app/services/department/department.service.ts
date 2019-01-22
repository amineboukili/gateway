import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';

@Injectable()
export class DepartmentService {

  public API = '//localhost:9008';
  public DEPARTMENTS_API = this.API + '/Departments';


  constructor(private http: HttpClient) {}

  getDepartments() {
    return this.http.get(this.DEPARTMENTS_API + '');
  }
}
