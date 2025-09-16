import { Component, signal } from '@angular/core';
import { RouterOutlet } from '@angular/router';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet],
  template: `
    <h1>User Managment</h1>
    <router-outlet></router-outlet>
  `
})
export class App {
  protected readonly title = signal('day2-angular-level2');
}
