import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { BestMealSharedModule } from 'app/shared';
import {
    CartaoCreditoComponent,
    CartaoCreditoDetailComponent,
    CartaoCreditoUpdateComponent,
    CartaoCreditoDeletePopupComponent,
    CartaoCreditoDeleteDialogComponent,
    cartaoCreditoRoute,
    cartaoCreditoPopupRoute
} from './';

const ENTITY_STATES = [...cartaoCreditoRoute, ...cartaoCreditoPopupRoute];

@NgModule({
    imports: [BestMealSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        CartaoCreditoComponent,
        CartaoCreditoDetailComponent,
        CartaoCreditoUpdateComponent,
        CartaoCreditoDeleteDialogComponent,
        CartaoCreditoDeletePopupComponent
    ],
    entryComponents: [
        CartaoCreditoComponent,
        CartaoCreditoUpdateComponent,
        CartaoCreditoDeleteDialogComponent,
        CartaoCreditoDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class BestMealCartaoCreditoModule {}
