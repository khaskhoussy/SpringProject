package com.example.entity;

public class ChargeRequest {
    public enum Currency {
        EUR, USD, DT;
    }
    private String description;
    private int amount;
    private Currency currency;
    private String stripeToken;
    private String currencyString;
    
	public String getCurrencyString() {
		return currencyString;
	}
	public void setCurrencyString(String currencyString) {
		this.currencyString = currencyString;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public Currency getCurrency() {
		return currency;
	}
	public void setCurrency(Currency currency) {
		this.currency = currency;
	}
	
	public String getStripeToken() {
		return stripeToken;
	}
	public void setStripeToken(String stripeToken) {
		this.stripeToken = stripeToken;
	}
	public ChargeRequest() {
		super();
	}
	public ChargeRequest(String description, int amount, Currency currency, String stripeToken) {
		super();
		this.description = description;
		this.amount = amount;
		this.currency = currency;
		
		this.stripeToken = stripeToken;
	}
	
	public ChargeRequest(String description, int amount, String stripeToken, String currencyString) {
		super();
		this.description = description;
		this.amount = amount;
		this.stripeToken = stripeToken;
		this.currencyString = currencyString;
	}
	@Override
	public String toString() {
		return "ChargeRequest [description=" + description + ", amount=" + amount + ", currency=" + currency
				+ ", stripeEmail=" + ", stripeToken=" + stripeToken + "]";
	}

}
