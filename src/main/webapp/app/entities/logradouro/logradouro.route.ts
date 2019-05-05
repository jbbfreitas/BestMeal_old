import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { Logradouro } from 'app/shared/model/logradouro.model';
import { LogradouroService } from './logradouro.service';
import { LogradouroComponent } from './logradouro.component';
import { LogradouroDetailComponent } from './logradouro-detail.component';
import { LogradouroUpdateComponent } from './logradouro-update.component';
import { LogradouroDeletePopupComponent } from './logradouro-delete-dialog.component';
import { ILogradouro } from 'app/shared/model/logradouro.model';

@Injectable({ providedIn: 'root' })
export class LogradouroResolve implements Resolve<ILogradouro> {
    constructor(private service: LogradouroService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((logradouro: HttpResponse<Logradouro>) => logradouro.body));
        }
        return of(new Logradouro());
    }
}

export const logradouroRoute: Routes = [
    {
        path: 'logradouro',
        component: LogradouroComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'bestMealApp.logradouro.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'logradouro/:id/view',
        component: LogradouroDetailComponent,
        resolve: {
            logradouro: LogradouroResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'bestMealApp.logradouro.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'logradouro/new',
        component: LogradouroUpdateComponent,
        resolve: {
            logradouro: LogradouroResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'bestMealApp.logradouro.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'logradouro/:id/edit',
        component: LogradouroUpdateComponent,
        resolve: {
            logradouro: LogradouroResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'bestMealApp.logradouro.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const logradouroPopupRoute: Routes = [
    {
        path: 'logradouro/:id/delete',
        component: LogradouroDeletePopupComponent,
        resolve: {
            logradouro: LogradouroResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'bestMealApp.logradouro.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
