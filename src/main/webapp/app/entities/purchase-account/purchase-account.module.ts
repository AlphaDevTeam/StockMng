import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { StockMngSharedModule } from 'app/shared';
import {
  PurchaseAccountComponent,
  PurchaseAccountDetailComponent,
  PurchaseAccountUpdateComponent,
  PurchaseAccountDeletePopupComponent,
  PurchaseAccountDeleteDialogComponent,
  purchaseAccountRoute,
  purchaseAccountPopupRoute
} from './';

const ENTITY_STATES = [...purchaseAccountRoute, ...purchaseAccountPopupRoute];

@NgModule({
  imports: [StockMngSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    PurchaseAccountComponent,
    PurchaseAccountDetailComponent,
    PurchaseAccountUpdateComponent,
    PurchaseAccountDeleteDialogComponent,
    PurchaseAccountDeletePopupComponent
  ],
  entryComponents: [
    PurchaseAccountComponent,
    PurchaseAccountUpdateComponent,
    PurchaseAccountDeleteDialogComponent,
    PurchaseAccountDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class StockMngPurchaseAccountModule {}
