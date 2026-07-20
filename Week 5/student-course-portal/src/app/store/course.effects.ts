import { Injectable, inject } from '@angular/core';
import { Actions, createEffect, ofType } from '@ngrx/effects';
import { map, catchError, of } from 'rxjs';

import * as CourseActions from './course.actions';
import { CourseService } from '../services/course.service';

@Injectable()
export class CourseEffects {

  private actions$ = inject(Actions);
  private courseService = inject(CourseService);

  loadCourses$ = createEffect(() =>
    this.actions$.pipe(
      ofType(CourseActions.loadCourses),
      map(() => {
        const courses = this.courseService.getCourses();
        return CourseActions.loadCoursesSuccess({ courses });
      }),
      catchError(() =>
        of(
          CourseActions.loadCoursesFailure({
            error: 'Failed to load courses'
          })
        )
      )
    )
  );
}