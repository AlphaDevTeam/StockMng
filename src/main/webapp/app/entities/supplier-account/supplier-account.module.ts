import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { StockMngSharedModule } from 'app/shared';
import {
  SupplierAccountComponent,
  SupplierAccountDetailComponent,
  SupplierAccountUpdateComponent,
  SupplierAccountDeletePopupComponent,
  SupplierAccountDeleteDialogComponent,
  supplierAccountRoute,
  supplierAccountPopupRoute
} from './';

const ENTITY_STATES = [...supplierAccountRoute, ...supplierAccountPopupRoute];

@NgModule({
  imports: [StockMngSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    SupplierAccountComponent,
    SupplierAccountDetailComponent,
    SupplierAccountUpdateComponent,
    SupplierAccountDeleteDialogComponent,
    SupplierAccountDeletePopupComponent
  ],
  entryComponents: [
    SupplierAccountComponent,
    SupplierAccountUpdateComponent,
    SupplierAccountDeleteDialogComponent,
    SupplierAccountDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class StockMngSupplierAccountModule {}
