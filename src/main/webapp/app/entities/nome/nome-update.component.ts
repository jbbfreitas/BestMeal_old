import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { INome } from 'app/shared/model/nome.model';
import { NomeService } from './nome.service';

@Component({
    selector: 'jhi-nome-update',
    templateUrl: './nome-update.component.html'
})
export class NomeUpdateComponent implements OnInit {
    nome: INome;
    isSaving: boolean;

    constructor(private nomeService: NomeService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ nome }) => {
            this.nome = nome;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.nome.id !== undefined) {
            this.subscribeToSaveResponse(this.nomeService.update(this.nome));
        } else {
            this.subscribeToSaveResponse(this.nomeService.create(this.nome));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<INome>>) {
        result.subscribe((res: HttpResponse<INome>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
}
