package deeplay;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


public class Solution {
    public static int getResult(String data, String race) throws Exception {
        if (data.length() != 16)
            throw new Exception("The first argument must be 16 characters long");

        FileReader fileReader = new FileReader("src/main/resources/ObstacleCoding");
        BufferedReader reader = new BufferedReader(fileReader);
        var obstacles = reader.readLine().split("");
        var gameCells = data.split("");
        for (var i : gameCells)
            if (!Arrays.asList(obstacles).contains(i))
                throw new Exception("Entered data contains incorrect characters");

        Map<String, Integer> obstacle = new HashMap<>(); // пары <препятствие, стоимость прохождения>
        int[][] A = new int[5][5]; // содержит в каждой ячейке стоимость прохождения соответствующего ей препятствия
        int[][] B = new int[5][5]; // содержит в каждой ячейке стоимость оптимального маршрута до этой ячейки

        fileReader = new FileReader("src/main/resources/" + race);
        reader = new BufferedReader(fileReader);

        String line = reader.readLine();
        while (line != null) {
            var splitLine = line.split(" ");
            obstacle.put(splitLine[0], Integer.valueOf(splitLine[1]));
            line = reader.readLine();
        }


        for (int i = 0; i < 16; i++)
            A[i / 4 + 1][i % 4 + 1] = obstacle.get(gameCells[i]);

        for (int i = 0; i < 5; i++) {
            B[0][i] = 10;
            B[i][0] = 10;
        }

        for (int i = 1; i < 5; i++)
            for (int j = 1; j < 5; j++) {
                if (i == j && i == 1) {
                    B[i][j] = A[i][j];
                    continue;
                }
                B[i][j] = Math.min(B[i - 1][j], B[i][j - 1]) + A[i][j];
            }

        return B[4][4] - A[1][1];
    }
}
