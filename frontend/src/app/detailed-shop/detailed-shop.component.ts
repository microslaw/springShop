import { Component, Input, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Shop } from '../../interfaces/shop';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ConvertDetailedShop, DetailedShop } from '../../interfaces/detailed-shop';
import { Convert, Computer } from '../../interfaces/computer';
import { ComputerComponent } from '../computer/computer.component';

@Component({
  imports: [CommonModule, FormsModule, ComputerComponent],
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
          <button (click)="viewComputerDetails(i)">Details</button>
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
      <button (click)="toggleAddComputerForm()">Add New Computer</button>
      <div *ngIf="isAddingComputer">
        <h3>Add New Computer</h3>
        <form (ngSubmit)="addComputer()">
          <label for="newComputerName">Name:</label>
          <input id="newComputerName" [(ngModel)]="newComputer.model" name="newComputerName" required>
          <br>
          <label for="newComputerProducer">Producer:</label>
          <input id="newComputerProducer" [(ngModel)]="newComputer.producer" name="newComputerProducer" required>
          <br>
          <label for="newComputerMemory">Memory:</label>
          <input id="newComputerMemory" [(ngModel)]="newComputer.memory" name="newComputerMemory" required>
          <br>
          <label for="newComputerMacNumber">Mac Number:</label>
          <input id="newComputerMacNumber" [(ngModel)]="newComputer.mac_number" name="newComputerMacNumber" required>
          <br>
          <button type="submit">Add</button>
          <button type="button" (click)="cancelAddComputer()">Cancel</button>
        </form>
      </div>
    </div>
    <app-computer *ngIf="showDetails" [computer]="currentComputer" (close)="closeDetails()"></app-computer>
    <button *ngIf="showDetails" (click)="closeDetails()">Close</button>
  `,
  styleUrls: ['./detailed-shop.component.css']
})
export class DetailedShopComponent implements OnInit {
  @Input() shop: Shop = { name: "", location: "", foundingYear: 0 };
  computerList: string[] = [];
  isEditingComputer: boolean = false;
  isAddingComputer: boolean = false;
  currentComputer: Computer = { producer: "", model: "", memory: 0, mac_number: "" };
  newComputer: Computer = { producer: "", model: "", memory: 0, mac_number: "" };
  showDetails: boolean = false;
  currentComputerIndex: number = -1;

  constructor(private http: HttpClient) { }

  ngOnInit(): void {
    this.getComputerList();
  }

  getComputerList(): void {
    console.log(this.shop);
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
      console.log(data)
    });
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

  toggleAddComputerForm(): void {
    this.isAddingComputer = !this.isAddingComputer;
  }

  addComputer(): void {
    this.isAddingComputer = false;
    this.http.put(`http://localhost:8080/api/shops/${this.shop.name}/computer_list/${this.newComputer.model}`, this.newComputer).subscribe(() => {
      this.computerList.push(this.newComputer.model);
      this.newComputer = { producer: "", model: "", memory: 0, mac_number: "" };
    });
  }

  cancelAddComputer(): void {
    this.isAddingComputer = false;
  }
  closeDetails(): void {
    this.showDetails = false;
  }


  viewComputerDetails(computerIndex: number): void {
    console.log(this.shop);
    // this.http.get<Computer>(`http://localhost:8080/api/shops/${this.shop.name}/computer_list/${this.computerList[computerIndex]}`).subscribe(shop => {
    //   this.currentComputer = shop;
    // });

    console.log(computerIndex)



    let url = `http://localhost:8080/api/shops/${this.shop.name}/computer_list/${this.computerList[computerIndex]}`;
    console.log(url);
    this.http.get<any>(url).subscribe(data => {
      this.currentComputer = Convert.fromJson(JSON.stringify(data));
    });
    this.currentComputerIndex = computerIndex;
    this.showDetails = true;
  }
}
