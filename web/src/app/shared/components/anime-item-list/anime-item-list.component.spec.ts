import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AnimeItemListComponent } from './anime-item-list.component';

describe('AnimeItemListComponent', () => {
  let component: AnimeItemListComponent;
  let fixture: ComponentFixture<AnimeItemListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AnimeItemListComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AnimeItemListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
