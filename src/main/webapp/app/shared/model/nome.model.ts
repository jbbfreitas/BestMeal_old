export interface INome {
    id?: number;
    primeiroNome?: string;
    nomeMeio?: string;
    sobreNome?: string;
    saudacao?: string;
    titulo?: string;
}

export class Nome implements INome {
    constructor(
        public id?: number,
        public primeiroNome?: string,
        public nomeMeio?: string,
        public sobreNome?: string,
        public saudacao?: string,
        public titulo?: string
    ) {}
}
