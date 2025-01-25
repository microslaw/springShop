import { Component } from '@angular/core';
import {ShopsComponent} from '../shops/shops.component';

@Component({
  selector: 'app-home',
  imports: [ShopsComponent],
  // templateUrl: './home.component.html',
  template: `
  <section>
      <form>
        <input type="text" placeholder="Filter by city" />
        <button class="primary" type="button">Search</button>
      </form>
    </section>
    <section class="results">
      <app-shops></app-shops>
    </section>
  `,
  styleUrls: ['./home.component.css'],
  // styleUrl: './home.component.css'
})
export class HomeComponent {

}
