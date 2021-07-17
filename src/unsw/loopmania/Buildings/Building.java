package unsw.loopmania.Buildings;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.*;
import org.javatuples.Pair;

import java.util.List;

/**
 * a basic form of building in the world
 */
public abstract class Building extends StaticEntity {
    // add more types of building, and make sure buildings have effects on entities as required by the spec

    public Building(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
    }

    public abstract BuildingInfo buildingEffect(LoopManiaWorld lmw, BuildingInfo newChanges);

    /**
     * 
     * @param building
     * @param orderedPath
     * @param enemyList
     * @return validSpawnPosition for ZombiePit and VampireCastle
     * This position must be top, bottom, left or right of building and have no enemy
     */
    public final Pair<Integer, Integer> getSpecificSpawnPosition(Building building, List<Pair<Integer, Integer>> orderedPath, List<Enemy> enemyList){
        int buildingX = building.getX();
        int buildingY = building.getY();

        // check if there is a path to the top, bottom, left or right of the vampire castle
        for (Pair<Integer, Integer> onePath : orderedPath) {
            
            //check if onePath has an enemy
            if (isEnemyOnPath(onePath, enemyList)) continue;

            // (case1) top
            if (onePath.equals(new Pair<Integer, Integer>(buildingX, buildingY-1))) {
                return onePath;
            }
            // (case2) bottom
            if (onePath.equals(new Pair<Integer, Integer>(buildingX, buildingY+1))) {
                return onePath;
            }
            // (case3) left
            if (onePath.equals(new Pair<Integer, Integer>(buildingX-1, buildingY))) {
                return onePath;
            }
            // (case4) right
            if (onePath.equals(new Pair<Integer, Integer>(buildingX+1, buildingY))) {
                return onePath;
            }
        }
        return null;
    }

    /**
     * 
     * @param path
     * @param enemyList
     * @return true if there is an enemy on the same position
     */
    private boolean isEnemyOnPath (Pair<Integer, Integer> path, List<Enemy> enemyList){
        for (Enemy e : enemyList){
            if (e.getX() == path.getValue0() && e.getY() == path.getValue1()) return true;
        }
        return false;
    }

}
