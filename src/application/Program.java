package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.naming.LimitExceededException;

import entities.Sale;

public class Program {

	public static void main(String[] args) {
		Locale.setDefault(Locale.US);

		Scanner sc = new Scanner(System.in);

		String path = "C:/Temp/in.txt";

		try (BufferedReader br = new BufferedReader(new FileReader(path))) {

			List<Sale> list = new ArrayList<>();
			String line = br.readLine();
			while (line != null) {
				String[] fields = line.split(",");
				list.add(new Sale(Integer.parseInt(fields[0]), Integer.parseInt(fields[1]), fields[2],
						Integer.parseInt(fields[3]), Double.parseDouble(fields[4])));
				line = br.readLine();
			}

			Map<String, Double> unique = new HashMap<String, Double>();

			for (Sale sale : list) {
				unique.put(sale.getSeller(), 0.0);

			}

			for (String seller : unique.keySet()) {
				double total = list.stream().filter(s -> s.getSeller().equals(seller)).map(s -> s.getTotal())
						.reduce(0.0, (x, y) -> x + y);
				unique.put(seller, total);
			}

			for (String seller : unique.keySet()) {
				System.out.printf(seller + " - R$ %.2f%n", unique.get(seller));
			}

		} catch (IOException e) {
			System.out.println("Error " + e.getMessage());

		}
		sc.close();

	}
}
