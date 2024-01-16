import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import {Pension} from "../models/pension";

@Injectable({
  providedIn: 'root'
})
export class PensionService {

  private readonly pensionUrl: string;

  constructor(private http: HttpClient) {
    this.pensionUrl = 'http://localhost:8080/pension';
  }

  findById(id: number, pensionAge: number): Observable<Pension> {
    const url = `${this.pensionUrl}/${id}`;
    const params = {verwachtePensioenLeeftijd: pensionAge}
    return this.http.get<Pension>(url, {params});
  }

}
