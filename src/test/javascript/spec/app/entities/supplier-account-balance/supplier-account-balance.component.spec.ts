/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { StockMngTestModule } from '../../../test.module';
import { SupplierAccountBalanceComponent } from 'app/entities/supplier-account-balance/supplier-account-balance.component';
import { SupplierAccountBalanceService } from 'app/entities/supplier-account-balance/supplier-account-balance.service';
import { SupplierAccountBalance } from 'app/shared/model/supplier-account-balance.model';

describe('Component Tests', () => {
  describe('SupplierAccountBalance Management Component', () => {
    let comp: SupplierAccountBalanceComponent;
    let fixture: ComponentFixture<SupplierAccountBalanceComponent>;
    let service: SupplierAccountBalanceService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [StockMngTestModule],
        declarations: [SupplierAccountBalanceComponent],
        providers: []
      })
        .overrideTemplate(SupplierAccountBalanceComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(SupplierAccountBalanceComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(SupplierAccountBalanceService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new SupplierAccountBalance(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.supplierAccountBalances[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
