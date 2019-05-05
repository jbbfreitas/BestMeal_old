import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { INome } from 'app/shared/model/nome.model';

@Component({
    selector: 'jhi-nome-detail',
    templateUrl: './nome-detail.component.html'
})
export class NomeDetailComponent implements OnInit {
    nome: INome;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ nome }) => {
            this.nome = nome;
        });
    }

    previousState() {
        window.history.back();
    }
}
