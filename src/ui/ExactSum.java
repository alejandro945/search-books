package ui;

import java.io.*;
import java.util.*;

public class ExactSum {
    private static List<int[]> cases = new ArrayList<int[]>();
    private static List<Integer> money = new ArrayList<Integer>();
    private static final String SPACE = " ";

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        readConsole(br);
        WriteSolution(bw);
        br.close();
        bw.close();
    }

    public static void readConsole(BufferedReader br) throws IOException {
        boolean render = false;
        String line = br.readLine();
        while (!render) {
            int size = Integer.parseInt(line);
            int[] booksPrices = new int[size];
            String[] parts = br.readLine().split(SPACE);
            for (int i = 0; i < booksPrices.length; i++) {
                booksPrices[i] = Integer.parseInt(parts[i]);
            }
            cases.add(booksPrices);
            money.add(Integer.parseInt(br.readLine()));
            String nextLine = br.readLine();
            line = br.readLine();
            render = (nextLine.equals("") && line.equals("")) ? true : false;
        }

    }

    public static void WriteSolution(BufferedWriter bw) throws IOException {
        sortBooksPrices(cases);
        for (int i = 0; i < cases.size(); i++) {
            bw.write("Case " + (i + 1) + ":" + getBestPricesForCase(cases.get(i), money.get(i)));
            bw.flush();
        }
    }

    public static String getBestPricesForCase(int[] p, int money) {
        int bestPrice1 = 0;
        int bestPrice2 = 1000000;
        for (int i = 0; i < p.length; i++) {
            int priceBook1 = p[i];
            int priceBook2 = searchBinary(i + 1, p.length - 1, (money - priceBook1), p);
            if (priceBook2 > -1) {
                if (Math.abs(priceBook2 - priceBook1) < Math.abs(bestPrice2 - bestPrice1)) {
                    bestPrice1 = priceBook1;
                    bestPrice2 = priceBook2;
                }
            }
        }
        return " Peter should buy books whose prices are " + bestPrice1 + " and " + bestPrice2 + ".\n\n";
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
