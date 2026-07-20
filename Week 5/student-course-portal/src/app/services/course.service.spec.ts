import { TestBed } from '@angular/core/testing';

import { CourseService } from './course.service';

describe('CourseService', () => {
  let service: CourseService;

  beforeEach(() => {
    TestBed.configureTestingModule({});

    service = TestBed.inject(CourseService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('should return all courses', () => {
    const courses = service.getCourses();

    expect(courses.length).toBe(5);
    expect(courses[0].name).toBe('Java Programming');
  });

  it('should return a course by id', () => {
    const course = service.getCourseById(2);

    expect(course).toBeTruthy();
    expect(course?.name).toBe('Database Management Systems');
  });

  it('should add a new course', () => {
    const initialLength = service.getCourses().length;

    service.addCourse({
      id: 6,
      name: 'Angular',
      code: 'CS106',
      credits: 3,
      gradeStatus: 'pending'
    });

    expect(service.getCourses().length).toBe(initialLength + 1);
  });
});