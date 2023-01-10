import java.util.Arrays;
import java.util.Scanner;

public class MineSweeper {
    int row;
    int col;
    int mine;
    String[][] mineMap;
    String[][] gameMap;

    MineSweeper(int row, int col) {
        this.row = row;
        this.col = col;
        this.mine = (row * col) / 4;
        this.mineMap = new String[row][col];
        this.gameMap = new String[row][col];
    }

    void gameMap() {
        for (String[] strings : this.gameMap) {
            Arrays.fill(strings, " - ");
        }
    }

    void printMap(String[][] Str) {
        System.out.print(" \\ ");
        for (int i = 0; i < col; i++) {
            System.out.print("(" + i + ")");
        }
        int i = 0;
        System.out.println();
        for (String[] strings : Str) {
            System.out.print("(" + i++ + ")");
            for (String string : strings) {
                System.out.print(string);
            }
            System.out.println();
        }
    }

    void setMine() {
        while (this.mine > 0) {
            int randomRow = (int) (Math.random() * this.row);
            int randomCol = (int) (Math.random() * this.col);
            if (this.mineMap[randomRow][randomCol] != " * ") {
                this.mineMap[randomRow][randomCol] = " * ";
                this.mine--;
            }
        }
        for (int i = 0; i < this.mineMap.length; i++) {
            for (int j = 0; j < this.mineMap[i].length; j++) {
                if (this.mineMap[i][j] != " * ") {
                    this.mineMap[i][j] = " - ";
                }
            }
        }
    }

    void run() {
        Scanner input = new Scanner(System.in);
        int totalMove = (this.row * this.col) - this.mine;

        gameMap();
        setMine();
        System.out.println("Welcome to the MineSweeper");

        while (totalMove > 0) {
            System.out.println("---------------------");
            System.out.println("Remaining moves : " + totalMove);
            printMap(this.gameMap);
            System.out.print("Enter the row number : ");
            int y = input.nextInt();
            System.out.print("Enter the column number : ");
            int x = input.nextInt();
            int count = 0;

            if (y < 0 || y >= this.row || x < 0 || x >= this.col) {
                System.out.println("Wrong index. Please try again!");
                continue;
            } else {
                if (this.mineMap[y][x] == " * ") {
                    System.out.println("Game Over.");
                    break;
                }
                if (this.gameMap[y][x] != " - ") {
                    System.out.println("You already did this move!");
                    continue;
                }
                else {
                    int minRow = y - 1, maxRow = y + 1;
                    int minCol = x - 1, maxCol = x + 1;

                    minRow = Math.max(minRow, 0);
                    minCol = Math.max(minCol, 0);
                    maxRow = Math.min(maxRow, (this.row - 1));
                    maxCol = Math.min(maxCol, (this.col - 1));

                    for (int i = minRow; i <= maxRow; i++) {
                        for (int j = minCol; j <= maxCol; j++) {
                            if (this.mineMap[i][j] == " * ")
                                count++;
                        }
                    }
                    String temp = String.valueOf(count);
                    this.gameMap[y][x] = " " + temp + " ";
                    totalMove--;
                }
            }
        }
        if (totalMove == 0) {
            System.out.println("You Win!");
            printMap(this.mineMap);
        }
    }







































}
