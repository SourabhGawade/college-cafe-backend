package com.cc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cc.model.MenuRating;

public interface IMenuRatingRepo extends JpaRepository<MenuRating, Integer> {
	public MenuRating findByDishName(String dishName);
}
