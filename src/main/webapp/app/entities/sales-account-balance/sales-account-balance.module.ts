import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { StockMngSharedModule } from 'app/shared';
import {
  SalesAccountBalanceComponent,
  SalesAccountBalanceDetailComponent,
  SalesAccountBalanceUpdateComponent,
  SalesAccountBalanceDeletePopupComponent,
  SalesAccountBalanceDeleteDialogComponent,
  salesAccountBalanceRoute,
  salesAccountBalancePopupRoute
} from './';

const ENTITY_STATES = [...salesAccountBalanceRoute, ...salesAccountBalancePopupRoute];

@NgModule({
  imports: [StockMngSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    SalesAccountBalanceComponent,
    SalesAccountBalanceDetailComponent,
    SalesAccountBalanceUpdateComponent,
    SalesAccountBalanceDeleteDialogComponent,
    SalesAccountBalanceDeletePopupComponent
  ],
  entryComponents: [
    SalesAccountBalanceComponent,
    SalesAccountBalanceUpdateComponent,
    SalesAccountBalanceDeleteDialogComponent,
    SalesAccountBalanceDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class StockMngSalesAccountBalanceModule {}
