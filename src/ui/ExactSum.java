package ui;

import java.io.*;
import java.util.*;

class ExactSum {
    static List<int[]> cases = new ArrayList<int[]>();
    static List<Integer> money = new ArrayList<Integer>();
    static final String SPACE = " ";

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        readConsole(br);
        WriteSolution(bw);
        br.close();
        bw.close();
    }

    static void readConsole(BufferedReader br) throws NumberFormatException, IOException {
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
            line = br.readLine();
            if (line != null) {
                line = br.readLine();
            }
            render = (line == null) ? true : false;
        }
    }

    static void WriteSolution(BufferedWriter bw) throws NumberFormatException, IOException {
        sortBooksPrices(cases);
        for (int i = 0; i < cases.size(); i++) {
            bw.write(/* "Case " + (i + 1) + ":" + */getBestPricesForCase(cases.get(i), money.get(i)));
        }
        bw.flush();
    }

    static String getBestPricesForCase(int[] p, int money) {
        int bestPrice1 = 0;
        int bestPrice2 = 1000000;
        for (int i = 0; i < p.length; i++) {
            int priceBook1 = p[i];
            int priceBook2 = searchBinary(i + 1, p.length - 1, (money - priceBook1), p);
            if (priceBook2 > -1) {
                if ((priceBook2 - priceBook1) < (bestPrice2 - bestPrice1)) {
                    bestPrice1 = priceBook1;
                    bestPrice2 = priceBook2;
                }
            }
        }
        return "Peter should buy books whose prices are " + bestPrice1 + " and " + bestPrice2 + ".\n\n";
    }

    static void sortBooksPrices(List<int[]> prices) {
        for (int[] p : prices) {
            Arrays.sort(p);
        }
    }

    static int searchBinary(int lowest, int highest, int money, int[] p) {
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
}
