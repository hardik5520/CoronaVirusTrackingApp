package io.javabrains.coronavirustracker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


import java.util.*;

import  java.io.*;

@Controller
public class HomeController {
	
	@Autowired
	CoronaVirusDataService dataserviceobj;
	@GetMapping("/")
	public String home(Model model) {
		List<LocationStats> allStats=dataserviceobj.getAllStats();
		int totalcases=allStats.stream().mapToInt(stat -> stat.getLatestTotalCases()).sum();
		model.addAttribute("locationStats", allStats);
		model.addAttribute("totalcases",totalcases);
		return "home";
	}
	

}
