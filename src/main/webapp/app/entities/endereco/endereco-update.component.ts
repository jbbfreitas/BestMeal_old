import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { IEndereco } from 'app/shared/model/endereco.model';
import { EnderecoService } from './endereco.service';
import { ILogradouro } from 'app/shared/model/logradouro.model';
import { LogradouroService } from 'app/entities/logradouro';
import { IMunicipio } from 'app/shared/model/municipio.model';
import { MunicipioService } from 'app/entities/municipio';

@Component({
    selector: 'jhi-endereco-update',
    templateUrl: './endereco-update.component.html'
})
export class EnderecoUpdateComponent implements OnInit {
    endereco: IEndereco;
    isSaving: boolean;

    logradouros: ILogradouro[];

    municipios: IMunicipio[];

    constructor(
        private jhiAlertService: JhiAlertService,
        private enderecoService: EnderecoService,
        private logradouroService: LogradouroService,
        private municipioService: MunicipioService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ endereco }) => {
            this.endereco = endereco;
        });
        this.logradouroService.query().subscribe(
            (res: HttpResponse<ILogradouro[]>) => {
                this.logradouros = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.municipioService.query().subscribe(
            (res: HttpResponse<IMunicipio[]>) => {
                this.municipios = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.endereco.id !== undefined) {
            this.subscribeToSaveResponse(this.enderecoService.update(this.endereco));
        } else {
            this.subscribeToSaveResponse(this.enderecoService.create(this.endereco));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IEndereco>>) {
        result.subscribe((res: HttpResponse<IEndereco>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }

    trackLogradouroById(index: number, item: ILogradouro) {
        return item.id;
    }

    trackMunicipioById(index: number, item: IMunicipio) {
        return item.id;
    }
}
