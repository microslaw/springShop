import { Component, Input, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Shop } from '../../interfaces/shop';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ConvertDetailedShop, DetailedShop } from '../../interfaces/detailed-shop';

@Component({
  imports: [CommonModule, FormsModule],
  selector: 'app-detailed-shop',
  template: `
    <div>
      <h3>Shop Details</h3>
      <p>Name: {{ shop.name }}</p>
      <p>Location: {{ shop.location }}</p>
      <p>Founding Year: {{ shop.foundingYear }}</p>
      <h4>Computer List</h4>
      <ul>
        <li *ngFor="let computer of computers">{{ computer }}</li>
      </ul>
    </div>
  `,
  styleUrls: ['./detailed-shop.component.css']
})
export class DetailedShopComponent implements OnInit {
  @Input() shop: Shop = { name: "", location: "", foundingYear: 0 };
  computers: string[] = [];

  constructor(private http: HttpClient) { }

  ngOnInit(): void {
    this.getComputerList();
  }

  getComputerList(): void {
    // print request
    this.http.get<any>(`http://localhost:8080/api/shops/${this.shop.name}/computer_list`).subscribe(data => {
      // convert data to string
      const computers = ConvertDetailedShop.fromJson(JSON.stringify(data));
      this.computers = computers.computers;

    });
  }

}
