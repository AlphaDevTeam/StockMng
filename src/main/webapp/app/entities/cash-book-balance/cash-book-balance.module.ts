import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { StockMngSharedModule } from 'app/shared';
import {
  CashBookBalanceComponent,
  CashBookBalanceDetailComponent,
  CashBookBalanceUpdateComponent,
  CashBookBalanceDeletePopupComponent,
  CashBookBalanceDeleteDialogComponent,
  cashBookBalanceRoute,
  cashBookBalancePopupRoute
} from './';

const ENTITY_STATES = [...cashBookBalanceRoute, ...cashBookBalancePopupRoute];

@NgModule({
  imports: [StockMngSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    CashBookBalanceComponent,
    CashBookBalanceDetailComponent,
    CashBookBalanceUpdateComponent,
    CashBookBalanceDeleteDialogComponent,
    CashBookBalanceDeletePopupComponent
  ],
  entryComponents: [
    CashBookBalanceComponent,
    CashBookBalanceUpdateComponent,
    CashBookBalanceDeleteDialogComponent,
    CashBookBalanceDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class StockMngCashBookBalanceModule {}
