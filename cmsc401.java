//Jordan Weiss
//CMSC 401
//Assignment 4
//Rod cutting Variation

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class cmsc401{

    public static int getMin(int[][] subProb, int[] cutPlace, int numCuts, int rodSize){
        
        //Process can begin by filling in diaganol of subproblem matrix
        //representing a single cut. Cost will be length of rod since it
        //is only one cut.

        for(int i = 0; i < numCuts; i++){
            subProb[i][i+2] = cutPlace[i+2] - cutPlace[i];
        }

        //Bottum up dynamic programming approach. Complexity is numCuts^3.
        //Loops fill in a coordinating subproblem map, using the minimum of 
        //preceding subproblems to continue throught the map. The top right 
        //corner of 2D array will then contain the minimum cost of any order of
        //cutting

        for(int i = 3; i <= numCuts + 1; i++){
            for(int j = 0; j <= numCuts+1-i; j++){
                int best_found = subProb[j+1][j+i];
                for(int k = j+2; k < j+i; k++){
                    if(subProb[j][k] + subProb[k][j+i] < best_found)
                        best_found = subProb[j][k] + subProb[k][j+i];
                }
                subProb[j][j+i] = cutPlace[j+i] - cutPlace[j] + best_found;
            }
        }
        return subProb[0][numCuts+1];
    }

    public static void main(final String[] args){
        Scanner sc = new Scanner(System.in);
        int rodSize = sc.nextInt();
        int numCuts = sc.nextInt();

        //Array must be created to store the results of subproblems,
        //size of array is determined by the total number of cuts and the ends of the rod,
        //0 and rodSize

        int[][] subProb = new int[numCuts + 2][numCuts + 2];
        
        //Loop below creates the 2D subproblem array, initializing all 
        //subproblem results to 0

        for(int i = 0; i < numCuts + 2; i++){
            for(int j = 0; j < numCuts + 2; j++){
                subProb[i][j] = 0;
            }
        }

        int[] cutPlacement = new int[numCuts + 2];
        cutPlacement[0] = 0;
        cutPlacement[numCuts + 1] = rodSize;

        //Loop below scans input from the user and records cut locations into
        //an array
        
        for(int i = 1; i <= numCuts; i++){
            cutPlacement[i] = sc.nextInt();
        }
        System.out.println(getMin(subProb, cutPlacement, numCuts, rodSize));
    }
}
