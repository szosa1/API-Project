package zad1;

class WeatherMain {
    double temp;
    double pressure;
    double humidity;

    private double toC(){
        String formattedTemp = String.format("%.2f", temp - 273.15);
        formattedTemp = formattedTemp.replace(",", ".");
        return Double.parseDouble(formattedTemp);
    }
    @Override
    public String toString() {
        return "Temperatura: " + toC() +
                " \nCiśnienie: " + pressure +
                " \nWilgotność: " + humidity;
    }
}
