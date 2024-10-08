package com.example.nagoyameshi.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import com.example.nagoyameshi.service.StripeReservationService;
import com.example.nagoyameshi.service.StripeWebhookService;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Event;

@Controller
public class StripeWebhookController {
    private final StripeReservationService stripeReservationService;
    private final StripeWebhookService stripeWebhookService;

    @Value("${stripe.api-key}")
    private String stripeApiKey;

    @Value("${stripe.webhook-secret}")
    private String webhookSecret;

    public StripeWebhookController(StripeReservationService stripeReservationService, StripeWebhookService stripeWebhookService) {
        this.stripeReservationService = stripeReservationService;
        this.stripeWebhookService = stripeWebhookService;
    }

    @PostMapping("/stripe/webhook")
    public ResponseEntity<String> webhook(@RequestBody String payload, @RequestHeader("Stripe-Signature") String sigHeader) {
        Stripe.apiKey = stripeApiKey;
        Event event = null;

        try {
            event = stripeWebhookService.constructEvent(payload, sigHeader, webhookSecret);
        } catch (StripeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        if ("checkout.session.completed".equals(event.getType())) {
            stripeReservationService.processSessionCompleted(event);
        }

        return new ResponseEntity<>("Success", HttpStatus.OK);
    }
}


//@Controller
//public class StripeWebhookController {
//	private final StripeReservationService stripeReservationService;
//	
//	@Value("${stripe.api-key}")
//    private String stripeApiKey;
//	
//	@Value("${stripe.webhook-secret}")
//    private String webhookSecret;
//	
//	public StripeWebhookController(StripeReservationService stripeReservationService) {
//		this.stripeReservationService = stripeReservationService;
//	}
//
//	@PostMapping("/stripe/webhook")
//	public ResponseEntity<String> webhook(@RequestBody String payload, @RequestHeader("Stripe-Signature") String sigHeader) {
//		Stripe.apiKey = stripeApiKey;
//        Event event = null;
//        
//        try {
//        	event = Webhook.constructEvent(payload, sigHeader, webhookSecret);
//        } catch (SignatureVerificationException e) {
//        	return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
//        }
//        if ("checkout.session.completed".equals(event.getType())) {
//        	stripeReservationService.processSessionCompleted(event);
//        }
//        
//        return new ResponseEntity<>("Success", HttpStatus.OK);
//	}
//}