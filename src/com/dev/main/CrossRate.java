package com.dev.main;


import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;

public class CrossRate {
	
	public static ExchangeRate getExchangeRate(String base, String counter) {
		
		ExchangeRate exchangeRate = null;
		
		String baseTerm = getTerm(base);
		String counterTerm = getTerm(counter);
		
		if ("USD".equals(base) || "USD".equals(counter)) {
			
			exchangeRate = loadExchangeRate().get(base + counter);
			
		} else {
			
			BigDecimal bid = BigDecimal.ZERO;
			BigDecimal ask = BigDecimal.ZERO;
			
			ExchangeRate baseExchangeRate = null;
			ExchangeRate counterExchangeRate = null;
			
			if (baseTerm.equals("European")) {
				baseExchangeRate = loadExchangeRate().get("USD" + base);
			} 
			
			if (baseTerm.equals("American")) {
				baseExchangeRate = loadExchangeRate().get(base + "USD");
			} 
			
			if (counterTerm.equals("European")) {
				counterExchangeRate = loadExchangeRate().get("USD" + counter);
			} 
			
			if (counterTerm.equals("American")) {
				counterExchangeRate = loadExchangeRate().get(counter + "USD");
			}

			//European Term - European Term
			if (baseTerm.equals("European") && counterTerm.equals("European")) {
				
				bid = counterExchangeRate.getBid().divide(baseExchangeRate.getAsk(), 4, RoundingMode.HALF_DOWN);
				ask = counterExchangeRate.getAsk().divide(baseExchangeRate.getBid(), 4, RoundingMode.HALF_DOWN);
			}
			//American Term - American Term
			else if (baseTerm.equals("American") && counterTerm.equals("American")) {
				
				bid = baseExchangeRate.getBid().divide(counterExchangeRate.getAsk(), 4, RoundingMode.HALF_DOWN);
				ask = baseExchangeRate.getAsk().divide(counterExchangeRate.getBid(), 4, RoundingMode.HALF_DOWN);
			}
			//American Term - European Term
			else {

				bid = counterExchangeRate.getBid().multiply(baseExchangeRate.getBid()).setScale(4, RoundingMode.HALF_DOWN);
				ask = counterExchangeRate.getAsk().multiply(baseExchangeRate.getAsk()).setScale(4, RoundingMode.HALF_DOWN);
			}
			
			exchangeRate = new ExchangeRate(bid, ask);
		}
		
		return exchangeRate;
	}
	
	private static String getTerm(String ccy) {
		
		String term = "";
		
		if ("EUR".equals(ccy) ||"GBP".equals(ccy) ||"AUD".equals(ccy) ||"NZD".equals(ccy)) {
			term = "American";
		} else {
			term = "European";
		}
		
		return term;
	}
	
	private static Map<String, ExchangeRate> loadExchangeRate() {

		Map<String, ExchangeRate> exchangeRates = new HashMap<String, ExchangeRate>();
		
		exchangeRates.put("USDKRW", new ExchangeRate(BigDecimal.valueOf(1124.50), BigDecimal.valueOf(1125.00)));
		exchangeRates.put("USDJPY", new ExchangeRate(BigDecimal.valueOf(76.65), BigDecimal.valueOf(76.70)));
		exchangeRates.put("EURUSD", new ExchangeRate(BigDecimal.valueOf(1.3180), BigDecimal.valueOf(1.3185)));
		exchangeRates.put("GBPUSD", new ExchangeRate(BigDecimal.valueOf(1.5711), BigDecimal.valueOf(1.5716)));
		
		return exchangeRates;
	}
	
	public static class ExchangeRate {

		private BigDecimal bid;
		private BigDecimal ask;
		
		public ExchangeRate(BigDecimal bid, BigDecimal ask) {
			this.bid = bid;
			this.ask = ask;
		}

		public BigDecimal getBid() {
			return bid;
		}

		public void setBid(BigDecimal bid) {
			this.bid = bid;
		}

		public BigDecimal getAsk() {
			return ask;
		}

		public void setAsk(BigDecimal ask) {
			this.ask = ask;
		}
	}

}

