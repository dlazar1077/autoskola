import { Instruktor } from "./instruktor";
import { Korisnik } from "./korisnik";
import { Sat } from "./sat";
import { Vozilo } from "./vozilo";

export class Polaznik{

    polaznikId !: number ;
    instruktorId : number | undefined;
    korisnikId : number | undefined;
    statusPolaznikaId: number |undefined;
    odabranaKategorijaId: number |undefined;

    korisnik : Korisnik  = new Korisnik();
    instruktor : Instruktor | undefined;
    dnevnikVoznje !: Sat[];
}