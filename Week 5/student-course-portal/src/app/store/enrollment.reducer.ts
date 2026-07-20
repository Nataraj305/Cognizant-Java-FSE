import { createReducer, on } from '@ngrx/store';

import * as EnrollmentActions from './enrollment.actions';
import {
  EnrollmentState,
  initialEnrollmentState
} from './enrollment.state';

export const enrollmentReducer = createReducer(

  initialEnrollmentState,

  on(EnrollmentActions.enrollInCourse, (state, { courseId }) => ({
    ...state,
    enrolledCourseIds: [...state.enrolledCourseIds, courseId]
  })),

  on(EnrollmentActions.unenrollFromCourse, (state, { courseId }) => ({
    ...state,
    enrolledCourseIds: state.enrolledCourseIds.filter(id => id !== courseId)
  })),

  on(EnrollmentActions.setEnrolledCourses, (state, { courseIds }) => ({
    ...state,
    enrolledCourseIds: courseIds
  }))

);