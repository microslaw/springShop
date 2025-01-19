import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { CommonModule } from '@angular/common';
import { ConvertShops, Shops } from "../../interfaces/shops";
import { ConvertShop, Shop } from "../../interfaces/shop";
import { FormsModule } from '@angular/forms';
import { DetailedShopComponent } from '../detailed-shop/detailed-shop.component';

@Component({
  selector: 'app-shops',
  imports: [CommonModule, FormsModule, DetailedShopComponent],
  // templateUrl: './shops.component.html',
  template: `
  <main *ngIf="!showDetails">
    <header class="brand-name">
      shops.components.ts
    </header>
    <section class="content">
      <ol>
        <li *ngFor="let shop of shops; let i = index">
          <span>{{ shop }}</span>
          <button (click)="editShop(i)">Edit</button>
          <button (click)="deleteShop(i)">Delete</button>
          <button (click)="viewDetails(i)">Details</button>
        </li>
      </ol>
      <div *ngIf="isEditing">
        <h3>Edit Shop</h3>
        <form (ngSubmit)="updateShop()">
          <label for="shopName">Name:</label>
          <input id="shopName" [(ngModel)]="currentShop.name" name="shopName" required>
          <br>
          <label for="shopLocation">Location:</label>
          <input id="shopLocation" [(ngModel)]="currentShop.location" name="shopLocation" required>
          <br>
          <label for="shopFoundingYear">Founding Year:</label>
          <input id="shopFoundingYear" [(ngModel)]="currentShop.foundingYear" name="shopFoundingYear" required>
          <br>
          <button type="submit">Save</button>
          <button type="button" (click)="cancelEdit()">Cancel</button>
        </form>
      </div>
      <button (click)="toggleAddShopForm()">Add New Shop</button>
      <div *ngIf="isAddingShop">
        <h3>Add New Shop</h3>
        <form (ngSubmit)="addShop()">
          <label for="shopName">Name:</label>
          <input id="shopName" [(ngModel)]="newShop.name" name="shopName" required>
          <br>
          <label for="shopLocation">Location:</label>
          <input id="shopLocation" [(ngModel)]="newShop.location" name="shopLocation" required>
          <br>
          <label for="shopFoundingYear">Founding Year:</label>
          <input id="shopFoundingYear" [(ngModel)]="newShop.foundingYear" name="shopFoundingYear" required>
          <br>
          <button type="submit">Add</button>
          <button type="button" (click)="cancelAddShop()">Cancel</button>
        </form>
      </div>
    </section>
  </main>
  <app-detailed-shop *ngIf="showDetails" [shop]="currentShop" (close)="closeDetails()"></app-detailed-shop>
  <button *ngIf="showDetails" (click)="closeDetails()">Close</button>
  `,
  styleUrls: ['./shops.component.css']
})
export class ShopsComponent implements OnInit {
  shops: String[] = [];
  isEditing: boolean = false;
  isAddingShop: boolean = false;
  showDetails: boolean = false;
  currentShop: Shop = { name: "", location: "", foundingYear: 0 };
  newShop: Shop = { name: "", location: "", foundingYear: 0 };

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

  updateShop(): void {
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

  toggleAddShopForm(): void {
    this.isAddingShop = !this.isAddingShop;
  }

  addShop(): void {
    this.isAddingShop = false;
    this.http.put(`http://localhost:8080/api/shops/${this.newShop.name}`, this.newShop).subscribe(() => {
      this.shops.push(this.newShop.name);
      this.newShop = { name: "", location: "", foundingYear: 0 };
    });
  }

  cancelAddShop(): void {
    this.isAddingShop = false;
  }

  viewDetails(index: number): void {
    this.http.get<Shop>("http://localhost:8080/api/shops/" + this.shops[index]).subscribe(shop => {
      this.currentShop = shop;
      this.showDetails = true;
    });
  }

  closeDetails(): void {
    this.showDetails = false;
  }
}
