package com.auction.house.utils;

public class BidUtils {

	public static boolean isBidHigherThanCurrentPrice(float bid, float currentPrice) {
		return bid > currentPrice;
	}
	
	public static boolean isBidHigherThanStartPrice(float bid, float startPrice) {
		return bid > startPrice;
	}
	
}
