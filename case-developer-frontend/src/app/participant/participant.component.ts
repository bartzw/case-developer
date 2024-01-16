import {Component, OnInit} from '@angular/core';
import {Participant} from "../models/participant";
import {ParticipantService} from "../services/participant-service.service";
import {CommonModule} from "@angular/common";
import {PensionFormComponent} from "../pension-form/pension-form.component";
import {Pension} from "../models/pension";

@Component({
  selector: 'participant',
  standalone: true,
  imports: [CommonModule, PensionFormComponent],
  templateUrl: './participant.component.html',
})
export class ParticipantComponent implements OnInit {

  participant: Participant | undefined;
  pension: Pension;

  constructor(private participantService: ParticipantService) {
    this.pension = new Pension()
  }

  ngOnInit() {
    this.participantService.findById(1).subscribe(data => {
      this.participant = data;
    })
  }

  handlePension(pension: Pension) {
    this.pension = pension;
  }

}
