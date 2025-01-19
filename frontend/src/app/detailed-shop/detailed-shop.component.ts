import { Component, Input } from '@angular/core';
import { Shop } from '../../interfaces/shop';

@Component({
  selector: 'app-detailed-shop',
  template: `
    <div>
      <h3>Shop Details</h3>
      <p>Name: {{ shop.name }}</p>
      <p>Location: {{ shop.location }}</p>
      <p>Founding Year: {{ shop.foundingYear }}</p>
    </div>
  `,
  styleUrls: ['./detailed-shop.component.css']
})
export class DetailedShopComponent {
  @Input() shop: Shop = { name: "", location: "", foundingYear: 0 };

  closeDetails() {

  }
}
