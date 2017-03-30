package com.ootb.service.currency;

import com.ootb.service.logger.type.InjectLogger;
import com.ritaja.xchangerate.api.CurrencyConverter;
import com.ritaja.xchangerate.api.CurrencyNotSupportedException;
import com.ritaja.xchangerate.endpoint.EndpointException;
import com.ritaja.xchangerate.service.ServiceException;
import com.ritaja.xchangerate.storage.StorageException;
import org.json.JSONException;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Currency;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Currency.getAvailableCurrencies;

/**
 * Created by Adam on 2017-03-28.
 */
@Service
public class CurrencyService {

    @Autowired
    private CurrencyConverter currencyConverter;

    protected static @InjectLogger Logger LOGGER;

    public List<Currency> getSortedCurrencies() {
        List<Currency> currencies = getAvailableCurrencies().stream()
                .sorted(Comparator.comparing(Currency::getCurrencyCode)).collect(Collectors.toList());
        List<String> convertibleCurrencies = getStringCurrencies();
        currencies.removeIf(cur -> {
            if(convertibleCurrencies.contains(cur.getCurrencyCode())) {
                return false;
            }
            return true;
        });

        return currencies;
    }

    public BigDecimal getConvertedCurrencyToPLN(BigDecimal expense, Currency currency) {
        com.ritaja.xchangerate.util.Currency exchangeCurrency
                = com.ritaja.xchangerate.util.Currency.valueOf(currency.getCurrencyCode());

        try {
            return currencyConverter.convertCurrency(expense, exchangeCurrency, com.ritaja.xchangerate.util.Currency.PLN);
        } catch (CurrencyNotSupportedException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
            LOGGER.error(e.toString());
        } catch (StorageException e) {
            e.printStackTrace();
            LOGGER.error(e.toString());
        } catch (EndpointException e) {
            e.printStackTrace();
            LOGGER.error(e.toString());
        } catch (ServiceException e) {
            e.printStackTrace();
            LOGGER.error(e.toString());
        }

        return BigDecimal.ZERO;
    }

    private List<String> getStringCurrencies() {
        List<com.ritaja.xchangerate.util.Currency> convertibleCurrencies = Arrays.asList(com.ritaja.xchangerate.util.Currency.values());
        List<String> currencies = new ArrayList<>();

        for(com.ritaja.xchangerate.util.Currency currency : convertibleCurrencies) {
            currencies.add(currency.toString());
        }

        return currencies;
    }
}
