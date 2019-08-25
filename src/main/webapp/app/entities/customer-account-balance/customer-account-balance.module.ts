import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { StockMngSharedModule } from 'app/shared';
import {
  CustomerAccountBalanceComponent,
  CustomerAccountBalanceDetailComponent,
  CustomerAccountBalanceUpdateComponent,
  CustomerAccountBalanceDeletePopupComponent,
  CustomerAccountBalanceDeleteDialogComponent,
  customerAccountBalanceRoute,
  customerAccountBalancePopupRoute
} from './';

const ENTITY_STATES = [...customerAccountBalanceRoute, ...customerAccountBalancePopupRoute];

@NgModule({
  imports: [StockMngSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    CustomerAccountBalanceComponent,
    CustomerAccountBalanceDetailComponent,
    CustomerAccountBalanceUpdateComponent,
    CustomerAccountBalanceDeleteDialogComponent,
    CustomerAccountBalanceDeletePopupComponent
  ],
  entryComponents: [
    CustomerAccountBalanceComponent,
    CustomerAccountBalanceUpdateComponent,
    CustomerAccountBalanceDeleteDialogComponent,
    CustomerAccountBalanceDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class StockMngCustomerAccountBalanceModule {}
