package com.example.nagoyameshi.service;

import org.springframework.stereotype.Service;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Event;
import com.stripe.net.Webhook;

@Service
public class StripeWebhookService {
    public Event constructEvent(String payload, String sigHeader, String webhookSecret) throws StripeException {
        Stripe.apiKey = "sk_test_51P1UH6LaJzLnoVtQxRzsYZEZNfe9FkJgndFEo8hPH4IvYUxPL02TSHLzSS313w9FxWU9s4JjBBFwZsrnG5puCN5v00VEEOkDO8";
        return Webhook.constructEvent(
                payload,
                sigHeader,
                webhookSecret
        );
    }
}