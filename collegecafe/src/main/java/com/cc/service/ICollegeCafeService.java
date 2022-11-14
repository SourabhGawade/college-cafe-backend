package com.cc.service;

import java.util.List;

import com.cc.model.ItemFeedback;
import com.cc.model.MenuRating;

public interface ICollegeCafeService {
	
	public List<MenuRating> fetchAllRatings();
	public ItemFeedback saveFeedback(ItemFeedback itemFeedback);
	public MenuRating findRatingByFoodName(ItemFeedback itemFeedback);
}
