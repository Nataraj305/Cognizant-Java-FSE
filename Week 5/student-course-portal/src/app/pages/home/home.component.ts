import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Course } from '../../models/course';
import { CourseCardComponent } from '../../components/course-card/course-card.component';
import { CourseService } from '../../services/course.service';

@Component({
  selector: 'app-home',
  imports: [FormsModule,CourseCardComponent],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent implements OnInit {

  portalName = 'Student Course Portal';
  isPortalActive = true;
  message = '';
  searchTerm = '';
  courses: Course[] = [];
  constructor(private courseService: CourseService) {}

  onEnrollClick() {
    this.message = 'Enrollment opened!';
  }
  onCourseEnroll(course: Course) {
  alert('Enrolled in ' + course.name);
}

  ngOnInit(): void {
  this.courses = this.courseService.getCourses();
  console.log('HomeComponent initialized');
}
}