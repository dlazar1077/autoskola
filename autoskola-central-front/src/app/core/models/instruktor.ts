import { Korisnik } from "./korisnik";
import { Vozilo } from "./vozilo";

export class Instruktor{

    instruktorId : number | undefined;
    korisnikId : number | undefined;
    brojSlobodnihMjesta!: number;

    korisnik : Korisnik  = new Korisnik();
    vozila !: Vozilo[] ;
}