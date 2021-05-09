package io.javabrains.coronavirustracker;

public class LocationStats {
	
	private String state;
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
	private String country;
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	
	private int latestTotalCases;
	public int getLatestTotalCases() {
		return latestTotalCases;
	}
	public void setLatestTotalCases(int latestTotalCases) {
		this.latestTotalCases = latestTotalCases;
	}
	
	@Override
	public String toString() {
		return "LocationStats{" + 
				"State= '" + state + '\'' + 
				",Country= '"+ country + '\'' +
				",latestTotalCases= "+ latestTotalCases +
				'}';
	}

}
