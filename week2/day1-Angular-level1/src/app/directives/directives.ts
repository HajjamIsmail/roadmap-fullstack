import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-directives',
  templateUrl: './directives.html',
  imports: [CommonModule],
  styleUrl: './directives.css'
})
export class Directives {
  show = true;
  items = ['Angular', 'React', 'Vue'];
  isHighlighted = true;
  color = 'blue';

}
