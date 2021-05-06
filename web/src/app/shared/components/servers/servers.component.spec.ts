import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ServersComponent } from './servers.component';

describe('ServersComponent', () => {
  let component: ServersComponent;
  let fixture: ComponentFixture<ServersComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ServersComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ServersComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
