package org.example.Currency;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONObject;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Scanner;

public class CurrencyConverter {
    static {
        // API endpoint to fetch the latest exchange rates for USD
        String api_url = "https://v6.exchangerate-api.com/v6/1fbd68ea9cf0434fe6b6bfde/latest/USD";
    }
    public static void main(String[] args) throws IOException {
        OkHttpClient client = new OkHttpClient(); //used to handle HTTP requests and responses in Java application.
        // Request to get the list of available currencies
        Request currenciesRequest = new Request.Builder()
                .url("https://v6.exchangerate-api.com/v6/1fbd68ea9cf0434fe6b6bfde/latest/USD")
                .get()
                .addHeader("X-RapidAPI-Key", "1fbd68ea9cf0434fe6b6bfde")
                .addHeader("X-RapidAPI-Host", "https://app.exchangerate-api.com")
                .build();

        Response currenciesResponse = client.newCall(currenciesRequest).execute();

        if (currenciesResponse.isSuccessful()) {
            String currenciesResponseBody = currenciesResponse.body().string();
            System.out.println("Available Currencies:\n" + currenciesResponseBody);
        } else {
            System.out.println("Failed to fetch currencies. Exiting.");
            System.exit(1);
        }

  //this part to take inpute and display
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter the Source currency : ");
        String convertFrom=sc.nextLine();
        System.out.println("Enter the Target Currency: ");
        String convertTo=sc.nextLine();
        System.out.println("Enter the Ammount");
        BigDecimal amount =sc.nextBigDecimal();


  //this code is fetch the pertucular currency and calculte

        Request request = new Request.Builder()
                .url("https://currency-converter-by-api-ninjas.p.rapidapi.com/v1/convertcurrency?have="+convertFrom+"&want="+convertTo+"&amount="+amount)
                .get()
                .addHeader("X-RapidAPI-Key", "21cb213efemshe5453594e89658cp16ff10jsnee6bc9feecbf")
                .addHeader("X-RapidAPI-Host", "currency-converter-by-api-ninjas.p.rapidapi.com")
                .build();

        Response response = client.newCall(request).execute();

        if(response.isSuccessful()){
            String responseBody=response.body().string();

            // Parse the JSON response and handle the data
            JSONObject jsonObject=new JSONObject(responseBody);
            double newAmmount= jsonObject.getDouble("new_amount");
            System.out.println("Converted amount= "   +newAmmount);
        }else {
            System.out.println("API failed");
        }

    }
}
