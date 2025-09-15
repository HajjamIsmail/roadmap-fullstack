import { Component } from '@angular/core';
import { Child } from "../child/child";

@Component({
  selector: 'app-parent',
  imports: [Child],
  templateUrl: './parent.html',
  styleUrl: './parent.css'
})
export class Parent {
  userName = 'Ismail';

  onChildGreet(message: string) {
    alert(message);
  }
}
