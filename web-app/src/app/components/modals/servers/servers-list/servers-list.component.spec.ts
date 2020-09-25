import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ServersListComponent } from './servers-list.component';

describe('ServersListComponent', () => {
  let component: ServersListComponent;
  let fixture: ComponentFixture<ServersListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ServersListComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ServersListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
