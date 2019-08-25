import { Moment } from 'moment';
import { ILocation } from 'app/shared/model/location.model';
import { ITransactionType } from 'app/shared/model/transaction-type.model';

export interface IPurchaseAccount {
  id?: number;
  transactionDate?: Moment;
  transactionDescription?: string;
  transactionAmountDR?: number;
  transactionAmountCR?: number;
  transactionBalance?: number;
  location?: ILocation;
  transactionType?: ITransactionType;
}

export class PurchaseAccount implements IPurchaseAccount {
  constructor(
    public id?: number,
    public transactionDate?: Moment,
    public transactionDescription?: string,
    public transactionAmountDR?: number,
    public transactionAmountCR?: number,
    public transactionBalance?: number,
    public location?: ILocation,
    public transactionType?: ITransactionType
  ) {}
}
