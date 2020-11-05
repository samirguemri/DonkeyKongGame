package org.samir.projects.monkeykong;

import java.util.Random;

public class GameEngine {

    public int[][]  DKBoard;
    public final int xEdgeNumber;
    public final int yEdgeNumber;
    public final int edgeSize;

    public static final int FLOOR = 0;
    public static final int DONKEY_KONG = 1;
    public static final int WALL = 2;
    public static final int BANANA = 3;

    private static final Random random = new Random();


    public GameEngine(int complexity){
        xEdgeNumber = SettingsViewController.X_EDGE_NUMBER;
        yEdgeNumber = SettingsViewController.Y_EDGE_NUMBER;
        edgeSize = SettingsViewController.EDGE_SIZE;
        initEngine(complexity);
        displayDKBoard();
    }

    private void initEngine(int complexity) {
        int x; int y;
        DKBoard = new int[xEdgeNumber][yEdgeNumber];
        for (int i=0;i<xEdgeNumber;i++){
            for (int j=0;j<yEdgeNumber;j++)
                DKBoard[i][j]=0;
        }

        // Create BANANA
        x = random.nextInt(5);
        y = random.nextInt(5);
        DKBoard[xEdgeNumber-x][yEdgeNumber-y] = BANANA;

        // Create DONKEY_KONG
        x = random.nextInt(5);
        y = random.nextInt(5);
        DKBoard[x][y] = DONKEY_KONG;

        int gameCellNumber = xEdgeNumber * yEdgeNumber;
        int wallCellNumber = gameCellNumber * complexity / 100;

        for (int i=0;i<wallCellNumber;i++){
            x = random.nextInt(xEdgeNumber);
            y = random.nextInt(yEdgeNumber);
            int cell = DKBoard[x][y];
            if( cell != BANANA && cell != DONKEY_KONG && cell != WALL)
                DKBoard[x][y] = WALL;
            else
                i--;
        }

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
