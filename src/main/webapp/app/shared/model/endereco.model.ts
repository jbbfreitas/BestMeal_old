import { ILogradouro } from 'app/shared/model//logradouro.model';
import { IMunicipio } from 'app/shared/model//municipio.model';

export interface IEndereco {
    id?: number;
    cep?: string;
    complemento?: string;
    logradouro?: ILogradouro;
    municipio?: IMunicipio;
}

export class Endereco implements IEndereco {
    constructor(
        public id?: number,
        public cep?: string,
        public complemento?: string,
        public logradouro?: ILogradouro,
        public municipio?: IMunicipio
    ) {}
}
