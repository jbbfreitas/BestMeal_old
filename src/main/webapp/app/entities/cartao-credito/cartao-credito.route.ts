import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { CartaoCredito } from 'app/shared/model/cartao-credito.model';
import { CartaoCreditoService } from './cartao-credito.service';
import { CartaoCreditoComponent } from './cartao-credito.component';
import { CartaoCreditoDetailComponent } from './cartao-credito-detail.component';
import { CartaoCreditoUpdateComponent } from './cartao-credito-update.component';
import { CartaoCreditoDeletePopupComponent } from './cartao-credito-delete-dialog.component';
import { ICartaoCredito } from 'app/shared/model/cartao-credito.model';

@Injectable({ providedIn: 'root' })
export class CartaoCreditoResolve implements Resolve<ICartaoCredito> {
    constructor(private service: CartaoCreditoService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((cartaoCredito: HttpResponse<CartaoCredito>) => cartaoCredito.body));
        }
        return of(new CartaoCredito());
    }
}

export const cartaoCreditoRoute: Routes = [
    {
        path: 'cartao-credito',
        component: CartaoCreditoComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'bestMealApp.cartaoCredito.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'cartao-credito/:id/view',
        component: CartaoCreditoDetailComponent,
        resolve: {
            cartaoCredito: CartaoCreditoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'bestMealApp.cartaoCredito.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'cartao-credito/new',
        component: CartaoCreditoUpdateComponent,
        resolve: {
            cartaoCredito: CartaoCreditoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'bestMealApp.cartaoCredito.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'cartao-credito/:id/edit',
        component: CartaoCreditoUpdateComponent,
        resolve: {
            cartaoCredito: CartaoCreditoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'bestMealApp.cartaoCredito.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const cartaoCreditoPopupRoute: Routes = [
    {
        path: 'cartao-credito/:id/delete',
        component: CartaoCreditoDeletePopupComponent,
        resolve: {
            cartaoCredito: CartaoCreditoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'bestMealApp.cartaoCredito.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
