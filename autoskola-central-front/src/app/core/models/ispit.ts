import { Korisnik } from "./korisnik";
import { Pitanje } from "./pitanje";

export class Ispit{

    ispitId : number | undefined;
    korisnikId : number | undefined;
    maksimalniBrojBodova !: number;
    ostvareniBrojBodova !: number ;
    statusIspita !: string;
    creationDate !: string;

    pitanja : Pitanje[] | undefined;
    korisnik !: Korisnik;
}