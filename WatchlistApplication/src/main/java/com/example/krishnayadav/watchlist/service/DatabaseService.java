package com.example.krishnayadav.watchlist.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.example.krishnayadav.watchlist.Reposetory.moveRepo;
import com.example.krishnayadav.watchlist.entity.movie;

import jakarta.validation.Valid;

@Service
public class DatabaseService {
	
	@Autowired
	moveRepo moverepo;
	
	@Autowired
	RatingService ratingService;
	public void created(movie Movie) {
		String rating = ratingService.getmovieRating(Movie.getTitle());
		if(rating!=null) {
			Movie.setRating(Float.parseFloat(rating));
		}
		moverepo.save(Movie);
		
	}
	public List<movie> getallmovie() {
		// TODO Auto-generated method stub
		return moverepo.findAll();

	}
	public movie getmovieByid(Integer id) {
		// TODO Auto-generated method stub
		return moverepo.findById(id).get();
	}
	public void update(@Valid movie Movie, Integer id) {
		// TODO Auto-generated method stub
		movie toBeUpdateMovie = getmovieByid(id);
		toBeUpdateMovie.setComment(Movie.getComment());
		toBeUpdateMovie.setRating(Movie.getRating());
		toBeUpdateMovie.setTitle(Movie.getTitle());
		toBeUpdateMovie.setPriority(Movie.getPriority());
		moverepo.save(toBeUpdateMovie);
	}


}
