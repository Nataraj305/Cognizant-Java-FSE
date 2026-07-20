import { ApplicationConfig, isDevMode, provideZoneChangeDetection } from '@angular/core';
import { provideRouter } from '@angular/router';

import { routes } from './app.routes';

import { provideStore } from '@ngrx/store';
import { provideEffects } from '@ngrx/effects';
import { provideStoreDevtools } from '@ngrx/store-devtools';

import { courseReducer } from './store/course.reducer';
import { enrollmentReducer } from './store/enrollment.reducer';
import { CourseEffects } from './store/course.effects';

export const appConfig: ApplicationConfig = {
  providers: [
    provideZoneChangeDetection({ eventCoalescing: true }),
    provideRouter(routes),

    provideStore({
      courses: courseReducer
    }),

    provideEffects([CourseEffects]),

    provideStoreDevtools({
      maxAge: 25,
      logOnly: !isDevMode()
    })
  ]
};