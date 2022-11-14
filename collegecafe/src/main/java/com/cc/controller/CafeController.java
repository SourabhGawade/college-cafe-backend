package com.cc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cc.model.ItemFeedback;
import com.cc.model.MenuRating;
import com.cc.service.CollegeCafeServiceImpl;

@CrossOrigin(origins = "http://192.168.0.108:8080/", maxAge = 3600)
@RestController
public class CafeController {
	
	@Autowired
	CollegeCafeServiceImpl ccService;
	
	@PostMapping("/submitFeedback")
	public ResponseEntity<?>  submitFeedback(@RequestBody ItemFeedback itemFeedback) {
		System.out.println(itemFeedback);
		ItemFeedback res = ccService.saveFeedback(itemFeedback);
		System.out.println(res);
		MenuRating rating = ccService.findRatingByFoodName(itemFeedback);
		System.out.println(rating);
		return new ResponseEntity<>(res,HttpStatus.OK);
	}
	
	
	@GetMapping("/getAllFeedbacks")
	public ResponseEntity<?>  getAllFeedbacks() {
		/*MenuRating menu1 = new MenuRating(1,4, "Pizza", 12, 50);
		MenuRating menu2 = new MenuRating(2,5, "Fries", 20, 100);
		List<MenuRating> list= new ArrayList<MenuRating>();
		list.add(menu1);list.add(menu2);
		//response.setHeader("Access-Control-Allow-Origin", "http://localhost:8080/");
		 * /*
		 */
		
		List<MenuRating> ratinglist= ccService.fetchAllRatings(); 
		return new ResponseEntity<List<MenuRating>>(ratinglist,HttpStatus.OK);
	}
	
}
