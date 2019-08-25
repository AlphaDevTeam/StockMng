import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { StockMngSharedModule } from 'app/shared';
import {
  SalesAccountComponent,
  SalesAccountDetailComponent,
  SalesAccountUpdateComponent,
  SalesAccountDeletePopupComponent,
  SalesAccountDeleteDialogComponent,
  salesAccountRoute,
  salesAccountPopupRoute
} from './';

const ENTITY_STATES = [...salesAccountRoute, ...salesAccountPopupRoute];

@NgModule({
  imports: [StockMngSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    SalesAccountComponent,
    SalesAccountDetailComponent,
    SalesAccountUpdateComponent,
    SalesAccountDeleteDialogComponent,
    SalesAccountDeletePopupComponent
  ],
  entryComponents: [
    SalesAccountComponent,
    SalesAccountUpdateComponent,
    SalesAccountDeleteDialogComponent,
    SalesAccountDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class StockMngSalesAccountModule {}
