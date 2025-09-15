import { Component, signal } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { Hello } from "./hello/hello";
import { Parent } from "./parent/parent";
import { Binding } from "./binding/binding";
import { Twoway } from "./twoway/twoway";
import { Directives } from "./directives/directives";
import { Todo } from "./todo/todo";

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, Hello, Parent, Binding, Twoway, Directives, Todo],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App {
  protected readonly title = signal('day1-Angular-level1');
}
