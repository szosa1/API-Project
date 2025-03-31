/**
 *
 *  @author Olszewski  Szymon S30524
 *
 */

package zad1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.json.JSONArray;
import org.json.JSONObject;

public class Service {

    public String kraj;
    public String miasto;
    private final String weatherAPI = "a4bd102efe0c965b9f82e29669da6410";
    private final String exchangeAPI = "1de286dd6d17d4b35c5fd1ed";
    private final String currencyAPI = "https://restcountries.com/v3.1/name/";

    public Service(String kraj) {
        this.kraj = kraj;
    }

    public String getWeather(String miasto) {
        this.miasto = miasto;
        String url = String.format("https://api.openweathermap.org/data/2.5/weather?q=%s,%s&appid=%s", miasto, getCountryCode(kraj), weatherAPI);
        return getInfo(url);
    }

    public Double getRateFor(String kodWaluty) {
        String countryCurrency = getCurrencyCode(kraj);
        String url = String.format("https://open.er-api.com/v6/latest/%s?apikey=%s", kodWaluty, exchangeAPI);
        String info = getInfo(url);

        JSONObject jsonObject = new JSONObject(info);

        return jsonObject.getJSONObject("rates").getDouble(countryCurrency);
    }

    public Double getNBPRate() {
        String countryCurrency = getCurrencyCode(kraj);
        String url = "https://api.nbp.pl/api/exchangerates/tables/A?format=json";
        String info = getInfo(url);

        try {
            JSONArray jsonArray = new JSONArray(info);
            JSONObject table = jsonArray.getJSONObject(0);
            JSONArray NBPrates = table.getJSONArray("rates");

            for (int i = 0; i < NBPrates.length(); i++) {
                JSONObject rate = NBPrates.getJSONObject(i);
                if (rate.getString("code").equals(countryCurrency)) {
                    return rate.getDouble("mid");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 1.0;
    }

    private String getCurrencyCode(String country) {
        try {
            String json = new String(Files.readAllBytes(Paths.get("codes.json")));
            JSONObject jsonObject = new JSONObject(json);
            return jsonObject.getString(country.toLowerCase());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


    

    private String getInfo(String urlString) {
        try {
            java.net.URL url = new java.net.URL(urlString);
            java.net.HttpURLConnection connection = (java.net.HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();

            java.util.Scanner scanner = new java.util.Scanner(url.openStream());
            StringBuilder stringBuilder = new StringBuilder();

            while (scanner.hasNext()) {
                stringBuilder.append(scanner.nextLine());
            }

            scanner.close();
            return stringBuilder.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private String getCountryCode(String countryName) {
        switch (countryName.toLowerCase()) {
            case "italy": return "IT";
            case "poland": return "PL";
            case "germany": return "DE";
            case "united states": return "US";
            case "france": return "FR";
            default: return countryName;
        }
    }
}


