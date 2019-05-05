/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { BestMealTestModule } from '../../../test.module';
import { NomeDetailComponent } from 'app/entities/nome/nome-detail.component';
import { Nome } from 'app/shared/model/nome.model';

describe('Component Tests', () => {
    describe('Nome Management Detail Component', () => {
        let comp: NomeDetailComponent;
        let fixture: ComponentFixture<NomeDetailComponent>;
        const route = ({ data: of({ nome: new Nome(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [BestMealTestModule],
                declarations: [NomeDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(NomeDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(NomeDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.nome).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
