import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { BestMealSharedModule } from 'app/shared';
import {
    LogradouroComponent,
    LogradouroDetailComponent,
    LogradouroUpdateComponent,
    LogradouroDeletePopupComponent,
    LogradouroDeleteDialogComponent,
    logradouroRoute,
    logradouroPopupRoute
} from './';

const ENTITY_STATES = [...logradouroRoute, ...logradouroPopupRoute];

@NgModule({
    imports: [BestMealSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        LogradouroComponent,
        LogradouroDetailComponent,
        LogradouroUpdateComponent,
        LogradouroDeleteDialogComponent,
        LogradouroDeletePopupComponent
    ],
    entryComponents: [LogradouroComponent, LogradouroUpdateComponent, LogradouroDeleteDialogComponent, LogradouroDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class BestMealLogradouroModule {}
