import { Component, Output, EventEmitter } from '@angular/core';

import {PensionService} from "../service/pension-service.service";
import {FormsModule} from "@angular/forms";
import {Pension} from "../model/pension";

@Component({
  selector: 'pension-form',
  template: `
    <form (ngSubmit)="submitForm()">
      <label for="pensionage">Pension Age:</label>
      <input type="number" id="pensionage" name="pensionage" [(ngModel)]="pensionAge" required>
      <button type="submit">Submit</button>
    </form>
  `,
  standalone: true,
  imports: [
    FormsModule
  ],
})
export class ParticipantFormComponent {

  pensionAge: number;
  @Output() pension = new EventEmitter<Pension>();


  constructor(private pensionService: PensionService, ) {
    this.pensionAge = 0;
  }

  submitForm() {
    this.pensionService.findById(1, this.pensionAge).subscribe(
        (pension: Pension) => {
          // Emit the pension number to the parent component
          this.pension.emit(pension);
        },
        (error) => {
          console.error('Error fetching pension number:', error);
          // Handle errors as needed
        }
    );
  }
}
