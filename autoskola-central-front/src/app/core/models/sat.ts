import { Vozilo } from "./vozilo";

export class Sat{
    satVoznjeId !: number;
    polaznikId !: number;
    voziloId !: number;
    brojSata !: number;
    opis !: string;
    datum !: string;

    vozilo !: Vozilo;
}