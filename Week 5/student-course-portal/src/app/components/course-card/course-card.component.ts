import {
  Component,
  Input,
  Output,
  EventEmitter,
  OnChanges,
  SimpleChanges
} from '@angular/core';
import { CommonModule } from '@angular/common';

import { Course } from '../../models/course';
import { CreditLabelPipe } from '../../pipes/credit-label.pipe';
import { HighlightDirective } from '../../directives/highlight.directive';

@Component({
  selector: 'app-course-card',
  standalone: true,
  imports: [
    CommonModule,
    CreditLabelPipe,
    HighlightDirective
  ],
  templateUrl: './course-card.component.html',
  styleUrl: './course-card.component.css'
})
export class CourseCardComponent implements OnChanges {

  @Input() course!: Course;

  @Input() isEnrolled = false;

  @Output() enroll = new EventEmitter<Course>();

  @Output() unenroll = new EventEmitter<Course>();

  isExpanded = false;

  ngOnChanges(changes: SimpleChanges): void {
    console.log('Course changed:', changes);
  }

  toggleEnrollment(): void {
    if (this.isEnrolled) {
      this.unenroll.emit(this.course);
    } else {
      this.enroll.emit(this.course);
    }
  }

  toggleDetails(): void {
    this.isExpanded = !this.isExpanded;
  }

  get cardClasses() {
    return {
      'card--enrolled': this.isEnrolled,
      'card--full': this.course.credits >= 4,
      expanded: this.isExpanded
    };
  }

}