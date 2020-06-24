package com.auction.house.utils;

import org.springframework.core.convert.converter.Converter;

import com.auction.house.entity.AuctionStatus;

public class StringToAuctionStatusConverter implements Converter<String, AuctionStatus> {
	
	@Override
	public AuctionStatus convert(String status) {
		try {
			return AuctionStatus.valueOf(status.toUpperCase());
		} catch(IllegalArgumentException e) {
			return null;
		}
	}

}
