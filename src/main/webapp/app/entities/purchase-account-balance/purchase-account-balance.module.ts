import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { StockMngSharedModule } from 'app/shared';
import {
  PurchaseAccountBalanceComponent,
  PurchaseAccountBalanceDetailComponent,
  PurchaseAccountBalanceUpdateComponent,
  PurchaseAccountBalanceDeletePopupComponent,
  PurchaseAccountBalanceDeleteDialogComponent,
  purchaseAccountBalanceRoute,
  purchaseAccountBalancePopupRoute
} from './';

const ENTITY_STATES = [...purchaseAccountBalanceRoute, ...purchaseAccountBalancePopupRoute];

@NgModule({
  imports: [StockMngSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    PurchaseAccountBalanceComponent,
    PurchaseAccountBalanceDetailComponent,
    PurchaseAccountBalanceUpdateComponent,
    PurchaseAccountBalanceDeleteDialogComponent,
    PurchaseAccountBalanceDeletePopupComponent
  ],
  entryComponents: [
    PurchaseAccountBalanceComponent,
    PurchaseAccountBalanceUpdateComponent,
    PurchaseAccountBalanceDeleteDialogComponent,
    PurchaseAccountBalanceDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class StockMngPurchaseAccountBalanceModule {}
