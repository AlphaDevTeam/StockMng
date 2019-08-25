import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IItemBinCard } from 'app/shared/model/item-bin-card.model';
import { AccountService } from 'app/core';
import { ItemBinCardService } from './item-bin-card.service';

@Component({
  selector: 'jhi-item-bin-card',
  templateUrl: './item-bin-card.component.html'
})
export class ItemBinCardComponent implements OnInit, OnDestroy {
  itemBinCards: IItemBinCard[];
  currentAccount: any;
  eventSubscriber: Subscription;

  constructor(
    protected itemBinCardService: ItemBinCardService,
    protected jhiAlertService: JhiAlertService,
    protected eventManager: JhiEventManager,
    protected accountService: AccountService
  ) {}

  loadAll() {
    this.itemBinCardService
      .query()
      .pipe(
        filter((res: HttpResponse<IItemBinCard[]>) => res.ok),
        map((res: HttpResponse<IItemBinCard[]>) => res.body)
      )
      .subscribe(
        (res: IItemBinCard[]) => {
          this.itemBinCards = res;
        },
        (res: HttpErrorResponse) => this.onError(res.message)
      );
  }

  ngOnInit() {
    this.loadAll();
    this.accountService.identity().then(account => {
      this.currentAccount = account;
    });
    this.registerChangeInItemBinCards();
  }

  ngOnDestroy() {
    this.eventManager.destroy(this.eventSubscriber);
  }

  trackId(index: number, item: IItemBinCard) {
    return item.id;
  }

  registerChangeInItemBinCards() {
    this.eventSubscriber = this.eventManager.subscribe('itemBinCardListModification', response => this.loadAll());
  }

  protected onError(errorMessage: string) {
    this.jhiAlertService.error(errorMessage, null, null);
  }
}
