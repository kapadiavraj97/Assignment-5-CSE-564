

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class NearestNeighbour {
	
	int lastCityVisited;
	LinkedList<City> sRoute = new LinkedList<City>();
    public static List<Integer> costList = new ArrayList<>();
	
	public Route findShortestRoute(LinkedList<City> cities , int startValue) {
		sRoute = cities;
		LinkedList<City> shortestRouteCities = new LinkedList<City>();
		City city = sRoute.get(startValue);
		updateRoutes(shortestRouteCities, city);
		while(sRoute.size() >= 1) {
			city = getNextCity(city);
			updateRoutes(shortestRouteCities, city);
		}
		return new Route(shortestRouteCities);
	}
	
	private void updateRoutes(LinkedList<City> shortestRouteCities, City city) {
		shortestRouteCities.add(city);
		sRoute.remove(city);
	}
	private City getNextCity(City city) {
		City minCity = city;
		int mindist = Integer.MAX_VALUE;
		for(int i = 0;i<=sRoute.size()-1 ; i++) {
			if(city.getX() != sRoute.get(i).getX() && city.getY() != sRoute.get(i).getY()) {
				int deltaX = (city.getX() - sRoute.get(i).getX());
				int deltaY = (city.getY() - sRoute.get(i).getY());
				int distance = (int) Math.sqrt( (deltaX*deltaX) + (deltaY*deltaY) );
				if (mindist > distance) {
					mindist = distance;
					minCity = sRoute.get(i);
				}
			}
		}
		return minCity;
	}
	

}
