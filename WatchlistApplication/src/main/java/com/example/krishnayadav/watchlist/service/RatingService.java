package com.example.krishnayadav.watchlist.service;


import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.node.ObjectNode;

@Service
public class RatingService {
	
	String apiUrl = "https://www.omdbapi.com/?apikey=a8431cee&t=";
	public String getmovieRating(String title) {
		// TODO Auto-generated method stub
		try {
			//try to fetch the rating by omdb api
			RestTemplate template = new RestTemplate();
			ResponseEntity<ObjectNode> response = template.getForEntity(apiUrl + title, ObjectNode.class);
			ObjectNode jsonObject = response.getBody();
			System.out.println(jsonObject.path("imdbRating").asText());
			return jsonObject.path("imdbRating").asText();
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println("Either movie name is not available or api is down"+ e.getMessage());
			return null;
			
		}
		
	}

}
