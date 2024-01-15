import {Component, OnInit} from '@angular/core';
import {Participant} from "../model/participant";
import {ParticipantService} from "../service/participant-service.service";
import {CommonModule} from "@angular/common";
import {ParticipantFormComponent} from "../user-form/participant-form.component";
import {Pension} from "../model/pension";

@Component({
  selector: 'participant',
  standalone: true,
  imports: [CommonModule, ParticipantFormComponent],
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
