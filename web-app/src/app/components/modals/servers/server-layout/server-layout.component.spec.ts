import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ServerLayoutComponent } from './server-layout.component';

describe('ServerLayoutComponent', () => {
  let component: ServerLayoutComponent;
  let fixture: ComponentFixture<ServerLayoutComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ServerLayoutComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ServerLayoutComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
