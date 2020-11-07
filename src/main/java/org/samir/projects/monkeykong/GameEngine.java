package org.samir.projects.monkeykong;

import java.util.*;

public class GameEngine {

    public int[][]  DKBoard;
    public final int xEdgeNumber;
    public final int yEdgeNumber;
    public final int edgeSize;

    public static final int FLOOR = 0;
    public static final int DONKEY_KONG = 1;
    public static final int WALL = 2;
    public static final int BANANA = 3;

    public Position dkPosition;
    public Position bananaPosition;

    private static final Random random = new Random();

    public GameEngine(int complexity){
        xEdgeNumber = SettingsViewController.X_EDGE_NUMBER;
        yEdgeNumber = SettingsViewController.Y_EDGE_NUMBER;
        edgeSize = SettingsViewController.EDGE_SIZE;
        initEngine(complexity);
    }

    private void initEngine(int complexity) {
        int x; int y;
        DKBoard = new int[xEdgeNumber][yEdgeNumber];
        for (int i=0;i<xEdgeNumber;i++){
            for (int j=0;j<yEdgeNumber;j++)
                DKBoard[i][j]=-1;
        }

        // Create DONKEY_KONG
        x = random.nextInt(5);
        y = random.nextInt(5);
        DKBoard[x][y] = DONKEY_KONG;
        dkPosition = new Position(x,y);

        // Create BANANA
        x = xEdgeNumber - random.nextInt(5) - 1;
        y = yEdgeNumber - random.nextInt(5) - 1;
        DKBoard[x][y] = BANANA;
        bananaPosition = new Position(x,y);

        // Create The FLOOR
        List<Position> floor = createFloor(dkPosition,bananaPosition);
        optimiseFloor(floor);
        for ( Position position : floor ) {
            DKBoard[position.x][position.y] = FLOOR;
        }
        DKBoard[dkPosition.x][dkPosition.y] = DONKEY_KONG;

        int gameCellNumber = xEdgeNumber * yEdgeNumber;
        int wallCellNumber = gameCellNumber * complexity / 100;

        for (int i=0;i<wallCellNumber;i++){
            x = random.nextInt(xEdgeNumber-1);
            y = random.nextInt(yEdgeNumber-1);
            int cell = DKBoard[x][y];
            if( cell == -1)
                DKBoard[x][y] = WALL;
            else
                i--;
        }

    }

    private void optimiseFloor(List<Position> floor) {
        List<Position> positionsToDelete = new ArrayList();
        for ( Position position : floor ) {
            boolean toDelete = checkDelete(position);
            if(toDelete)
                positionsToDelete.add(position);
        }
        for ( Position position : positionsToDelete ) {
            floor.remove(position);
        }
    }

    private boolean checkDelete(Position position) {
        if(position.x == 0 || position.x == xEdgeNumber-1 || position.y == 0 || position.y == yEdgeNumber-1)
            return false;
        int top = DKBoard[position.x][position.y-1];
        int bottom = DKBoard[position.x][position.y+1];
        int left = DKBoard[position.x-1][position.y];
        int right = DKBoard[position.x+1][position.y];
        if ((top == FLOOR && bottom == FLOOR) || (left == FLOOR && right == FLOOR))
            return true;
        return false;
    }

    private List<Position> createFloor(Position startPosition,Position targetPosition) {

        if (startPosition.equals(targetPosition))
            return null;

        List<Position> floor = new ArrayList();
        floor.add(startPosition);

        Position intermediatePosition = null;
        try {
            intermediatePosition = (Position) startPosition.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        boolean isX = random.nextBoolean();
        boolean isAddition;
        if (isX){
            // X value
            if (startPosition.x != targetPosition.x) {
                isAddition = random.nextBoolean();
                if (isAddition) {
                    // Addition operation
                    if (startPosition.x < xEdgeNumber-1 )
                        intermediatePosition.x ++;
                } else
                    // subtraction operation
                    if (startPosition.x > 0 )
                        intermediatePosition.x --;
            }
        } else {
            // Y value
            if (startPosition.y != targetPosition.y) {
                isAddition = random.nextBoolean();
                if (isAddition) {
                    // Addition operation
                    if (startPosition.y < yEdgeNumber-1 )
                        intermediatePosition.y ++;
                } else
                    // subtraction operation
                    if (startPosition.y > 0 )
                        intermediatePosition.y --;
            }
        }
        List<Position> floorLeft = createFloor(intermediatePosition,targetPosition);
        if (floorLeft != null)
            floor.addAll(floorLeft);
        return floor;
    }

    private void displayDKBoard(){
        System.out.print("[");
        for (int i=0;i<xEdgeNumber;i++){
            System.out.print("[");
            for (int j=0;j<yEdgeNumber;j++){
                System.out.print(DKBoard[i][j]);
                if (j != yEdgeNumber-1)
                    System.out.print(",");
                else if (i != xEdgeNumber-1)
                    System.out.println("],");
                else
                    System.out.print("]");

            }
        }
        System.out.println("]");
    }
}
