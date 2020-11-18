import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class Repository extends Observable {
    private List<Integer> x_coordinates = new ArrayList<>();
    private List<Integer> y_coordinates = new ArrayList<>();

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
