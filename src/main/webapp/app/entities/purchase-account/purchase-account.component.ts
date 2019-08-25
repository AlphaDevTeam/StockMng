import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IPurchaseAccount } from 'app/shared/model/purchase-account.model';
import { AccountService } from 'app/core';
import { PurchaseAccountService } from './purchase-account.service';

@Component({
  selector: 'jhi-purchase-account',
  templateUrl: './purchase-account.component.html'
})
export class PurchaseAccountComponent implements OnInit, OnDestroy {
  purchaseAccounts: IPurchaseAccount[];
  currentAccount: any;
  eventSubscriber: Subscription;

  constructor(
    protected purchaseAccountService: PurchaseAccountService,
    protected jhiAlertService: JhiAlertService,
    protected eventManager: JhiEventManager,
    protected accountService: AccountService
  ) {}

  loadAll() {
    this.purchaseAccountService
      .query()
      .pipe(
        filter((res: HttpResponse<IPurchaseAccount[]>) => res.ok),
        map((res: HttpResponse<IPurchaseAccount[]>) => res.body)
      )
      .subscribe(
        (res: IPurchaseAccount[]) => {
          this.purchaseAccounts = res;
        },
        (res: HttpErrorResponse) => this.onError(res.message)
      );
  }

  ngOnInit() {
    this.loadAll();
    this.accountService.identity().then(account => {
      this.currentAccount = account;
    });
    this.registerChangeInPurchaseAccounts();
  }

  ngOnDestroy() {
    this.eventManager.destroy(this.eventSubscriber);
  }

  trackId(index: number, item: IPurchaseAccount) {
    return item.id;
  }

  registerChangeInPurchaseAccounts() {
    this.eventSubscriber = this.eventManager.subscribe('purchaseAccountListModification', response => this.loadAll());
  }

  protected onError(errorMessage: string) {
    this.jhiAlertService.error(errorMessage, null, null);
  }
}
