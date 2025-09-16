import { Component, OnInit } from '@angular/core';
import { Router, RouterLink } from '@angular/router';
import { CommonModule } from '@angular/common';
import { User, UserInterface } from '../../service/user';

@Component({
  selector: 'app-user-list',
  standalone: true,
  imports: [CommonModule, RouterLink],
  template: `
    <h2>All Users</h2>
    <button (click)="goToNew()">Add User</button>
    <ul>
      <li *ngFor="let user of users">
        {{user.name}} ({{user.email}})
        <button (click)="edit(user.id!)">Edit</button>
        <button (click)="delete(user.id!)">Delete</button>
      </li>
    </ul>
  `
})
export class UserList implements OnInit {
  users: UserInterface[] = [];

  constructor(private userService: User, private router: Router) {}

  ngOnInit() {
    this.load();
  }

  load() {
    this.userService.getAll().subscribe(data => this.users = data);
  }

  edit(id: number) {
    this.router.navigate(['/users/edit', id]);
  }

  delete(id: number) {
    this.userService.delete(id).subscribe(() => this.load());
  }

  goToNew() {
    this.router.navigate(['/users/new']);
  }
}
