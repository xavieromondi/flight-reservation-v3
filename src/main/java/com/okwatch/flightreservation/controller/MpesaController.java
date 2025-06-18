package com.okwatch.flightreservation.controller;

import com.okwatch.flightreservation.entities.MpesaTransaction;
import com.okwatch.flightreservation.repos.MpesaTransactionRepository;
import com.okwatch.flightreservation.services.DarajaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/payment")
public class MpesaController {

    @Autowired
    private DarajaService darajaService;

    @Autowired
    private MpesaTransactionRepository transactionRepository;

    // ✅ Trigger Lipa Na M-Pesa Online and return JSON response
    @PostMapping("/pay")
    public ResponseEntity<?> initiatePayment(@RequestBody Map<String, String> request) {
        String phone = request.get("phone");
        String amount = request.get("amount");

        try {
            Map<String, Object> safaricomResponse = darajaService.initiateStkPush(phone, amount);
            System.out.println("📤 STK Push initiated with response: " + safaricomResponse);
            return ResponseEntity.ok(safaricomResponse); // return JSON to frontend
        } catch (Exception e) {
            System.err.println("❌ Error initiating STK Push: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(500).body(Map.of("error", e.getMessage()));
        }
    }

    // ✅ Handle Safaricom Callback
    @PostMapping("/callback")
    public ResponseEntity<String> handleCallback(@RequestBody Map<String, Object> payload) {
        // Respond to Safaricom immediately
        ResponseEntity<String> response = ResponseEntity.ok("Callback received");

        new Thread(() -> {
            try {
                System.out.println("📥 M-Pesa Callback Received");
                System.out.println("🔍 Full Payload: " + payload);

                Map<String, Object> body = (Map<String, Object>) payload.get("Body");
                Map<String, Object> stkCallback = (Map<String, Object>) body.get("stkCallback");

                Integer resultCode = (Integer) stkCallback.get("ResultCode");
                String resultDesc = (String) stkCallback.get("ResultDesc");
                String checkoutRequestId = (String) stkCallback.get("CheckoutRequestID");

                MpesaTransaction transaction = new MpesaTransaction(checkoutRequestId, resultCode, resultDesc);
                transactionRepository.save(transaction);

                System.out.println("💾 Saved transaction: " + transaction);
            } catch (Exception e) {
                System.err.println("❌ Callback error: " + e.getMessage());
            }
        }).start(); // async execution

        return response;
    }



    // ✅ Check payment status
    @GetMapping("/status/{checkoutRequestId}")
    public ResponseEntity<Map<String, Object>> getPaymentStatus(@PathVariable String checkoutRequestId) {
        return transactionRepository.findById(checkoutRequestId)
                .map(tx -> {
                    Map<String, Object> success = new HashMap<>();
                    success.put("resultCode", tx.getResultCode());

                    System.out.println("🔄 Status check for: " + checkoutRequestId);
                    System.out.println("📤 Returning resultCode: " + tx.getResultCode());

                    return ResponseEntity.ok(success);
                })
                .orElseGet(() -> {
                    Map<String, Object> error = new HashMap<>();
                    error.put("message", "Pending");

                    System.out.println("⌛ Status pending for: " + checkoutRequestId);
                    return ResponseEntity.status(404).body(error);
                });
    }
}
