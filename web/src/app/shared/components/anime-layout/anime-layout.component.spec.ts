import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AnimeLayoutComponent } from './anime-layout.component';

describe('AnimeLayoutComponent', () => {
  let component: AnimeLayoutComponent;
  let fixture: ComponentFixture<AnimeLayoutComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AnimeLayoutComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AnimeLayoutComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
