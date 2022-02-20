package persistence;

import model.StocksInWallet;
import model.UserProfile;
import model.UserProfileAndWallet;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;


public class JsonReading {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReading(String source) {
        this.source = source;
    }

    // EFFECTS: reads UserProfileAndWallet from file and returns it;
    // throws IOException if an error occurs reading data from file
    public UserProfileAndWallet read() throws IOException {
        String jsonData = readFile(source);
        JSONObject json = new JSONObject(jsonData);
        return parseUserProfileAndWallet(json);
    }


    //EFFECTS: returning a string that represent the save data
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    private UserProfileAndWallet parseUserProfileAndWallet(JSONObject json) {
        String key = "userProfileAndWallets";
        String stringfiedJson = json.toString();
        System.out.println(stringfiedJson);
        System.out.println(json.keySet().toString());
        System.out.println(json.get(key));
        String[] array = json.get(key).toString().split(",");
        String number = array[0].split("number\":")[1];
        String price = array[1].split(":")[1];



        Pattern pattern = Pattern.compile("ticker\":\"([a-zA-Z]*)");
        Matcher matcher = pattern.matcher(json.get(key).toString());
        if (matcher.find()) {
            String valueTicker = matcher.group(1);
            System.out.println("---------------------------------++++++++++");
            System.out.println(valueTicker + "  value");
        }
        Pattern patternPass = Pattern.compile("password\":\"([1-9]*)");
        Matcher matcherPass = patternPass.matcher(json.get(key).toString());
        if (matcherPass.find()) {
            String valuePass = matcherPass.group(1);
            System.out.println("---------------------------------++++++++++");
            System.out.println(valuePass + "  value");
        }
        Pattern patternUsername = Pattern.compile("username\":\"([a-zA-Z]*)");
        Matcher matcherUsername = patternUsername.matcher(json.get(key).toString());
        if (matcherUsername.find()) {
            String valueUsername = matcherUsername.group(1);
            System.out.println("---------------------------------++++++++++");
            System.out.println(valueUsername + "  value");
        }


        for (String s : array)  {
            if (s.contains("ticker\":")) {
                String[] stringTicker = s.split("ticker\":");
                String ticker = stringTicker[1].split("}")[0];
                System.out.println("---------------------------------");
                System.out.println(ticker);
            }
            if (s.contains("password\":")) {
                String[] stringPass = s.split("password\":");
                String pass = stringPass[1].split(",")[0];
                System.out.println("---------------------------------");
                System.out.println(pass);
            }
            if (s.contains("username\":")) {
                String[] stringName = s.split("username\":");
                String userName = stringName[1].split("}")[0];
                System.out.println("---------------------------------");
                System.out.println(userName);
            }
            System.out.println("--------------");
            System.out.println(s);
        }

        return new UserProfileAndWallet(new UserProfile("ali", "123"), new StocksInWallet());
    }


}
