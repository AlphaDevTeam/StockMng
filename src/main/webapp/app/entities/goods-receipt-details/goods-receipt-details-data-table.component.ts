import { Component, OnInit, OnDestroy, Input  } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IGoodsReceiptDetails,GoodsReceiptDetails } from 'app/shared/model/goods-receipt-details.model';
import { AccountService } from 'app/core';
import { GoodsReceiptDetailsService } from './goods-receipt-details.service';

@Component({
  selector: 'jhi-goods-receipt-details-data-table',
  templateUrl: './goods-receipt-details-data-table.component.html',
})

export class GoodsReceiptDetailsDataTableComponent implements OnInit, OnDestroy {

  goodsReceiptDetails: IGoodsReceiptDetails[];
  @Input() goodsReceiptDetail: IGoodsReceiptDetails;
  currentAccount: any;
  eventSubscriber: Subscription;

  constructor(
    protected goodsReceiptDetailsService: GoodsReceiptDetailsService,
    protected jhiAlertService: JhiAlertService,
    protected eventManager: JhiEventManager,
    protected accountService: AccountService
  ) {}

  loadAll() {
    this.goodsReceiptDetailsService
      .query()
      .pipe(
        filter((res: HttpResponse<IGoodsReceiptDetails[]>) => res.ok),
        map((res: HttpResponse<IGoodsReceiptDetails[]>) => res.body)
      )
      .subscribe(
        (res: IGoodsReceiptDetails[]) => {
          this.goodsReceiptDetails = res;
        },
        (res: HttpErrorResponse) => this.onError(res.message)
      );
  }

  testLoad(){
    //(public id?: number, public grnQty?: string, public item?: IItems, public grn?: IGoodsReceipt)
    //this.goodsReceiptDetail = new GoodsReceiptDetails(null,"23",null,null);
    this.goodsReceiptDetails.push(this.goodsReceiptDetail);
  }

  ngOnInit() {
    this.testLoad();
    this.accountService.identity().then(account => {
      this.currentAccount = account;
    });
    this.registerChangeInGoodsReceiptDetails();
  }

  ngOnDestroy() {
    this.eventManager.destroy(this.eventSubscriber);
  }

  trackId(index: number, item: IGoodsReceiptDetails) {
    return item.id;
  }

  registerChangeInGoodsReceiptDetails() {
    this.eventSubscriber = this.eventManager.subscribe('goodsReceiptDetailsListModification', response => this.loadAll());
  }

  protected onError(errorMessage: string) {
    this.jhiAlertService.error(errorMessage, null, null);
  }
}
