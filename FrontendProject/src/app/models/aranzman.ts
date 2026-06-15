import { Hotel } from "./hotel";
import { TuristickaAgencija } from "./turistickaAgencija";

export class Aranzman {
    id!:number;
    ukupnaCena!:number;
    placeno!:boolean;
    datumRealizacije!:Date;
    hotel!:Hotel;
    agencija!:TuristickaAgencija;




}