package com.okwatch.flightreservation.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class DarajaService {

    @Value("${daraja.consumerKey}")
    private String consumerKey;

    @Value("${daraja.consumerSecret}")
    private String consumerSecret;

    @Value("${daraja.shortCode}")
    private String shortCode;

    @Value("${daraja.passKey}")
    private String passKey;

    @Value("${daraja.callbackUrl}")
    private String callbackUrl;

    private final RestTemplate restTemplate = new RestTemplate();

    public String getAccessToken() {
        try {
            String authUrl = "https://sandbox.safaricom.co.ke/oauth/v1/generate?grant_type=client_credentials";
            String credentials = consumerKey + ":" + consumerSecret;
            String encoded = Base64.getEncoder().encodeToString(credentials.getBytes());

            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Basic " + encoded);
            headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);

            HttpEntity<String> request = new HttpEntity<>(headers);

            ResponseEntity<Map> response = restTemplate.exchange(authUrl, HttpMethod.GET, request, Map.class);

            if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
                String token = (String) response.getBody().get("access_token");
                System.out.println("✅ Valid Access Token: " + token);
                return token;
            } else {
                System.err.println("⚠️ Token response issue: " + response);
                return null;
            }

        } catch (Exception e) {
            System.err.println("❌ Error fetching access token: " + e.getMessage());
            return null;
        }
    }

    public Map<String, Object> initiateStkPush(String phone, String amount) throws Exception {
        String accessToken = getAccessToken();
        if (accessToken == null) throw new Exception("Access token is null");

        String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        String password = Base64.getEncoder().encodeToString((shortCode + passKey + timestamp).getBytes());

        Map<String, Object> body = new HashMap<>();
        body.put("BusinessShortCode", shortCode);
        body.put("Password", password);
        body.put("Timestamp", timestamp);
        body.put("TransactionType", "CustomerPayBillOnline");
        body.put("Amount", amount);
        body.put("PartyA", phone);
        body.put("PartyB", shortCode);
        body.put("PhoneNumber", phone);
        body.put("CallBackURL", callbackUrl);
        body.put("AccountReference", "FlightBooking");
        body.put("TransactionDesc", "Pay for flight");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(accessToken);

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(body, headers);

        ResponseEntity<Map> response = restTemplate.postForEntity(
                "https://sandbox.safaricom.co.ke/mpesa/stkpush/v1/processrequest",
                request,
                Map.class
        );

        System.out.println("📤 STK Push initiated. Response:");
        System.out.println(response.getBody());

        return response.getBody();
    }
}
