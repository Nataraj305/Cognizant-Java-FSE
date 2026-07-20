import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute, convertToParamMap } from '@angular/router';

import { CourseDetailsComponent } from './course-details.component';

describe('CourseDetailsComponent', () => {
  let component: CourseDetailsComponent;
  let fixture: ComponentFixture<CourseDetailsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CourseDetailsComponent],
      providers: [
        {
          provide: ActivatedRoute,
          useValue: {
            snapshot: {
              paramMap: convertToParamMap({ id: '1' })
            }
          }
        }
      ]
    }).compileComponents();

    fixture = TestBed.createComponent(CourseDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});