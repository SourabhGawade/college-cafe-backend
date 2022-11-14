package com.cc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cc.model.ItemFeedback;
import com.cc.model.MenuRating;
import com.cc.repository.IIteamFeedbackRepo;
import com.cc.repository.IMenuRatingRepo;

@Service
public class CollegeCafeServiceImpl implements ICollegeCafeService{

	@Autowired
	private IMenuRatingRepo menuRatingRepo;
	
	@Autowired
	private IIteamFeedbackRepo iteamFeedbackRepo;

	@Override
	public List<MenuRating> fetchAllRatings() {
		return menuRatingRepo.findAll();
	}

	@Override
	public ItemFeedback saveFeedback(ItemFeedback itemFeedback) {
		return iteamFeedbackRepo.save(itemFeedback);
	}

	@Override
	public MenuRating findRatingByFoodName(ItemFeedback itemFeedback) {
		MenuRating rating = menuRatingRepo.findByDishName(itemFeedback.getFeedbackItem());
		
		//checking if Item is already present in DB or not
		if (rating == null) {
			System.out.println("Adding new Item");
			MenuRating insertData = new MenuRating(itemFeedback.getFeedbackRating(),itemFeedback.getFeedbackItem(), 1, itemFeedback.getFeedbackRating().longValue());
			return menuRatingRepo.save(insertData);
		}else {
			System.out.println("Updating Existing Item");
			//fetching values to update current feedback count
			int feedbackSubmitted= rating.getTotalFeedbackSubmitted()+1;
			Long totalFeedback= (long) (rating.getTotalFeedbacks() + itemFeedback.getFeedbackRating());
			Double finalRating = Math.round(((double)totalFeedback/(double)feedbackSubmitted) * 10)/10.0;
			System.out.println(finalRating);
			//updating values for current feedback
			rating.setTotalFeedbackSubmitted(feedbackSubmitted);
			rating.setTotalFeedbacks(totalFeedback);
			rating.setTotalRating(finalRating);
			
			//updating values to DB
			 return menuRatingRepo.save(rating);
		}
	}
		
}
