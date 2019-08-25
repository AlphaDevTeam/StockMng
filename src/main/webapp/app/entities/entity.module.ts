import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'products',
        loadChildren: () => import('./products/products.module').then(m => m.StockMngProductsModule)
      },
      {
        path: 'desings',
        loadChildren: () => import('./desings/desings.module').then(m => m.StockMngDesingsModule)
      },
      {
        path: 'job',
        loadChildren: () => import('./job/job.module').then(m => m.StockMngJobModule)
      },
      {
        path: 'job-detais',
        loadChildren: () => import('./job-detais/job-detais.module').then(m => m.StockMngJobDetaisModule)
      },
      {
        path: 'job-status',
        loadChildren: () => import('./job-status/job-status.module').then(m => m.StockMngJobStatusModule)
      },
      {
        path: 'items',
        loadChildren: () => import('./items/items.module').then(m => m.StockMngItemsModule)
      },
      {
        path: 'item-bin-card',
        loadChildren: () => import('./item-bin-card/item-bin-card.module').then(m => m.StockMngItemBinCardModule)
      },
      {
        path: 'purchase-order',
        loadChildren: () => import('./purchase-order/purchase-order.module').then(m => m.StockMngPurchaseOrderModule)
      },
      {
        path: 'purchase-order-details',
        loadChildren: () => import('./purchase-order-details/purchase-order-details.module').then(m => m.StockMngPurchaseOrderDetailsModule)
      },
      {
        path: 'goods-receipt',
        loadChildren: () => import('./goods-receipt/goods-receipt.module').then(m => m.StockMngGoodsReceiptModule)
      },
      {
        path: 'goods-receipt-details',
        loadChildren: () => import('./goods-receipt-details/goods-receipt-details.module').then(m => m.StockMngGoodsReceiptDetailsModule)
      },
      {
        path: 'cash-book',
        loadChildren: () => import('./cash-book/cash-book.module').then(m => m.StockMngCashBookModule)
      },
      {
        path: 'cash-book-balance',
        loadChildren: () => import('./cash-book-balance/cash-book-balance.module').then(m => m.StockMngCashBookBalanceModule)
      },
      {
        path: 'customer-account',
        loadChildren: () => import('./customer-account/customer-account.module').then(m => m.StockMngCustomerAccountModule)
      },
      {
        path: 'customer-account-balance',
        loadChildren: () =>
          import('./customer-account-balance/customer-account-balance.module').then(m => m.StockMngCustomerAccountBalanceModule)
      },
      {
        path: 'supplier-account',
        loadChildren: () => import('./supplier-account/supplier-account.module').then(m => m.StockMngSupplierAccountModule)
      },
      {
        path: 'supplier-account-balance',
        loadChildren: () =>
          import('./supplier-account-balance/supplier-account-balance.module').then(m => m.StockMngSupplierAccountBalanceModule)
      },
      {
        path: 'purchase-account',
        loadChildren: () => import('./purchase-account/purchase-account.module').then(m => m.StockMngPurchaseAccountModule)
      },
      {
        path: 'purchase-account-balance',
        loadChildren: () =>
          import('./purchase-account-balance/purchase-account-balance.module').then(m => m.StockMngPurchaseAccountBalanceModule)
      },
      {
        path: 'sales-account',
        loadChildren: () => import('./sales-account/sales-account.module').then(m => m.StockMngSalesAccountModule)
      },
      {
        path: 'sales-account-balance',
        loadChildren: () => import('./sales-account-balance/sales-account-balance.module').then(m => m.StockMngSalesAccountBalanceModule)
      },
      {
        path: 'document-type',
        loadChildren: () => import('./document-type/document-type.module').then(m => m.StockMngDocumentTypeModule)
      },
      {
        path: 'transaction-type',
        loadChildren: () => import('./transaction-type/transaction-type.module').then(m => m.StockMngTransactionTypeModule)
      },
      {
        path: 'location',
        loadChildren: () => import('./location/location.module').then(m => m.StockMngLocationModule)
      },
      {
        path: 'customer',
        loadChildren: () => import('./customer/customer.module').then(m => m.StockMngCustomerModule)
      },
      {
        path: 'supplier',
        loadChildren: () => import('./supplier/supplier.module').then(m => m.StockMngSupplierModule)
      },
      {
        path: 'worker',
        loadChildren: () => import('./worker/worker.module').then(m => m.StockMngWorkerModule)
      },
      {
        path: 'ex-user',
        loadChildren: () => import('./ex-user/ex-user.module').then(m => m.StockMngExUserModule)
      },
      {
        path: 'stock',
        loadChildren: () => import('./stock/stock.module').then(m => m.StockMngStockModule)
      },
      {
        path: 'company',
        loadChildren: () => import('./company/company.module').then(m => m.StockMngCompanyModule)
      }
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ])
  ],
  declarations: [],
  entryComponents: [],
  providers: [],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class StockMngEntityModule {}
