import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ILogradouro } from 'app/shared/model/logradouro.model';
import { LogradouroService } from './logradouro.service';

@Component({
    selector: 'jhi-logradouro-delete-dialog',
    templateUrl: './logradouro-delete-dialog.component.html'
})
export class LogradouroDeleteDialogComponent {
    logradouro: ILogradouro;

    constructor(private logradouroService: LogradouroService, public activeModal: NgbActiveModal, private eventManager: JhiEventManager) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.logradouroService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'logradouroListModification',
                content: 'Deleted an logradouro'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-logradouro-delete-popup',
    template: ''
})
export class LogradouroDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ logradouro }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(LogradouroDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
                this.ngbModalRef.componentInstance.logradouro = logradouro;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate([{ outlets: { popup: null } }], { replaceUrl: true, queryParamsHandling: 'merge' });
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate([{ outlets: { popup: null } }], { replaceUrl: true, queryParamsHandling: 'merge' });
                        this.ngbModalRef = null;
                    }
                );
            }, 0);
        });
    }

    ngOnDestroy() {
        this.ngbModalRef = null;
    }
}
