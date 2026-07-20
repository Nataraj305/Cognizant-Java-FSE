import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ActivatedRoute, RouterLink } from '@angular/router';

import { Course } from '../../models/course';
import { CourseService } from '../../services/course.service';

@Component({
  selector: 'app-course-details',
  standalone: true,
  imports: [CommonModule, RouterLink],
  templateUrl: './course-details.component.html',
  styleUrl: './course-details.component.css'
})
export class CourseDetailsComponent implements OnInit {

  course?: Course;

  constructor(
    private route: ActivatedRoute,
    private courseService: CourseService
  ) {}

  ngOnInit(): void {
    const id = Number(this.route.snapshot.paramMap.get('id'));
    this.course = this.courseService.getCourseById(id);
  }

}