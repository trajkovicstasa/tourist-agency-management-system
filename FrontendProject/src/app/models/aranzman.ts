import { Hotel } from "./hotel";
import { TuristickaAgencija } from "./turistickaAgencija";

export class Aranzman { // export znaci da klasu mogu da koristim u drugim fajlovima tako asto je uvozim preko importa
    id!:number;
    ukupnaCena!:number;
    placeno!:boolean;
    datumRealizacije!:Date;
    hotel!:Hotel;
    agencija!:TuristickaAgencija;

// ! = definite assignment assertion
// ! - kaze typescriptu ovo polje ce sigurno dobiti vrednost pre koriscenja


}