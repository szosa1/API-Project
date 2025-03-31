/**
 *
 *  @author Olszewski  Szymon S30524
 *
 */

package zad1;

import com.google.gson.Gson;

public class Main {
  public static void main(String[] args) {
    Service s = new Service("Poland");
    String weatherJson = s.getWeather("Warsaw");
    Double rate1 = s.getRateFor("USD");
    Double rate2 = s.getNBPRate();
    // ...
    // część uruchamiająca GUI

    Weather weather = new Gson().fromJson(weatherJson, Weather.class);
    UI ui = new UI(s.miasto, s.kraj, weather, rate1,rate2);
  }
}
