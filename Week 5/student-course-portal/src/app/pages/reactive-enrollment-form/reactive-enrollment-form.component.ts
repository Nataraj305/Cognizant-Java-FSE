import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import {
  FormArray,
  FormBuilder,
  FormGroup,
  ReactiveFormsModule,
  Validators,
  AbstractControl,
  ValidationErrors,
  AsyncValidatorFn
} from '@angular/forms';
import { Observable, of } from 'rxjs';
import { delay, map } from 'rxjs/operators';

@Component({
  selector: 'app-reactive-enrollment-form',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './reactive-enrollment-form.component.html',
  styleUrl: './reactive-enrollment-form.component.css'
})
export class ReactiveEnrollmentFormComponent implements OnInit {

  enrollForm!: FormGroup;

  constructor(private fb: FormBuilder) {}

  ngOnInit(): void {

    this.enrollForm = this.fb.group({

      studentName: [
        '',
        [
          Validators.required,
          Validators.minLength(3),
          this.noNumbersValidator
        ]
      ],

      studentEmail: [
        '',
        [Validators.required, Validators.email],
        [this.emailExistsValidator()]
      ],

      courseId: [null, Validators.required],

      preferredSemester: ['Odd', Validators.required],

      agreeToTerms: [false, Validators.requiredTrue],

      skills: this.fb.array([
        this.fb.control('')
      ])

    });

  }

  get skills(): FormArray {
    return this.enrollForm.get('skills') as FormArray;
  }

  addSkill() {
    this.skills.push(this.fb.control(''));
  }

  removeSkill(index: number) {
    this.skills.removeAt(index);
  }

  onSubmit() {
    console.log(this.enrollForm.value);
    console.log(this.enrollForm.getRawValue());
  }

  noNumbersValidator(control: AbstractControl): ValidationErrors | null {

    const value = control.value;

    if (/\d/.test(value)) {
      return { containsNumber: true };
    }

    return null;
  }

  emailExistsValidator(): AsyncValidatorFn {

    return (control: AbstractControl): Observable<ValidationErrors | null> => {

      return of(control.value).pipe(

        delay(1000),

        map(email => {

          if (email === 'admin@test.com') {
            return { emailTaken: true };
          }

          return null;

        })

      );

    };

  }

}