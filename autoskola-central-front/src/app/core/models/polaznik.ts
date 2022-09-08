import { Instruktor } from "./instruktor";
import { Korisnik } from "./korisnik";
import { Vozilo } from "./vozilo";

export class Polaznik{

    polaznikId : number | undefined;
    instruktorId : number | undefined;
    korisnikId : number | undefined;
    statusPolaznikaId: number |undefined;
    odabranaKategorijaId: number |undefined;

    korisnik : Korisnik  = new Korisnik();
    instruktor : Instruktor | undefined;
}