package com.NathanNile.AssetTracker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.NathanNile.AssetTracker.entity.Asset;
import com.NathanNile.AssetTracker.entity.Booking;
import com.NathanNile.AssetTracker.service.AssetService;
import com.NathanNile.AssetTracker.service.BookingService;

@Controller
@RequestMapping("/bookings")
public class BookingController {
	
	@Autowired
	private AssetService assetService;
	
	private BookingService bookingService;
	
	@Autowired
	public BookingController(BookingService theBookingService) {
		bookingService = theBookingService;
	}
	
	
	@GetMapping("/list")
	public String showBookingsList(Model theModel) {
		
		List<Booking> theBookings = bookingService.findAll();
		
		theModel.addAttribute("bookings", theBookings);
		
		return "bookings/list-bookings";
	}
	
	@GetMapping("/showIndividualBookingsList")
	public String showIndividualBookingsList(@RequestParam("assetId") int theId, Model theModel) {
		 
		List<Booking> theBookings = bookingService.findByAssetIdOrderByStartOfBookingAsc(theId);
		
		theModel.addAttribute("bookings", theBookings);
		
		Asset theAsset = assetService.findById(theId);
		
		theModel.addAttribute("asset", theAsset);
		
		return "bookings/list-bookings";
	}
	
}
