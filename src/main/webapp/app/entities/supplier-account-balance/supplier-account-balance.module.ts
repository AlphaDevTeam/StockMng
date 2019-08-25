import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { StockMngSharedModule } from 'app/shared';
import {
  SupplierAccountBalanceComponent,
  SupplierAccountBalanceDetailComponent,
  SupplierAccountBalanceUpdateComponent,
  SupplierAccountBalanceDeletePopupComponent,
  SupplierAccountBalanceDeleteDialogComponent,
  supplierAccountBalanceRoute,
  supplierAccountBalancePopupRoute
} from './';

const ENTITY_STATES = [...supplierAccountBalanceRoute, ...supplierAccountBalancePopupRoute];

@NgModule({
  imports: [StockMngSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    SupplierAccountBalanceComponent,
    SupplierAccountBalanceDetailComponent,
    SupplierAccountBalanceUpdateComponent,
    SupplierAccountBalanceDeleteDialogComponent,
    SupplierAccountBalanceDeletePopupComponent
  ],
  entryComponents: [
    SupplierAccountBalanceComponent,
    SupplierAccountBalanceUpdateComponent,
    SupplierAccountBalanceDeleteDialogComponent,
    SupplierAccountBalanceDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class StockMngSupplierAccountBalanceModule {}
