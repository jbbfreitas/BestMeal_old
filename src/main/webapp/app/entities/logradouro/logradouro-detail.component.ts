import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ILogradouro } from 'app/shared/model/logradouro.model';

@Component({
    selector: 'jhi-logradouro-detail',
    templateUrl: './logradouro-detail.component.html'
})
export class LogradouroDetailComponent implements OnInit {
    logradouro: ILogradouro;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ logradouro }) => {
            this.logradouro = logradouro;
        });
    }

    previousState() {
        window.history.back();
    }
}
