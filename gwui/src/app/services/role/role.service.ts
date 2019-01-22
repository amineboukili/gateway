import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';

@Injectable()
export class RoleService {

  public API = '//localhost:9008';
  public ROLES_API = this.API + '/Roles';


  constructor(private http: HttpClient) {}

  getRoles() {
    return this.http.get(this.ROLES_API + '');
  }
}
