import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { IPessoa } from 'app/shared/model/pessoa.model';
import { PessoaService } from './pessoa.service';
import { INome } from 'app/shared/model/nome.model';
import { NomeService } from 'app/entities/nome';
import { IEndereco } from 'app/shared/model/endereco.model';
import { EnderecoService } from 'app/entities/endereco';

@Component({
    selector: 'jhi-pessoa-update',
    templateUrl: './pessoa-update.component.html'
})
export class PessoaUpdateComponent implements OnInit {
    pessoa: IPessoa;
    isSaving: boolean;

    nomes: INome[];

    enderecos: IEndereco[];

    constructor(
        private jhiAlertService: JhiAlertService,
        private pessoaService: PessoaService,
        private nomeService: NomeService,
        private enderecoService: EnderecoService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ pessoa }) => {
            this.pessoa = pessoa;
        });
        this.nomeService.query().subscribe(
            (res: HttpResponse<INome[]>) => {
                this.nomes = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.enderecoService.query().subscribe(
            (res: HttpResponse<IEndereco[]>) => {
                this.enderecos = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.pessoa.id !== undefined) {
            this.subscribeToSaveResponse(this.pessoaService.update(this.pessoa));
        } else {
            this.subscribeToSaveResponse(this.pessoaService.create(this.pessoa));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IPessoa>>) {
        result.subscribe((res: HttpResponse<IPessoa>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackNomeById(index: number, item: INome) {
        return item.id;
    }

    trackEnderecoById(index: number, item: IEndereco) {
        return item.id;
    }
}
