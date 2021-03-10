package ui;

import java.io.*;
import java.util.*;

public class ExactSum {
    private static List<int[]> cases = new ArrayList<int[]>();
    private static List<Integer> money = new ArrayList<Integer>();
    private static final String SPACE = " ";
    private static int bestPrice1 = 0;
    private static int bestPrice2 = 1000000;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        readConsole(br);
        WriteSolution(bw);
        br.close();
        bw.close();
    }

    public static void readConsole(BufferedReader br) throws IOException {
        String nextLine = ";";
        while (br.readLine() != null || nextLine != null) {
            int size = Integer.parseInt(br.readLine());
            int[] booksPrices = new int[size];
            String[] parts = br.readLine().split(SPACE);
            for (int i = 0; i < booksPrices.length; i++) {
                booksPrices[i] = Integer.parseInt(parts[i]);
            }
            cases.add(booksPrices);
            money.add(Integer.parseInt(br.readLine()));
            nextLine = br.readLine();
        }

    }

    public static void WriteSolution(BufferedWriter bw) throws IOException {

    }

    public static void sortBooksPrices(List<int[]> prices) {
        for (int[] p : prices) {
            Arrays.sort(p);
        }
    }

    public static int searchBinary(int lowest, int highest, int money, int[] p) {
        if (lowest > highest) {
            return -1;
        }
        int medium = (lowest + highest) / 2;
        if (p[medium] > money) {
            return searchBinary(lowest, medium - 1, money, p);
        } else if (p[medium] < money) {
            return searchBinary(medium + 1, highest, money, p);
        } else {
            return money;
        }
    }

    public static int difference(int a, int b) {
        if (a <= b)
            return b - a;
        else
            return a - b;
    }
}
