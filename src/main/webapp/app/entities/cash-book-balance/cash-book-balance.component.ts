import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { ICashBookBalance } from 'app/shared/model/cash-book-balance.model';
import { AccountService } from 'app/core';
import { CashBookBalanceService } from './cash-book-balance.service';

@Component({
  selector: 'jhi-cash-book-balance',
  templateUrl: './cash-book-balance.component.html'
})
export class CashBookBalanceComponent implements OnInit, OnDestroy {
  cashBookBalances: ICashBookBalance[];
  currentAccount: any;
  eventSubscriber: Subscription;

  constructor(
    protected cashBookBalanceService: CashBookBalanceService,
    protected jhiAlertService: JhiAlertService,
    protected eventManager: JhiEventManager,
    protected accountService: AccountService
  ) {}

  loadAll() {
    this.cashBookBalanceService
      .query()
      .pipe(
        filter((res: HttpResponse<ICashBookBalance[]>) => res.ok),
        map((res: HttpResponse<ICashBookBalance[]>) => res.body)
      )
      .subscribe(
        (res: ICashBookBalance[]) => {
          this.cashBookBalances = res;
        },
        (res: HttpErrorResponse) => this.onError(res.message)
      );
  }

  ngOnInit() {
    this.loadAll();
    this.accountService.identity().then(account => {
      this.currentAccount = account;
    });
    this.registerChangeInCashBookBalances();
  }

  ngOnDestroy() {
    this.eventManager.destroy(this.eventSubscriber);
  }

  trackId(index: number, item: ICashBookBalance) {
    return item.id;
  }

  registerChangeInCashBookBalances() {
    this.eventSubscriber = this.eventManager.subscribe('cashBookBalanceListModification', response => this.loadAll());
  }

  protected onError(errorMessage: string) {
    this.jhiAlertService.error(errorMessage, null, null);
  }
}
