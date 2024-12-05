package com.example.krishnayadav.watchlist.Reposetory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.krishnayadav.watchlist.entity.movie;

@Repository
public interface moveRepo extends JpaRepository<movie, Integer> {
	
}
