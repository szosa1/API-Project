package zad1;

import javafx.embed.swing.JFXPanel;

import javax.swing.*;
import java.awt.*;

public class UI extends JFrame {

    private final JFXPanel jfxPanel;
    private final JLabel cityLabel, countryLabel, weatherLabel, currencyLabel, nbpRateLabel;

    public UI(String city, String country, Weather weather, double currency, double nbpRate) {
        this.setTitle("Weather and Currency");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800, 600);
        this.setLayout(new BorderLayout());

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        countryLabel = new JLabel("Kraj: " + country);
        cityLabel = new JLabel("Miasto: " + city);
        weatherLabel = new JLabel("Pogoda: " + weather);
        currencyLabel = new JLabel("Kurs waluty kraju wobec waluty podanej: " + currency);
        nbpRateLabel = new JLabel("Kurs NBP złotego wobec waluty: " + nbpRate);

        cityLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        countryLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        weatherLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        currencyLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        nbpRateLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        mainPanel.add(cityLabel);
        mainPanel.add(countryLabel);
        mainPanel.add(weatherLabel);
        mainPanel.add(currencyLabel);
        mainPanel.add(nbpRateLabel);

        JPanel wikiPanel = new JPanel(new BorderLayout());
        jfxPanel = new JFXPanel();
        wikiPanel.add(jfxPanel, BorderLayout.CENTER);

        this.add(mainPanel, BorderLayout.NORTH);
        this.add(wikiPanel, BorderLayout.CENTER);

        this.setVisible(true);
    }
}