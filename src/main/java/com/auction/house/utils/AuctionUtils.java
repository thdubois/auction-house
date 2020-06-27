package com.auction.house.utils;

import com.auction.house.entity.AuctionStatus;

public class AuctionUtils {
	
	public static boolean isAuctionRunning(AuctionStatus auctionStatus) {
		return AuctionStatus.RUNNING == auctionStatus;
	}
	
	public static boolean isAuctionTerminated(AuctionStatus auctionStatus) {
		return AuctionStatus.TERMINATED == auctionStatus;
	}
	
}
