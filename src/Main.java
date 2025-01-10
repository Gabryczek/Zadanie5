import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        System.out.print("Podaj pierwsze 3 cyfry numeru konta: ");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        try {
            String inputDigits = reader.readLine();

            if (inputDigits.length() != 3 || !inputDigits.matches("\\d{3}")) {
                System.out.println("Podano nieprawidłowe dane! Musisz wprowadzić dokładnie 3 cyfry.");
                return;
            }

            String urlString = "https://ewib.nbp.pl/plewibnra?dokNazwa=plewibnra.txt";
            URL url = new URL(urlString);

            try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(url.openStream()))) {
                String line;
                boolean found = false;

                while ((line = fileReader.readLine()) != null) {
                    if (line.startsWith(inputDigits)) {
                        String bankCode = line.substring(0, 3);
                        String bankName = line.substring(4).trim();
                        System.out.println("Numer banku: " + bankCode);
                        System.out.println("Nazwa banku: " + bankName);
                        found = true;
                        break;
                    }
                }

                if (!found) {
                    System.out.println("Nie znaleziono banku dla podanych trzech cyfr.");
                }

            } catch (IOException e) {
                System.out.println("Błąd podczas pobierania pliku: " + e.getMessage());
            }

        } catch (IOException e) {
            System.out.println("Błąd podczas wczytywania danych wejściowych: " + e.getMessage());
        }
    }
}
