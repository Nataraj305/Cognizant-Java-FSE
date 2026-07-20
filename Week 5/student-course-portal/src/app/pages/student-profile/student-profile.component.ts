import { Component } from '@angular/core';
import { EnrollmentService } from '../../services/enrollment.service';
import { Course } from '../../models/course';

@Component({
  selector: 'app-student-profile',
  imports: [],
  templateUrl: './student-profile.component.html',
  styleUrl: './student-profile.component.css'
})
export class StudentProfileComponent {

  constructor(private enrollmentService: EnrollmentService) {}

  get enrolledCourses(): Course[] {
    return this.enrollmentService.getEnrolledCourses();
  }
}