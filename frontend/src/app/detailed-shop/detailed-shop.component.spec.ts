import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DetailedShopComponent } from './detailed-shop.component';

describe('DetailedShopComponent', () => {
  let component: DetailedShopComponent;
  let fixture: ComponentFixture<DetailedShopComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [DetailedShopComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DetailedShopComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
