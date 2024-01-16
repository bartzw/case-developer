import { Routes } from '@angular/router';
import {ParticipantComponent} from "./participant/participant.component";

export const routes: Routes = [
    { path: '', redirectTo: '/participant', pathMatch: 'full' },
    { path: 'participant', component: ParticipantComponent },
];
