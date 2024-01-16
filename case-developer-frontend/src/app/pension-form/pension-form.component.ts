import { Component, Output, EventEmitter } from '@angular/core';

import {PensionService} from "../services/pension-service.service";
import {FormsModule} from "@angular/forms";
import {Pension} from "../models/pension";

@Component({
  selector: 'pension-form',
  template: `
    <form (ngSubmit)="submitForm()">
      <div class="form-group">
      <label for="pensionAge">Verwachte pensioen leeftijd</label>
      <input type="number" class="form-control" id="pensionAge" name="pensionAge" [(ngModel)]="pensionAge" required>
      <button type="submit" class="btn btn-primary">Submit</button>
      </div>
    </form>
  `,
  standalone: true,
  imports: [
    FormsModule
  ],
})
export class PensionFormComponent {

  pensionAge: number;
  @Output() pension = new EventEmitter<Pension>();


  constructor(private pensionService: PensionService, ) {
    this.pensionAge = 0;
  }

  submitForm() {
    this.pensionService.findById(1, this.pensionAge).subscribe(data => {
          this.pension.emit(data);
        });
  }
}
