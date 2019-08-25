import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { ISalesAccountBalance } from 'app/shared/model/sales-account-balance.model';
import { AccountService } from 'app/core';
import { SalesAccountBalanceService } from './sales-account-balance.service';

@Component({
  selector: 'jhi-sales-account-balance',
  templateUrl: './sales-account-balance.component.html'
})
export class SalesAccountBalanceComponent implements OnInit, OnDestroy {
  salesAccountBalances: ISalesAccountBalance[];
  currentAccount: any;
  eventSubscriber: Subscription;

  constructor(
    protected salesAccountBalanceService: SalesAccountBalanceService,
    protected jhiAlertService: JhiAlertService,
    protected eventManager: JhiEventManager,
    protected accountService: AccountService
  ) {}

  loadAll() {
    this.salesAccountBalanceService
      .query()
      .pipe(
        filter((res: HttpResponse<ISalesAccountBalance[]>) => res.ok),
        map((res: HttpResponse<ISalesAccountBalance[]>) => res.body)
      )
      .subscribe(
        (res: ISalesAccountBalance[]) => {
          this.salesAccountBalances = res;
        },
        (res: HttpErrorResponse) => this.onError(res.message)
      );
  }

  ngOnInit() {
    this.loadAll();
    this.accountService.identity().then(account => {
      this.currentAccount = account;
    });
    this.registerChangeInSalesAccountBalances();
  }

  ngOnDestroy() {
    this.eventManager.destroy(this.eventSubscriber);
  }

  trackId(index: number, item: ISalesAccountBalance) {
    return item.id;
  }

  registerChangeInSalesAccountBalances() {
    this.eventSubscriber = this.eventManager.subscribe('salesAccountBalanceListModification', response => this.loadAll());
  }

  protected onError(errorMessage: string) {
    this.jhiAlertService.error(errorMessage, null, null);
  }
}
