/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { BestMealTestModule } from '../../../test.module';
import { LogradouroDeleteDialogComponent } from 'app/entities/logradouro/logradouro-delete-dialog.component';
import { LogradouroService } from 'app/entities/logradouro/logradouro.service';

describe('Component Tests', () => {
    describe('Logradouro Management Delete Component', () => {
        let comp: LogradouroDeleteDialogComponent;
        let fixture: ComponentFixture<LogradouroDeleteDialogComponent>;
        let service: LogradouroService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [BestMealTestModule],
                declarations: [LogradouroDeleteDialogComponent]
            })
                .overrideTemplate(LogradouroDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(LogradouroDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(LogradouroService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('confirmDelete', () => {
            it(
                'Should call delete service on confirmDelete',
                inject(
                    [],
                    fakeAsync(() => {
                        // GIVEN
                        spyOn(service, 'delete').and.returnValue(of({}));

                        // WHEN
                        comp.confirmDelete(123);
                        tick();

                        // THEN
                        expect(service.delete).toHaveBeenCalledWith(123);
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
                    })
                )
            );
        });
    });
});
