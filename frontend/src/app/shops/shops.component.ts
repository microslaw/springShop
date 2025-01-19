import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { CommonModule } from '@angular/common';
import { ConvertShops, Shops } from "../../interfaces/shops";
import { ConvertShop, Shop } from "../../interfaces/shop";
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-shops',
  imports: [CommonModule, FormsModule],
  // templateUrl: './shops.component.html',
  template: `
  <main>
    <header class="brand-name">
      shops.components.ts
    </header>
    <section class="content">
      <ol>
        <li *ngFor="let shop of shops; let i = index">
          <span>{{ shop }}</span>
          <button (click)="editShop(i)">Edit</button>
          <button (click)="deleteShop(i)">Delete</button>
        </li>
      </ol>
      <div *ngIf="isEditing">
        <h3>Edit Shop</h3>
        <form (ngSubmit)="saveShop()">
          <label for="shopName">Name:</label>
          <input id="shopName" [(ngModel)]="currentShop.name" name="shopName" required>
          <br>
          <label for="shopName">Location:</label>
          <input id="shopLocation" [(ngModel)]="currentShop.location" name="shopLocation" required>
          <br>
          <label for="shopName">FoundingYear:</label>
          <input id="shopFoundingYear" [(ngModel)]="currentShop.foundingYear" name="shopFoundingYear" required>
          <br>
          <button type="submit">Save</button>
          <button type="button" (click)="cancelEdit()">Cancel</button>
        </form>
      </div>
    </section>
  </main>
  `,
  styleUrls: ['./shops.component.css']
})
export class ShopsComponent implements OnInit {
  shops: String[] = [];
  isEditing: boolean = false;
  currentShop: Shop = { name: "", location: "", foundingYear: 0 };

  constructor(private http: HttpClient) { }

  ngOnInit(): void {
    this.http.get<any[]>('http://localhost:8080/api/shops').subscribe(json => {
      const shops = ConvertShops.fromJson(JSON.stringify(json));
      this.shops = shops.shopNames;
    });
  }

  editShop(index: number): void {
    this.http.get<Shop>("http://localhost:8080/api/shops/" + this.shops[index]).subscribe(shop => {
      this.currentShop = shop;
      this.isEditing = true;
    });
  }

  saveShop(): void {
    this.isEditing = false;
    this.http.patch(`http://localhost:8080/api/shops/${this.currentShop.name}`, this.currentShop).subscribe(() => {
    });
  }

  cancelEdit(): void {
    this.isEditing = false;
  }

  deleteShop(index: number): void {
    this.http.delete(`http://localhost:8080/api/shops/${this.shops[index]}`).subscribe(() => {
      this.shops.splice(index, 1);
    });
  }
}
