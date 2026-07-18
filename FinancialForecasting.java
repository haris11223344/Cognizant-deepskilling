/*
 * ============================================================================
 *  FINANCIAL FORECASTING
 *  Data Structures & Algorithms Exercise
 * ============================================================================
 *
 *  Author        : Haris K Sumesh
 *  Reg. No.      : 23BSA10085
 *  Course        : B.Tech CSE - Data Structures & Algorithms
 *  Topic         : Applying core DSA concepts to a financial forecasting
 *                  problem (stock / revenue price prediction)
 *
 *  DSA CONCEPTS DEMONSTRATED
 *  ---------------------------------------------------------------------
 *   1. Dynamic Arrays (ArrayList)      -> storing historical price series
 *   2. Sliding Window (ArrayDeque)     -> O(n) Simple Moving Average
 *   3. Linked List (custom singly LL)  -> chronological price ledger
 *   4. Sorting (merge sort, manual)    -> ranking daily returns
 *   5. Binary Search                   -> locating price on a given day
 *   6. Prefix Sum Array                -> O(1) range-sum queries
 *   7. Greedy Algorithm                -> max profit (single buy/sell)
 *   8. Divide & Conquer / Regression   -> least-squares linear trend forecast
 *   9. Exponential Moving Average      -> weighted recursive smoothing
 *
 *  Each method includes its time & space complexity in comments.
 * ============================================================================
 */

import java.util.*;

public class FinancialForecasting {

    // =========================================================================
    // 1. CUSTOM SINGLY LINKED LIST — chronological ledger of prices
    // =========================================================================
    static class PriceNode {
        int day;
        double price;
        PriceNode next;

        PriceNode(int day, double price) {
            this.day = day;
            this.price = price;
        }
    }

    static class PriceLedger {
        PriceNode head, tail;
        int size = 0;

        // O(1) append
        void add(int day, double price) {
            PriceNode node = new PriceNode(day, price);
            if (head == null) {
                head = tail = node;
            } else {
                tail.next = node;
                tail = node;
            }
            size++;
        }

        // O(n) traversal -> convert to array for algorithms that need
        // random access (arrays are more efficient there than linked lists)
        double[] toArray() {
            double[] arr = new double[size];
            PriceNode cur = head;
            int i = 0;
            while (cur != null) {
                arr[i++] = cur.price;
                cur = cur.next;
            }
            return arr;
        }

        void print() {
            PriceNode cur = head;
            StringBuilder sb = new StringBuilder();
            while (cur != null) {
                sb.append(String.format("Day %2d: %.2f", cur.day, cur.price));
                if (cur.next != null) sb.append("  ->  ");
                cur = cur.next;
            }
            System.out.println(sb);
        }
    }

    // =========================================================================
    // 2. SLIDING WINDOW — Simple Moving Average (SMA) using ArrayDeque
    //    Time: O(n)   Space: O(k)   where k = window size
    // =========================================================================
    static double[] simpleMovingAverage(double[] prices, int windowSize) {
        int n = prices.length;
        if (windowSize <= 0 || windowSize > n) {
            throw new IllegalArgumentException("Invalid window size");
        }
        double[] sma = new double[n - windowSize + 1];
        Deque<Double> window = new ArrayDeque<>();
        double windowSum = 0.0;

        for (int i = 0; i < n; i++) {
            window.addLast(prices[i]);
            windowSum += prices[i];

            if (window.size() > windowSize) {
                windowSum -= window.pollFirst();
            }
            if (window.size() == windowSize) {
                sma[i - windowSize + 1] = windowSum / windowSize;
            }
        }
        return sma;
    }

    // =========================================================================
    // 3. WEIGHTED MOVING AVERAGE — more weight to recent prices
    //    Time: O(n*k)   Space: O(n)
    // =========================================================================
    static double[] weightedMovingAverage(double[] prices, int windowSize) {
        int n = prices.length;
        double[] wma = new double[n - windowSize + 1];
        int weightSum = windowSize * (windowSize + 1) / 2;

        for (int i = 0; i <= n - windowSize; i++) {
            double sum = 0.0;
            int weight = 1;
            for (int j = i; j < i + windowSize; j++) {
                sum += prices[j] * weight;
                weight++;
            }
            wma[i] = sum / weightSum;
        }
        return wma;
    }

    // =========================================================================
    // 4. EXPONENTIAL MOVING AVERAGE (EMA) — recursive weighted smoothing
    //    Time: O(n)   Space: O(n)
    // =========================================================================
    static double[] exponentialMovingAverage(double[] prices, double smoothing) {
        int n = prices.length;
        double[] ema = new double[n];
        ema[0] = prices[0];
        for (int i = 1; i < n; i++) {
            ema[i] = smoothing * prices[i] + (1 - smoothing) * ema[i - 1];
        }
        return ema;
    }

    // =========================================================================
    // 5. PREFIX SUM ARRAY — O(1) range sum / range average queries
    //    Build: O(n)   Query: O(1)
    // =========================================================================
    static double[] buildPrefixSum(double[] prices) {
        double[] prefix = new double[prices.length + 1];
        for (int i = 0; i < prices.length; i++) {
            prefix[i + 1] = prefix[i] + prices[i];
        }
        return prefix;
    }

    static double rangeAverage(double[] prefix, int startDay, int endDayInclusive) {
        double rangeSum = prefix[endDayInclusive + 1] - prefix[startDay];
        int count = endDayInclusive - startDay + 1;
        return rangeSum / count;
    }

    // =========================================================================
    // 6. MERGE SORT — sort daily returns to compute best/worst/median return
    //    Time: O(n log n)   Space: O(n)
    // =========================================================================
    static void mergeSort(double[] arr, int left, int right) {
        if (left >= right) return;
        int mid = left + (right - left) / 2;
        mergeSort(arr, left, mid);
        mergeSort(arr, mid + 1, right);
        merge(arr, left, mid, right);
    }

    private static void merge(double[] arr, int left, int mid, int right) {
        double[] leftArr = Arrays.copyOfRange(arr, left, mid + 1);
        double[] rightArr = Arrays.copyOfRange(arr, mid + 1, right + 1);
        int i = 0, j = 0, k = left;
        while (i < leftArr.length && j < rightArr.length) {
            arr[k++] = (leftArr[i] <= rightArr[j]) ? leftArr[i++] : rightArr[j++];
        }
        while (i < leftArr.length) arr[k++] = leftArr[i++];
        while (j < rightArr.length) arr[k++] = rightArr[j++];
    }

    static double[] dailyReturns(double[] prices) {
        double[] returns = new double[prices.length - 1];
        for (int i = 1; i < prices.length; i++) {
            returns[i - 1] = ((prices[i] - prices[i - 1]) / prices[i - 1]) * 100.0;
        }
        return returns;
    }

    // =========================================================================
    // 7. BINARY SEARCH — locate the price for a specific day quickly
    //    Time: O(log n)   Space: O(1)
    //    Precondition: days[] must be sorted (naturally true for a timeline)
    // =========================================================================
    static double binarySearchPriceByDay(int[] days, double[] prices, int targetDay) {
        int lo = 0, hi = days.length - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (days[mid] == targetDay) return prices[mid];
            else if (days[mid] < targetDay) lo = mid + 1;
            else hi = mid - 1;
        }
        return -1; // day not found
    }

    // =========================================================================
    // 8. GREEDY ALGORITHM — maximum profit from a single buy/sell transaction
    //    Time: O(n)   Space: O(1)
    // =========================================================================
    static double maxProfitSingleTransaction(double[] prices) {
        double minPriceSoFar = prices[0];
        double maxProfit = 0.0;
        for (double price : prices) {
            minPriceSoFar = Math.min(minPriceSoFar, price);
            maxProfit = Math.max(maxProfit, price - minPriceSoFar);
        }
        return maxProfit;
    }

    // =========================================================================
    // 9. LINEAR REGRESSION FORECAST — least-squares trend line y = a + bx
    //    Used to project future prices beyond the known data.
    //    Time: O(n)   Space: O(1)
    // =========================================================================
    static double[] fitLinearRegression(double[] prices) {
        int n = prices.length;
        double sumX = 0, sumY = 0, sumXY = 0, sumXX = 0;

        for (int i = 0; i < n; i++) {
            sumX += i;
            sumY += prices[i];
            sumXY += (double) i * prices[i];
            sumXX += (double) i * i;
        }

        double slope = (n * sumXY - sumX * sumY) / (n * sumXX - sumX * sumX);
        double intercept = (sumY - slope * sumX) / n;
        return new double[]{intercept, slope}; // [a, b] for y = a + b*x
    }

    static double[] forecastFutureDays(double[] prices, int daysAhead) {
        double[] coeffs = fitLinearRegression(prices);
        double intercept = coeffs[0], slope = coeffs[1];
        int n = prices.length;

        double[] forecast = new double[daysAhead];
        for (int i = 0; i < daysAhead; i++) {
            int x = n + i;
            forecast[i] = intercept + slope * x;
        }
        return forecast;
    }

    // =========================================================================
    // MAIN — demonstration / test harness
    // =========================================================================
    public static void main(String[] args) {

        System.out.println("=====================================================");
        System.out.println(" FINANCIAL FORECASTING - DSA DEMONSTRATION");
        System.out.println(" Haris K Sumesh | 23BSA10085");
        System.out.println("=====================================================\n");

        // Sample 15-day closing price series (e.g. a stock or revenue trend)
        double[] closingPrices = {
                102.5, 104.0, 103.2, 105.8, 107.1,
                106.4, 108.9, 110.2, 109.5, 111.8,
                113.0, 112.4, 114.6, 116.1, 115.7
        };
        int[] days = new int[closingPrices.length];
        for (int i = 0; i < days.length; i++) days[i] = i + 1;

        // ---- 1. Linked list ledger ----
        PriceLedger ledger = new PriceLedger();
        for (int i = 0; i < closingPrices.length; i++) {
            ledger.add(days[i], closingPrices[i]);
        }
        System.out.println("1) Price Ledger (Linked List):");
        ledger.print();
        System.out.println();

        // ---- 2. Simple Moving Average ----
        int window = 3;
        double[] sma = simpleMovingAverage(closingPrices, window);
        System.out.println("2) " + window + "-Day Simple Moving Average (Sliding Window):");
        System.out.println(Arrays.toString(roundArray(sma)));
        System.out.println();

        // ---- 3. Weighted Moving Average ----
        double[] wma = weightedMovingAverage(closingPrices, window);
        System.out.println("3) " + window + "-Day Weighted Moving Average:");
        System.out.println(Arrays.toString(roundArray(wma)));
        System.out.println();

        // ---- 4. Exponential Moving Average ----
        double[] ema = exponentialMovingAverage(closingPrices, 0.3);
        System.out.println("4) Exponential Moving Average (alpha = 0.3):");
        System.out.println(Arrays.toString(roundArray(ema)));
        System.out.println();

        // ---- 5. Prefix sum range query ----
        double[] prefix = buildPrefixSum(closingPrices);
        double avgDays5to10 = rangeAverage(prefix, 4, 9); // 0-indexed: day 5 to day 10
        System.out.println("5) Prefix Sum Query -> Average price, Day 5 to Day 10: "
                + round(avgDays5to10));
        System.out.println();

        // ---- 6. Sorted daily returns (Merge Sort) ----
        double[] returns = dailyReturns(closingPrices);
        double[] sortedReturns = returns.clone();
        mergeSort(sortedReturns, 0, sortedReturns.length - 1);
        System.out.println("6) Daily % Returns sorted (Merge Sort):");
        System.out.println(Arrays.toString(roundArray(sortedReturns)));
        System.out.println("   Best single-day return : " + round(sortedReturns[sortedReturns.length - 1]) + "%");
        System.out.println("   Worst single-day return: " + round(sortedReturns[0]) + "%");
        System.out.println();

        // ---- 7. Binary search for a specific day's price ----
        int queryDay = 10;
        double priceOnDay = binarySearchPriceByDay(days, closingPrices, queryDay);
        System.out.println("7) Binary Search -> Price on Day " + queryDay + ": " + round(priceOnDay));
        System.out.println();

        // ---- 8. Greedy max profit ----
        double maxProfit = maxProfitSingleTransaction(closingPrices);
        System.out.println("8) Greedy Algorithm -> Max profit (single buy/sell): " + round(maxProfit));
        System.out.println();

        // ---- 9. Linear regression forecast ----
        int daysAhead = 5;
        double[] forecast = forecastFutureDays(closingPrices, daysAhead);
        System.out.println("9) Linear Regression Forecast for next " + daysAhead + " days:");
        for (int i = 0; i < forecast.length; i++) {
            System.out.printf("   Day %2d -> Predicted Price: %.2f%n",
                    closingPrices.length + i + 1, forecast[i]);
        }
        System.out.println();

        System.out.println("=====================================================");
        System.out.println(" END OF DEMONSTRATION");
        System.out.println("=====================================================");
    }

    // ---- helper: round a double to 2 decimal places ----
    static double round(double value) {
        return Math.round(value * 100.0) / 100.0;
    }

    static double[] roundArray(double[] arr) {
        double[] rounded = new double[arr.length];
        for (int i = 0; i < arr.length; i++) rounded[i] = round(arr[i]);
        return rounded;
    }
}
