import { Component, Input, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Shop } from '../../interfaces/shop';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ConvertDetailedShop, DetailedShop } from '../../interfaces/detailed-shop';
import { Convert, Computer } from '../../interfaces/computer';

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
        <li *ngFor="let computer of computerList; let i = index">
          {{ computer }}
          <button (click)="editComputer(i)">Edit</button>
          <button (click)="deleteComputer(i)">Delete</button>
        </li>
      </ul>
      <div *ngIf="isEditingComputer">
        <h3>Edit Computer</h3>
        <form (ngSubmit)="updateComputer()">
          <label for="computerName">Name:</label>
          <input id="computerName" [(ngModel)]="currentComputer.model" name="computerName" required>
          <br>
          <label for="computerProducer">Producer:</label>
          <input id="computerProducer" [(ngModel)]="currentComputer.producer" name="computerProducer" required>
          <br>
          <label for="computerMemory">Memory:</label>
          <input id="computerMemory" [(ngModel)]="currentComputer.memory" name="computerMemory" required>
          <br>
          <label for="computerMacNumber">Mac Number:</label>
          <input id="computerMacNumber" [(ngModel)]="currentComputer.mac_number" name="computerMacNumber" required>
          <br>

          <button type="submit">Save</button>
          <button type="button" (click)="cancelEditComputer()">Cancel</button>
        </form>
      </div>
    </div>
  `,
  styleUrls: ['./detailed-shop.component.css']
})
export class DetailedShopComponent implements OnInit {
  @Input() shop: Shop = { name: "", location: "", foundingYear: 0 };
  computerList: String[] = [];
  isEditingComputer: boolean = false;
  currentComputer: Computer = { producer: "", model: "", memory: 0, mac_number: "" };
  currentComputerIndex: number = -1;

  constructor(private http: HttpClient) { }

  ngOnInit(): void {
    this.getComputerList();
  }

  getComputerList(): void {
    this.http.get<any>(`http://localhost:8080/api/shops/${this.shop.name}/computer_list`).subscribe(data => {
      const computers = ConvertDetailedShop.fromJson(JSON.stringify(data));
      this.computerList = computers.computers;
    });
  }

  editComputer(computerIndex: number): void {
    this.http.get<any>(`http://localhost:8080/api/shops/${this.shop.name}/computer_list/${this.computerList[computerIndex]}`).subscribe(data => {

      this.currentComputer = Convert.fromJson(JSON.stringify(data));
      this.currentComputerIndex = computerIndex;
      this.isEditingComputer = true;
    });
    console.log(this.currentComputer);
    console.log(computerIndex);
  }

  updateComputer(): void {
    this.isEditingComputer = false;
    this.http.patch(`http://localhost:8080/api/shops/${this.shop.name}/computer_list/${this.currentComputer.model}`, this.currentComputer).subscribe(() => {
      this.computerList[this.currentComputerIndex] = this.currentComputer.model;
      this.currentComputer = { producer: "", model: "", memory: 0, mac_number: "" };
      this.currentComputerIndex = -1;
    });
  }

  cancelEditComputer(): void {
    this.isEditingComputer = false;
    this.currentComputer = { producer: "", model: "", memory: 0, mac_number: "" };
    this.currentComputerIndex = -1;
  }

  deleteComputer(index: number): void {
    this.http.delete(`http://localhost:8080/api/shops/${this.shop.name}/computer_list/${this.computerList[index]}`).subscribe(() => {
      this.computerList.splice(index, 1);
    });
  }
}
