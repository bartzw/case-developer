import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Participant } from '../models/participant';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ParticipantService {

  private readonly participantUrl: string;

  constructor(private http: HttpClient) {
    this.participantUrl = 'http://localhost:8080/deelnemers';
  }

  findById(id: number): Observable<Participant> {
    const url = `${this.participantUrl}/${id}`;
    return this.http.get<Participant>(url);
  }

}
