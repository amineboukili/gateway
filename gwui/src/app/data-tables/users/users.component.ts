import { Component, OnInit } from '@angular/core';
import {UserService} from '../../services/user/user.service';

@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.scss']
})
export class UsersComponent implements OnInit {
  public users;
  constructor(private userService: UserService) { }
  ngOnInit() {
    this.getUsers();
  }
  getUsers() {
    this.userService.getUsers().subscribe(data => {this.users = data});
  }
}
