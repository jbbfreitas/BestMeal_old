/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { BestMealTestModule } from '../../../test.module';
import { LogradouroDetailComponent } from 'app/entities/logradouro/logradouro-detail.component';
import { Logradouro } from 'app/shared/model/logradouro.model';

describe('Component Tests', () => {
    describe('Logradouro Management Detail Component', () => {
        let comp: LogradouroDetailComponent;
        let fixture: ComponentFixture<LogradouroDetailComponent>;
        const route = ({ data: of({ logradouro: new Logradouro(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [BestMealTestModule],
                declarations: [LogradouroDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(LogradouroDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(LogradouroDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.logradouro).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
