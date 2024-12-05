package com.example.krishnayadav.watchlist.Controler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.example.krishnayadav.watchlist.entity.movie;
import com.example.krishnayadav.watchlist.service.DatabaseService;

import jakarta.validation.Valid;
@RestController 
public class movieController {	
	
	@Autowired
	DatabaseService databaseService;
	
	@GetMapping("/index")
	public ModelAndView showIndex() {
		// TODO Auto-generated method stub
		String viewname = "index";
		Map<String, Object> model = new HashMap<String, Object>();
		
		return new ModelAndView(viewname,model);

	}
	@GetMapping("/watchlistitemForm")
	public ModelAndView showWatchListForm( @RequestParam(required = false) Integer id) {
		// TODO Auto-generated method stub
		String viewName = "watchlistitemForm";
		Map<String, Object> model = new HashMap<String, Object>();
		if(id==null) {
			model.put("watchlistItem", new movie());
		}
		else {
			model.put("watchlistItem", databaseService.getmovieByid(id));
		}
		
		return new ModelAndView(viewName,model);
	}
	
	@SuppressWarnings("unused")
	@PostMapping("/watchlistitemForm")
	public ModelAndView submitWatchListForm(@Valid @ModelAttribute("watchlistItem") movie Movie, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return new ModelAndView("watchlistitemForm");
		}
		Integer id = Movie.getId();
		if(id==null) {
			databaseService.created(Movie);
		}
		else {
			databaseService.update(Movie, id);
		}
		
	   
		RedirectView rd = new RedirectView();
		rd.setUrl("/watchlist");
		return new ModelAndView(rd);
	}
	@GetMapping("/watchlist")
	public ModelAndView getwatchlist() {
		// TODO Auto-generate method stub
		String viewName = "watchlist";
		Map<String, Object> model = new HashMap<>();
		List<movie> movieList = databaseService.getallmovie();
		model.put("watchlistrows", movieList);
		model.put("noofmovies", movieList.size());
		return new ModelAndView(viewName,model);
	}
}
