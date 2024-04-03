import java.util.*;

class House {
    private double price;
    private double squareFootage;

    public House(double price, double squareFootage) {
        this.price = price;
        this.squareFootage = squareFootage;
    }

    public double getPrice() {
        return price;
    }

    public double getSquareFootage() {
        return squareFootage;
    }
}

public class HousingAnalyzer {
    public static void main(String[] args) {
      
        List<House> houses = Arrays.asList(
            new House(150000.0, 1200.0),
            new House(220000.0, 1500.0),
            new House(180000.0, 1300.0),
            new House(250000.0, 1700.0),
            new House(190000.0, 1350.0),
            new House(280000.0, 1800.0),
            new House(210000.0, 1550.0)
        );

        double[] priceRanges = { 100000, 200000, 300000, 400000, 500000, Double.MAX_VALUE };

        Map<String, Integer> houseCountByRange = new LinkedHashMap<>();
        Map<String, Double> totalSquareFootageByRange = new LinkedHashMap<>();

        for (int i = 0; i < priceRanges.length - 1; i++) {
            houseCountByRange.put(String.format("$%.0f-%.0f", priceRanges[i], priceRanges[i + 1]), 0);
            totalSquareFootageByRange.put(String.format("$%.0f-%.0f", priceRanges[i], priceRanges[i + 1]), 0.0);
        }

        for (House house : houses) {
            double price = house.getPrice();
            double squareFootage = house.getSquareFootage();
            for (int i = 0; i < priceRanges.length - 1; i++) {
                if (price >= priceRanges[i] && price < priceRanges[i + 1]) {
                    int count = houseCountByRange.get(String.format("$%.0f-%.0f", priceRanges[i], priceRanges[i + 1]));
                    houseCountByRange.put(String.format("$%.0f-%.0f", priceRanges[i], priceRanges[i + 1]), count + 1);

                    double totalSquareFootage = totalSquareFootageByRange.get(String.format("$%.0f-%.0f", priceRanges[i], priceRanges[i + 1]));
                    totalSquareFootageByRange.put(String.format("$%.0f-%.0f", priceRanges[i], priceRanges[i + 1]), totalSquareFootage + squareFootage);
                    break;
                }
            }
        }
      
        System.out.println("Price Range       | Houses Sold | Avg Square Footage");
        System.out.println("---------------------------------------------");
        for (Map.Entry<String, Integer> entry : houseCountByRange.entrySet()) {
            String range = entry.getKey();
            int count = entry.getValue();
            double totalSquareFootage = totalSquareFootageByRange.get(range);
            double avgSquareFootage = count == 0 ? 0 : totalSquareFootage / count;
            System.out.printf("%-18s | %-11d | %.2f\n", range, count, avgSquareFootage);
        }
    }
}
