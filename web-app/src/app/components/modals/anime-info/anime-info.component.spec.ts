import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AnimeInfoModal } from './anime-info.component';

describe('AnimeInfoComponent', () => {
  let component: AnimeInfoModal;
  let fixture: ComponentFixture<AnimeInfoModal>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AnimeInfoModal ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AnimeInfoModal);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
