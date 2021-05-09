package io.javabrains.coronavirustracker;

import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;

import javax.annotation.PostConstruct;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

//import com.sun.tools.javac.util.List;
import java.util.*;


@Service
public class CoronaVirusDataService {
	
	private static String VIRUS_DATA_URL = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv";
	
	private List<LocationStats> allStats =new ArrayList<>();
	public List<LocationStats> getAllStats() {
		return allStats;
	}
	@PostConstruct
	@Scheduled(cron = " * *  1 * * * ")
	public void fetchVirusData() throws IOException, InterruptedException {
		List<LocationStats> newStats = new ArrayList<>();
		HttpClient client=HttpClient.newHttpClient();
		HttpRequest request =HttpRequest.newBuilder().uri(URI.create(VIRUS_DATA_URL)).build();
		HttpResponse<String> httpResponse=client.send(request, HttpResponse.BodyHandlers.ofString());
		//System.out.println(httpResponse.body());
		StringReader csvBodyReader=new StringReader(httpResponse.body());
		Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(csvBodyReader);
		for (CSVRecord record : records) {
			LocationStats locationStat = new LocationStats();
			String s,c,d,s2;
			s=record.get("Province/State");
			c=record.get("Country/Region");
			d=record.get(record.size()-1);
			//System.out.println("check1"+s+"check2"+c+"check3"+d);
		    locationStat.setState(s);
		    s2=locationStat.getState();
		    locationStat.setCountry(c);
		    locationStat.setLatestTotalCases(Integer.parseInt(d));
		    //System.out.println(locationStat);
		    newStats.add(locationStat);
		}
		this.allStats = newStats;
	}

}
