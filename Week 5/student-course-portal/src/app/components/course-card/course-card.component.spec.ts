import { ComponentFixture, TestBed } from '@angular/core/testing';
import { SimpleChange } from '@angular/core';
import { By } from '@angular/platform-browser';

import { CourseCardComponent } from './course-card.component';
import { Course } from '../../models/course';

describe('CourseCardComponent', () => {
  let component: CourseCardComponent;
  let fixture: ComponentFixture<CourseCardComponent>;

  const mockCourse: Course = {
    id: 1,
    name: 'Data Structures',
    code: 'CS101',
    credits: 4,
    gradeStatus: 'passed',
  };

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CourseCardComponent],
    }).compileComponents();

    fixture = TestBed.createComponent(CourseCardComponent);
    component = fixture.componentInstance;
  });

  it('should create', () => {
    component.course = mockCourse;
    fixture.detectChanges();

    expect(component).toBeTruthy();
  });

  it('should display the course name', () => {
    component.course = mockCourse;
    fixture.detectChanges();

    const heading = fixture.debugElement.query(By.css('h3')).nativeElement;
    expect(heading.textContent).toContain('Data Structures');
  });

  it('should emit enroll event when not enrolled', () => {
    component.course = mockCourse;
    component.isEnrolled = false;

    spyOn(component.enroll, 'emit');

    fixture.detectChanges();

    const button = fixture.debugElement.query(By.css('button')).nativeElement;
    button.click();

    expect(component.enroll.emit).toHaveBeenCalledWith(mockCourse);
  });

  it('should emit unenroll event when already enrolled', () => {
    component.course = mockCourse;
    component.isEnrolled = true;

    spyOn(component.unenroll, 'emit');

    fixture.detectChanges();

    const button = fixture.debugElement.query(By.css('button')).nativeElement;
    button.click();

    expect(component.unenroll.emit).toHaveBeenCalledWith(mockCourse);
  });

  it('should call ngOnChanges', () => {
    const consoleSpy = spyOn(console, 'log');

    component.course = mockCourse;

    component.ngOnChanges({
      course: new SimpleChange(null, mockCourse, true),
    });

    expect(consoleSpy).toHaveBeenCalled();
  });
});
