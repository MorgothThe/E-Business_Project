package repositories;

import models.Currency;

public class CurrencyRepository extends GenericRepository<Currency> {

    public CurrencyRepository(){
        super(Currency.class);
    }

}
