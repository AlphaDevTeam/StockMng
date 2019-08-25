import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { StockMngSharedModule } from 'app/shared';
import {
  TransactionTypeComponent,
  TransactionTypeDetailComponent,
  TransactionTypeUpdateComponent,
  TransactionTypeDeletePopupComponent,
  TransactionTypeDeleteDialogComponent,
  transactionTypeRoute,
  transactionTypePopupRoute
} from './';

const ENTITY_STATES = [...transactionTypeRoute, ...transactionTypePopupRoute];

@NgModule({
  imports: [StockMngSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    TransactionTypeComponent,
    TransactionTypeDetailComponent,
    TransactionTypeUpdateComponent,
    TransactionTypeDeleteDialogComponent,
    TransactionTypeDeletePopupComponent
  ],
  entryComponents: [
    TransactionTypeComponent,
    TransactionTypeUpdateComponent,
    TransactionTypeDeleteDialogComponent,
    TransactionTypeDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class StockMngTransactionTypeModule {}
