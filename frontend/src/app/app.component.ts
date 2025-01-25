import { Component } from '@angular/core';
import { HomeComponent } from './home/home.component';

@Component({
  selector: 'app-root',
  imports: [HomeComponent],
  template: `
  <main>
      <header class="brand-name">
        app.components.ts
      </header>
      <section class="content">
        <app-home></app-home>
      </section>
    </main>
  `,
  // templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'frontend';
}
