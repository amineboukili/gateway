import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';

@Injectable()
export class UserService {

  public API = '//localhost:9008';
  public USERS_API = this.API + '/Users';


  constructor(private http: HttpClient) {}

  getUsers() {
    return this.http.get(this.USERS_API + '');
  }
}
