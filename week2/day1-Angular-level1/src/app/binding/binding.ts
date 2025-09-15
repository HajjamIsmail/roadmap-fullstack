import { Component } from '@angular/core';

@Component({
  selector: 'app-binding',
  imports: [],
  templateUrl: './binding.html',
  styleUrl: './binding.css'
})
export class Binding {
  title = 'Binding demo';
  imageUrl = 'favicon.ico'

  sayHello() {
    alert('Hello Binding !');
  }

}
