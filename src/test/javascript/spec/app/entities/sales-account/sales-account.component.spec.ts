/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { StockMngTestModule } from '../../../test.module';
import { SalesAccountComponent } from 'app/entities/sales-account/sales-account.component';
import { SalesAccountService } from 'app/entities/sales-account/sales-account.service';
import { SalesAccount } from 'app/shared/model/sales-account.model';

describe('Component Tests', () => {
  describe('SalesAccount Management Component', () => {
    let comp: SalesAccountComponent;
    let fixture: ComponentFixture<SalesAccountComponent>;
    let service: SalesAccountService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [StockMngTestModule],
        declarations: [SalesAccountComponent],
        providers: []
      })
        .overrideTemplate(SalesAccountComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(SalesAccountComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(SalesAccountService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new SalesAccount(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.salesAccounts[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
