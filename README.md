# 교차환율(Cross Rate)

* European Term 환율 간
  * bid = 상대통화 bid / 기준통화 ask
  * ask = 상대통화 ask / 기준통화 bid
  
  ex) JPY/KRW 환율 <br/>
  USD/JPY = 76.65/76.70 <br/>
  USD/KRW = 1124.50/1125.00 <br/>
  bid = 1124.50 / 76.70 = 14.6610 <br/>
  ask = 1125.00 / 76.65 = 14.6771 <br/>

* American Term 환율 간
  * bid = 기준통화 bid / 상대통화 ask
  * ask = 기준통화 ask / 상대통화 bid
  
  ex) GBP/EUR 환율 <br/>
  GBP/USD = 1.5711/1.5716 <br/>
  EUR/USD = 1.3180/1.3185 <br/>
  bid = 1.5711 / 1.3185 = 1.19158 <br/>
  ask = 1.5716 / 1.3180 = 1.19241 <br/>
  
* European Term 환율과 American Term 환율 간
  * bid = 상대통화 bid * 기준통화 bid
  * ask = 상대통화 ask * 기준통화 ask
  
  ex) EUR/KRW 환율 <br/>
  EUR/USD = 1.3180/1.3185 <br/>
  USD/KRW = 1124.50/1125.00 <br/>
  bid = 1124.50 * 1.3180 = 1482.09 <br/>
  ask = 1125.00 * 1.3185 = 1483.31 <br/>
