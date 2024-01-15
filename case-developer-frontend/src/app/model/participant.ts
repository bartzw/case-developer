import {Employment} from "./employment";

export class Participant {
    id: number;
    name: string;
    address: string;
    placeOfBirth: string;
    isEmployed: boolean;
    dateOfBirth: Date | null;
    email: string;
    employment: Employment;
    pensionAccountNumber: number;
    pension: number;


    constructor() {
        this.id = 0;
        this.name = '';
        this.address = '';
        this.placeOfBirth = '';
        this.isEmployed = false;
        this.dateOfBirth = null;
        this.email = '';
        this.employment = new Employment();
        this.pensionAccountNumber = 0;
        this.pension = 0;
    }
}
