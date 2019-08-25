import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { ISupplierAccountBalance } from 'app/shared/model/supplier-account-balance.model';
import { AccountService } from 'app/core';
import { SupplierAccountBalanceService } from './supplier-account-balance.service';

@Component({
  selector: 'jhi-supplier-account-balance',
  templateUrl: './supplier-account-balance.component.html'
})
export class SupplierAccountBalanceComponent implements OnInit, OnDestroy {
  supplierAccountBalances: ISupplierAccountBalance[];
  currentAccount: any;
  eventSubscriber: Subscription;

  constructor(
    protected supplierAccountBalanceService: SupplierAccountBalanceService,
    protected jhiAlertService: JhiAlertService,
    protected eventManager: JhiEventManager,
    protected accountService: AccountService
  ) {}

  loadAll() {
    this.supplierAccountBalanceService
      .query()
      .pipe(
        filter((res: HttpResponse<ISupplierAccountBalance[]>) => res.ok),
        map((res: HttpResponse<ISupplierAccountBalance[]>) => res.body)
      )
      .subscribe(
        (res: ISupplierAccountBalance[]) => {
          this.supplierAccountBalances = res;
        },
        (res: HttpErrorResponse) => this.onError(res.message)
      );
  }

  ngOnInit() {
    this.loadAll();
    this.accountService.identity().then(account => {
      this.currentAccount = account;
    });
    this.registerChangeInSupplierAccountBalances();
  }

  ngOnDestroy() {
    this.eventManager.destroy(this.eventSubscriber);
  }

  trackId(index: number, item: ISupplierAccountBalance) {
    return item.id;
  }

  registerChangeInSupplierAccountBalances() {
    this.eventSubscriber = this.eventManager.subscribe('supplierAccountBalanceListModification', response => this.loadAll());
  }

  protected onError(errorMessage: string) {
    this.jhiAlertService.error(errorMessage, null, null);
  }
}
