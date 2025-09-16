import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ActivatedRoute, Router } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { User, UserInterface } from '../../service/user';

@Component({
  selector: 'app-user-form',
  standalone: true,
  imports: [CommonModule, FormsModule],
  template: `
    <h2>{{ isEdit ? 'Edit' : 'New' }} User</h2>
    <form (ngSubmit)="save()">
      <input [(ngModel)]="user.name" name="name" placeholder="Name" required />
      <input [(ngModel)]="user.email" name="email" placeholder="Email" required type="email" />
      <button type="submit">Save</button>
    </form>
  `
})
export class UserForm implements OnInit {
  user: UserInterface = { name: '', email: '' };
  isEdit = false;

  constructor(
    private userService: User,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit() {
    const id = this.route.snapshot.paramMap.get('id');
    if (id) {
      this.isEdit = true;
      this.userService.getById(+id).subscribe(u => this.user = u);
    }
  }

  save() {
    if (this.isEdit && this.user.id) {
      this.userService.update(this.user.id, this.user)
        .subscribe(() => this.router.navigate(['/users']));
    } else {
      this.userService.create(this.user)
        .subscribe(() => this.router.navigate(['/users']));
    }
  }
}
