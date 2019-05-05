import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

import { BestMealCartaoCreditoModule } from './cartao-credito/cartao-credito.module';
import { BestMealCartaoRecompensaModule } from './cartao-recompensa/cartao-recompensa.module';
import { BestMealMenuModule } from './menu/menu.module';
import { BestMealProdutoModule } from './produto/produto.module';
/* jhipster-needle-add-entity-module-import - JHipster will add entity modules imports here */

@NgModule({
    // prettier-ignore
    imports: [
        BestMealCartaoCreditoModule,
        BestMealCartaoRecompensaModule,
        BestMealMenuModule,
        BestMealProdutoModule,
        /* jhipster-needle-add-entity-module - JHipster will add entity modules here */
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class BestMealEntityModule {}
