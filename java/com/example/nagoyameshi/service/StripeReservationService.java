package com.example.nagoyameshi.service;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.example.nagoyameshi.form.ReservationRegisterForm;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Event;
import com.stripe.model.StripeObject;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import com.stripe.param.checkout.SessionRetrieveParams;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class StripeReservationService {
    @Value("${stripe.api-key}")
    private String stripeApiKey;
    
    private final ReservationService reservationService;
    
    public StripeReservationService(ReservationService reservationService) {
        this.reservationService = reservationService;
    }    
    
    // セッションを作成し、Stripeに必要な情報を返す
    public String createStripeSession(String shopName, ReservationRegisterForm reservationRegisterForm, HttpServletRequest httpServletRequest) {
        Stripe.apiKey = stripeApiKey;
        String requestUrl = new String(httpServletRequest.getRequestURL());
        SessionCreateParams params =
            SessionCreateParams.builder()
                .addPaymentMethodType(SessionCreateParams.PaymentMethodType.CARD)
                .addLineItem(
                    SessionCreateParams.LineItem.builder()
                        .setPriceData(
                            SessionCreateParams.LineItem.PriceData.builder()   
                                .setProductData(
                                    SessionCreateParams.LineItem.PriceData.ProductData.builder()
                                        .setName(shopName)
                                        .build())
                                .setUnitAmount((long)reservationRegisterForm.getAmount())
                                .setCurrency("jpy")                                
                                .build())
                        .setQuantity(1L)
                        .build())
                .setMode(SessionCreateParams.Mode.PAYMENT)
                .setSuccessUrl(requestUrl.replaceAll("/shops/[0-9]+/reservations/confirm", "") + "/reservations?reserved")
                .setCancelUrl(requestUrl.replace("/reservations/confirm", ""))
                .setPaymentIntentData(
                    SessionCreateParams.PaymentIntentData.builder()
                        .putMetadata("shopId", reservationRegisterForm.getShopId().toString())
                        .putMetadata("userId", reservationRegisterForm.getUserId().toString())
                        .putMetadata("reservationDateTime", reservationRegisterForm.getReservationDateTime().toString())
                        .putMetadata("numberOfPeople", reservationRegisterForm.getNumberOfPeople().toString())
                        .putMetadata("amount", reservationRegisterForm.getAmount().toString())
                        .build())
                .build();
        try {
            Session session = Session.create(params);
            return session.getId();
        } catch (StripeException e) {
            e.printStackTrace();
            return "";
        }
    } 
    
    // セッションから予約情報を取得し、ReservationServiceクラスを介してデータベースに登録する
    public void processSessionCompleted(Event event) {
        Optional<StripeObject> optionalStripeObject = event.getDataObjectDeserializer().getObject();
        optionalStripeObject.ifPresent(stripeObject -> {
            Session session = (Session)stripeObject;
            SessionRetrieveParams params = SessionRetrieveParams.builder().addExpand("payment_intent").build();

            try {
                session = Session.retrieve(session.getId(), params, null);
                Map<String, String> paymentIntentObject = session.getPaymentIntentObject().getMetadata();
                reservationService.create(paymentIntentObject);
            } catch (StripeException e) {
                e.printStackTrace();
            }
        });
    }    
}

//AI先生回答
//@Service
//public class StripeReservationService {
//    @Value("${stripe.api-key}")
//    private String stripeApiKey;
//    
//    private final ReservationService reservationService;
//    
//    public StripeReservationService(ReservationService reservationService) {
//        this.reservationService = reservationService;
//    }    
//    public String createStripeSession(String shopName, ReservationRegisterForm reservationRegisterForm, HttpServletRequest httpServletRequest) {
//    Stripe.apiKey = stripeApiKey;
//    String requestUrl = new String(httpServletRequest.getRequestURL());
//
//    SessionCreateParams params = SessionCreateParams.builder()
//        .addPaymentMethodType(SessionCreateParams.PaymentMethodType.CARD)
//        .addLineItem(
//            SessionCreateParams.LineItem.builder()
//                .setPriceData(
//                    SessionCreateParams.LineItem.PriceData.builder()
//                        .setCurrency("jpy")
//                        .setUnitAmount(300L) // 月額300円
//                        .setRecurring(
//                            SessionCreateParams.LineItem.PriceData.Recurring.builder()
//                                .setInterval(SessionCreateParams.LineItem.PriceData.Recurring.Interval.MONTH)
//                                .build()
//                        )
//                        .setProductData(
//                            SessionCreateParams.LineItem.PriceData.ProductData.builder()
//                                .setName("月額有料プラン")
//                                .build()
//                        )
//                        .build()
//                )
//                .setQuantity(1L)
//                .build()
//        )
//        .setMode(SessionCreateParams.Mode.SUBSCRIPTION)
//        .setSuccessUrl(requestUrl.replaceAll("/subscription/create", "") + "/subscription?subscribed")
//        .setCancelUrl(requestUrl.replace("/subscription/create", ""))
//        .build();
//
//    try {
//        Session session = Session.create(params);
//        return session.getId();
//    } catch (StripeException e) {
//        e.printStackTrace();
//        return "";
//    }
//}