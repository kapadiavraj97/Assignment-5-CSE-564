

import java.util.LinkedList;

public class Route {
	
	LinkedList<City> cities = new LinkedList<City>();
	
	public Route(LinkedList<City> cities) {
		this.cities.addAll(cities);
	}
	
	public LinkedList<City> getCities(){
		return cities;
	}
	
	public int calculateTotalDistance() {
		int citiesSize = this.getCities().size();
		return (int)(this.getCities().stream().mapToDouble(x -> {
			int cityIndex = this.getCities().indexOf(x);
			double returnValue = 0;
			if (cityIndex < citiesSize - 1) {
				returnValue = x.measureDistance(this.getCities().get(cityIndex + 1));
			}
			return returnValue;
		}).sum() + this.getCities().get(citiesSize - 1).measureDistance(this.getCities().get(0)));
	}
	
	public String toString() {
		String s = "";
        for (int i = 0; i < cities.size(); i++) {
		    s = s + cities.get(i).getX() + ", ";
	    }
        return s;
	}
	
}
