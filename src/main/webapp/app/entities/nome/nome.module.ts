import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { BestMealSharedModule } from 'app/shared';
import {
    NomeComponent,
    NomeDetailComponent,
    NomeUpdateComponent,
    NomeDeletePopupComponent,
    NomeDeleteDialogComponent,
    nomeRoute,
    nomePopupRoute
} from './';

const ENTITY_STATES = [...nomeRoute, ...nomePopupRoute];

@NgModule({
    imports: [BestMealSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [NomeComponent, NomeDetailComponent, NomeUpdateComponent, NomeDeleteDialogComponent, NomeDeletePopupComponent],
    entryComponents: [NomeComponent, NomeUpdateComponent, NomeDeleteDialogComponent, NomeDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class BestMealNomeModule {}
