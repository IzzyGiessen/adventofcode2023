package day5.classes;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

public class Category {
    public String name;
    public List<Range> seed;
    private List<Mapping> maps;

    public Category(String name) {
        this.name = name;
        this.seed = null;
        this.maps = new ArrayList<>();
    }

    public void addMap(Mapping map) {
        maps.add(map);
    }

    public List<Mapping> getMaps() {
        return this.maps;
    }
}
