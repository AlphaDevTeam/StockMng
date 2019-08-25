import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { StockMngSharedModule } from 'app/shared';
import {
  ItemBinCardComponent,
  ItemBinCardDetailComponent,
  ItemBinCardUpdateComponent,
  ItemBinCardDeletePopupComponent,
  ItemBinCardDeleteDialogComponent,
  itemBinCardRoute,
  itemBinCardPopupRoute
} from './';

const ENTITY_STATES = [...itemBinCardRoute, ...itemBinCardPopupRoute];

@NgModule({
  imports: [StockMngSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    ItemBinCardComponent,
    ItemBinCardDetailComponent,
    ItemBinCardUpdateComponent,
    ItemBinCardDeleteDialogComponent,
    ItemBinCardDeletePopupComponent
  ],
  entryComponents: [ItemBinCardComponent, ItemBinCardUpdateComponent, ItemBinCardDeleteDialogComponent, ItemBinCardDeletePopupComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class StockMngItemBinCardModule {}
