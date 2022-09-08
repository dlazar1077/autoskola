import { Pitanje } from "./pitanje";

export class Ispit{

    ispitId : number | undefined;
    korisnikId : number | undefined;
    maksimalniBrojBodova !: number;
    ostvareniBrojBodova !: number ;
    statusIspita : String | undefined;

    pitanja : Pitanje[] | undefined;
}