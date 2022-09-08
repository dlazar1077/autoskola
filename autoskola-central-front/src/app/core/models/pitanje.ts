import { Odabir } from "./odabir";

export class Pitanje{

    pitanjeId : number | undefined;
    tekstPitanja : String | undefined;
    slika : String | undefined;
    raskrizje : boolean | undefined;
    brojBodova !: number;
    odgovor !: String ;

    odabiri !: Odabir[] ;

}