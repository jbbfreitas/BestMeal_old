export const enum UF {
    MT = 'MT',
    GO = 'GO',
    MS = 'MS',
    AM = 'AM',
    PA = 'PA',
    PR = 'PR',
    RN = 'RN',
    RS = 'RS',
    MG = 'MG',
    DF = 'DF',
    PB = 'PB',
    AL = 'AL',
    SE = 'SE',
    PI = 'PI',
    MA = 'MA',
    RO = 'RO',
    RR = 'RR',
    AP = 'AP',
    AC = 'AC',
    TO = 'TO',
    ES = 'ES',
    SC = 'SC',
    BA = 'BA',
    PE = 'PE'
}

export interface IMunicipio {
    id?: number;
    nome?: string;
    uf?: UF;
}

export class Municipio implements IMunicipio {
    constructor(public id?: number, public nome?: string, public uf?: UF) {}
}
