import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { BestMealSharedModule } from 'app/shared';
import {
    CartaoRecompensaComponent,
    CartaoRecompensaDetailComponent,
    CartaoRecompensaUpdateComponent,
    CartaoRecompensaDeletePopupComponent,
    CartaoRecompensaDeleteDialogComponent,
    cartaoRecompensaRoute,
    cartaoRecompensaPopupRoute
} from './';

const ENTITY_STATES = [...cartaoRecompensaRoute, ...cartaoRecompensaPopupRoute];

@NgModule({
    imports: [BestMealSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        CartaoRecompensaComponent,
        CartaoRecompensaDetailComponent,
        CartaoRecompensaUpdateComponent,
        CartaoRecompensaDeleteDialogComponent,
        CartaoRecompensaDeletePopupComponent
    ],
    entryComponents: [
        CartaoRecompensaComponent,
        CartaoRecompensaUpdateComponent,
        CartaoRecompensaDeleteDialogComponent,
        CartaoRecompensaDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class BestMealCartaoRecompensaModule {}
