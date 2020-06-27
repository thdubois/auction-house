package com.auction.house;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.auction.house.entity.AuctionStatus;
import com.auction.house.utils.AuctionUtils;
import com.fasterxml.jackson.databind.util.ArrayBuilders.BooleanBuilder;
import org.springframework.test.context.junit4.*;

@SpringBootTest
public class AuctionUtilsTest {

	@Test
	void isAuctionRunningFalseCase() {
		boolean isAuctionRunning =  AuctionUtils.isAuctionRunning(AuctionStatus.DELETED);
		assertEquals(false, isAuctionRunning);
	}
	
	@Test
	void isAuctionRunningTrueCase() {
		boolean isAuctionRunning =  AuctionUtils.isAuctionRunning(AuctionStatus.RUNNING);
		assertEquals(true, isAuctionRunning);
	}
	
	@Test
	void isAuctionTerminatedTrueCase() {
		boolean isAuctionRunning =  AuctionUtils.isAuctionTerminated(AuctionStatus.TERMINATED);
		assertEquals(true, isAuctionRunning);
	}

}
