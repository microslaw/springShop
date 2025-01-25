import { Component, Input, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Computer, Convert } from '../../interfaces/computer';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  imports: [CommonModule, FormsModule],
  selector: 'app-computer',
  template: `
    <div>
      <h3>Computer Details</h3>
      <p>Model: {{ computer.model }}</p>
      <p>Producer: {{ computer.producer }}</p>
      <p>Memory: {{ computer.memory }}</p>
      <p>Mac Number: {{ computer.mac_number }}</p>
    </div>
  `,
  styleUrl: './computer.component.css'
})
export class ComputerComponent {
  @Input() computer: Computer = { producer: "", model: "", memory: 0, mac_number: "" };
}
