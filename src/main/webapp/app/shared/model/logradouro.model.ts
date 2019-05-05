export const enum TipoLogradouro {
    RUA = 'RUA',
    AVENIDA = 'AVENIDA',
    TRAVESSA = 'TRAVESSA',
    QUADRA = 'QUADRA',
    BECO = 'BECO',
    ESTRADA = 'ESTRADA',
    CHACARA = 'CHACARA',
    RODOVIA = 'RODOVIA',
    VIADUTO = 'VIADUTO',
    SITIO = 'SITIO',
    FEIRA = 'FEIRA',
    SETOR = 'SETOR',
    MORRO = 'MORRO',
    LARGO = 'LARGO',
    FAZENDA = 'FAZENDA'
}

export interface ILogradouro {
    id?: number;
    tipo?: TipoLogradouro;
    nome?: string;
}

export class Logradouro implements ILogradouro {
    constructor(public id?: number, public tipo?: TipoLogradouro, public nome?: string) {}
}
