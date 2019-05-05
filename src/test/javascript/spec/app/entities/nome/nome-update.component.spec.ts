/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { BestMealTestModule } from '../../../test.module';
import { NomeUpdateComponent } from 'app/entities/nome/nome-update.component';
import { NomeService } from 'app/entities/nome/nome.service';
import { Nome } from 'app/shared/model/nome.model';

describe('Component Tests', () => {
    describe('Nome Management Update Component', () => {
        let comp: NomeUpdateComponent;
        let fixture: ComponentFixture<NomeUpdateComponent>;
        let service: NomeService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [BestMealTestModule],
                declarations: [NomeUpdateComponent]
            })
                .overrideTemplate(NomeUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(NomeUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(NomeService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new Nome(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.nome = entity;
                    // WHEN
                    comp.save();
                    tick(); // simulate async

                    // THEN
                    expect(service.update).toHaveBeenCalledWith(entity);
                    expect(comp.isSaving).toEqual(false);
                })
            );

            it(
                'Should call create service on save for new entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new Nome();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.nome = entity;
                    // WHEN
                    comp.save();
                    tick(); // simulate async

                    // THEN
                    expect(service.create).toHaveBeenCalledWith(entity);
                    expect(comp.isSaving).toEqual(false);
                })
            );
        });
    });
});
