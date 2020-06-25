package com.auction.house.utils;

import com.auction.house.entity.AuctionStatus;

public class BidUtils {

	public static boolean isBidHigherThanCurrentPrice(float bid, float currentPrice) {
		return bid > currentPrice;
	}
	
	public static boolean isBidHigherThanStartPrice(float bid, float startPrice) {
		return bid > startPrice;
	}
	
	public static boolean isAuctionRunning(AuctionStatus auctionStatus) {
		return AuctionStatus.RUNNING == auctionStatus;
	}
}
