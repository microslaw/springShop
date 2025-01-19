import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { CommonModule } from '@angular/common';
import { Convert, Shops } from "../../interfaces/shops";


@Component({
  selector: 'app-shops',
  imports: [CommonModule],
  // templateUrl: './shops.component.html',
  template: `
  <main>
    <header class="brand-name">
      shops.components.ts
    </header>
    <section class="content">

      <!-- {{ shops | json }} -->

      <div *ngFor="let shop of shops">
        <p>{{ shop}}</p>
      </div>

    </section>
  </main>
  `,
  styleUrls: ['./shops.component.css']
})
export class ShopsComponent implements OnInit {
  shops: String[] = [];

  constructor(private http: HttpClient) { }

  ngOnInit(): void {

    this.http.get<any[]>('http://localhost:8080/api/shops').subscribe(json => {
      const shops = Convert.toShops(JSON.stringify(json));
      this.shops = shops.shopNames;
    });
  }
}
