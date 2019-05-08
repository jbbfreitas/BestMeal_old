import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

import { BestMealCartaoCreditoModule } from './cartao-credito/cartao-credito.module';
import { BestMealCartaoRecompensaModule } from './cartao-recompensa/cartao-recompensa.module';
import { BestMealMenuModule } from './menu/menu.module';
import { BestMealProdutoModule } from './produto/produto.module';
import { BestMealLogradouroModule } from './logradouro/logradouro.module';
import { BestMealMunicipioModule } from './municipio/municipio.module';
import { BestMealNomeModule } from './nome/nome.module';
import { BestMealEnderecoModule } from './endereco/endereco.module';
import { CvvValidatorDirective } from '../shared/validators/cvv-validator.directive';

import { BestMealPessoaModule } from './pessoa/pessoa.module';
/* jhipster-needle-add-entity-module-import - JHipster will add entity modules imports here */

@NgModule({
    // prettier-ignore
    imports: [
        BestMealCartaoCreditoModule,
        BestMealCartaoRecompensaModule,
        BestMealMenuModule,
        BestMealProdutoModule,
        BestMealLogradouroModule,
        BestMealMunicipioModule,
        BestMealNomeModule,
        BestMealEnderecoModule,
        BestMealPessoaModule,
        /* jhipster-needle-add-entity-module - JHipster will add entity modules here */
    ],
    declarations: [CvvValidatorDirective],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class BestMealEntityModule {}
