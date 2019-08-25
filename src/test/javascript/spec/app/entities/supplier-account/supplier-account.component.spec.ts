/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { StockMngTestModule } from '../../../test.module';
import { SupplierAccountComponent } from 'app/entities/supplier-account/supplier-account.component';
import { SupplierAccountService } from 'app/entities/supplier-account/supplier-account.service';
import { SupplierAccount } from 'app/shared/model/supplier-account.model';

describe('Component Tests', () => {
  describe('SupplierAccount Management Component', () => {
    let comp: SupplierAccountComponent;
    let fixture: ComponentFixture<SupplierAccountComponent>;
    let service: SupplierAccountService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [StockMngTestModule],
        declarations: [SupplierAccountComponent],
        providers: []
      })
        .overrideTemplate(SupplierAccountComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(SupplierAccountComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(SupplierAccountService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new SupplierAccount(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.supplierAccounts[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
