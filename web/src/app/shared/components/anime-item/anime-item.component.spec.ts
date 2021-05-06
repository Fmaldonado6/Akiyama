import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AnimeItemComponent } from './anime-item.component';

describe('AnimeItemComponent', () => {
  let component: AnimeItemComponent;
  let fixture: ComponentFixture<AnimeItemComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AnimeItemComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AnimeItemComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
