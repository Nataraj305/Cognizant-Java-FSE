import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';

import { Store } from '@ngrx/store';

import { Course } from '../../models/course';
import { CourseCardComponent } from '../../components/course-card/course-card.component';

import * as CourseActions from '../../store/course.actions';
import * as CourseSelectors from '../../store/course.selectors';

import * as EnrollmentActions from '../../store/enrollment.actions';
import * as EnrollmentSelectors from '../../store/enrollment.selectors';

@Component({
  selector: 'app-course-list',
  standalone: true,
  imports: [
    CommonModule,
    CourseCardComponent
  ],
  templateUrl: './course-list.component.html',
  styleUrl: './course-list.component.css'
})
export class CourseListComponent implements OnInit {

  courses$!: Observable<Course[]>;
  loading$!: Observable<boolean>;
  enrolledIds$!: Observable<number[]>;

  enrolledIds: number[] = [];

  constructor(
    private store: Store,
    private router: Router
  ) {}

  ngOnInit(): void {

    this.courses$ = this.store.select(CourseSelectors.selectAllCourses);
    this.loading$ = this.store.select(CourseSelectors.selectLoading);

    this.enrolledIds$ = this.store.select(
      EnrollmentSelectors.selectEnrolledIds
    );

    this.enrolledIds$.subscribe(ids => {
      this.enrolledIds = ids;
    });

    this.store.dispatch(CourseActions.loadCourses());
  }

  trackByCourseId(index: number, course: Course): number {
    return course.id;
  }

  isCourseEnrolled(courseId: number): boolean {
    return this.enrolledIds.includes(courseId);
  }

  onCourseEnroll(course: Course): void {
    this.store.dispatch(
      EnrollmentActions.enrollInCourse({
        courseId: course.id
      })
    );
  }

  onCourseUnenroll(course: Course): void {
    this.store.dispatch(
      EnrollmentActions.unenrollFromCourse({
        courseId: course.id
      })
    );
  }

  viewCourse(courseId: number): void {
    this.router.navigate(['/courses', courseId]);
  }
}