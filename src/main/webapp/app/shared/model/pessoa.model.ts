import { INome } from 'app/shared/model//nome.model';
import { IEndereco } from 'app/shared/model//endereco.model';

export const enum TipoPessoa {
    FISICA = 'FISICA',
    JURIDICA = 'JURIDICA'
}

export interface IPessoa {
    id?: number;
    tipo?: TipoPessoa;
    cpf?: string;
    cnpj?: string;
    nome?: INome;
    endereco?: IEndereco;
}

export class Pessoa implements IPessoa {
    constructor(
        public id?: number,
        public tipo?: TipoPessoa,
        public cpf?: string,
        public cnpj?: string,
        public nome?: INome,
        public endereco?: IEndereco
    ) {}
}
