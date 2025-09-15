import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-twoway',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './twoway.html',
  styleUrl: './twoway.css'
})
export class Twoway {
  name = '';
}
