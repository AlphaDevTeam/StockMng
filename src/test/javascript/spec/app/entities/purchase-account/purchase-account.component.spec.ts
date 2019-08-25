/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { StockMngTestModule } from '../../../test.module';
import { PurchaseAccountComponent } from 'app/entities/purchase-account/purchase-account.component';
import { PurchaseAccountService } from 'app/entities/purchase-account/purchase-account.service';
import { PurchaseAccount } from 'app/shared/model/purchase-account.model';

describe('Component Tests', () => {
  describe('PurchaseAccount Management Component', () => {
    let comp: PurchaseAccountComponent;
    let fixture: ComponentFixture<PurchaseAccountComponent>;
    let service: PurchaseAccountService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [StockMngTestModule],
        declarations: [PurchaseAccountComponent],
        providers: []
      })
        .overrideTemplate(PurchaseAccountComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(PurchaseAccountComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(PurchaseAccountService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new PurchaseAccount(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.purchaseAccounts[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
