import { Component, Input, Output, EventEmitter } from '@angular/core';

@Component({
  selector: 'app-child',
  templateUrl: './child.html',
  styleUrl: './child.css'
})
export class Child {
  @Input() name!: string;
  @Output() greet = new EventEmitter<string>();

  sendGreet() {
    this.greet.emit(`Hi ${this.name}`);
  }
}
