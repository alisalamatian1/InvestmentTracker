package persistence;

import model.*;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

// A class for reading the JSON file
// citation: this is the similar code that can be found on the repository: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
public class JsonReading {
    private final String source;
    private JSONObject json;

    // EFFECTS: constructs reader to read from source file
    public JsonReading(String source) {
        this.source = source;
    }

    // EFFECTS: reads UserProfileAndWallet from file and returns it;
    // throws IOException if an error occurs reading data from file
    public UserProfileAndWallet read() throws IOException {
        String jsonData = readFile(source);
        json = new JSONObject(jsonData);
        return parseUserProfileAndWallet();
    }


    //EFFECTS: returning a string that represent the save data
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();
        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }
        return contentBuilder.toString();
    }

    // EFFECTS: parses userProfileAndWallet by taking out profile and stocksInWallet and making an object
    private UserProfileAndWallet parseUserProfileAndWallet() {
        String password = matchFinder("password\":\"([0-9]*)");
        String username = matchFinder("username\":\"([a-zA-Z]*)");
        String profitString = matchFinder("profit\":(-?[0-9]*)");
        double profit = Double.parseDouble(profitString);
        ArrayList<String> tickers = matchFinderArray("ticker\":\"([a-zA-Z]*)");
        ArrayList<String> numbers = matchFinderArray("number\":([0-9]+)");
        ArrayList<String> prices = matchFinderArray("price\":([0-9]+)");

        UserProfile profile = new UserProfile(username, password);
        StocksInWallet stocksInWallet = new StocksInWallet();
        for (int i = 0; i < tickers.size(); i++) {
            stocksInWallet.addPurchasedStock(new PurchasedStock(new Stock(tickers.get(i)),
                    Integer.parseInt(numbers.get(i)), Double.parseDouble(prices.get(i))));
        }
        UserProfileAndWallet userProfileAndWallet = new UserProfileAndWallet(profile, stocksInWallet);
        userProfileAndWallet.setProfit(profit);
        return userProfileAndWallet;
    }

    // REQUIRES: regex must have at least one group
    // EFFECTS: searching for the given pattern and returning the wanted string based on that
    public String matchFinder(String regex) {
        String key = "userProfileAndWallets";
        String value = "";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(json.get(key).toString());
        if (matcher.find()) {
            value = matcher.group(1);
        }
        return value;
    }

    // REQUIRES: regex must have at least one group
    // EFFECTS: searching for the given pattern and returning the wanted string based on that
    private ArrayList<String> matchFinderArray(String regex) {
        String key = "userProfileAndWallets";
        ArrayList<String> values = new ArrayList<>();
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(json.get(key).toString());
        while (matcher.find()) {
            values.add(matcher.group(1));
        }
        return values;
    }
}
