import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class Repository extends Observable {
    private static Repository repository;
    static private List<Integer> x_coordinates = new ArrayList<>();
    static private List<Integer> y_coordinates = new ArrayList<>();
    static private List<Integer> route1 = new ArrayList<>();
    static private List<Integer> route2 = new ArrayList<>();
    static private List<Integer> route3 = new ArrayList<>();

    public static void setRoute1(List<Integer> route1) {
        Repository.route1 = route1;
    }

    public static void setRoute2(List<Integer> route2) {
        Repository.route2 = route2;
    }

    public static void setRoute3(List<Integer> route3) {
        Repository.route3 = route3;
    }

    public static List<Integer> getRoute1() {
        return route1;
    }

    public static List<Integer> getRoute2() {
        return route2;
    }

    public static List<Integer> getRoute3() {
        return route3;
    }

    private Repository(){ }

    public static Repository createInstance(){
        if(repository==null){
            repository = new Repository();
        }
        return repository;
    }

    public List<Integer> getX_coordinates() {
        return x_coordinates;
    }

    public List<Integer> getY_coordinates() {
        return y_coordinates;
    }

    public void addCoordinates(List<Integer> x, List<Integer> y){
        this.x_coordinates.addAll(x);
        this.y_coordinates.addAll(y);
        setChanged();
        notifyObservers();
    }

    public void updateRepository(List<Integer> x, List<Integer> y) {
        this.x_coordinates = x;
        this.y_coordinates = y;
        setChanged();
        notifyObservers();
    }

}

