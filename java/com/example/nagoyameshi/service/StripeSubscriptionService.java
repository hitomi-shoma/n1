package com.example.nagoyameshi.service;

 import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.example.nagoyameshi.entity.User;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Event;
import com.stripe.model.StripeObject;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import com.stripe.param.checkout.SessionRetrieveParams;

import jakarta.servlet.http.HttpServletRequest;
 
 @Service
public class StripeSubscriptionService {
	 @Value("${stripe.api-key}")
     private String stripeApiKey;

	 private final UserService userService;
     
     public StripeSubscriptionService(UserService userService) {
         this.userService = userService;
     }  
   
//     public String createStripeSubscriptionSession(HttpServletRequest httpServletRequest,User user) {
//    	    Stripe.apiKey = stripeApiKey;
//    	    String requestUrl = new String(httpServletRequest.getRequestURL());
//
//    	    SessionCreateParams params = SessionCreateParams.builder()
//    	        .addPaymentMethodType(SessionCreateParams.PaymentMethodType.CARD)
//    	        .addLineItem(
//    	            SessionCreateParams.LineItem.builder()
//    	                .setPriceData(
//    	                    SessionCreateParams.LineItem.PriceData.builder()
//    	                        .setCurrency("jpy")
//    	                        .setUnitAmount(300L) // 月額300円
//    	                        .setRecurring(
//    	                            SessionCreateParams.LineItem.PriceData.Recurring.builder()
//    	                                .setInterval(SessionCreateParams.LineItem.PriceData.Recurring.Interval.MONTH)
//    	                                .build()
//    	                        )
//    	                        .setProductData(
//    	                            SessionCreateParams.LineItem.PriceData.ProductData.builder()
//    	                                .setName("月額有料プラン")
//    	                                .build()
//    	                        )
//    	                        .build()
//    	                )
//    	                .setQuantity(1L)
//    	                .build()
//    	        )
//    	        .setMode(SessionCreateParams.Mode.SUBSCRIPTION)
//    	        .setSuccessUrl(requestUrl.replaceAll("/subscription/register", "") + "/login?reserved")
//    	        .setCancelUrl(requestUrl.replace("/subscription/delete", ""))
//    	        .build();
//
//    	    try {
//    	        Session session = Session.create(params);
//    	        return session.getId();
//    	    } catch (StripeException e) {
//    	        e.printStackTrace();
//    	        return "";
//    	    }
//    	}
//   ↑最新版訂正
     
     public String createStripeSubscriptionSession(HttpServletRequest request, User user) {
    	    Stripe.apiKey = stripeApiKey;

    	    SessionCreateParams params = SessionCreateParams.builder()
    	        .addPaymentMethodType(SessionCreateParams.PaymentMethodType.CARD)
    	        .addLineItem(
    	            SessionCreateParams.LineItem.builder()
    	                .setPriceData(
    	                    SessionCreateParams.LineItem.PriceData.builder()
    	                        .setCurrency("jpy")
    	                        .setUnitAmount(300L)
    	                        .setRecurring(
    	                            SessionCreateParams.LineItem.PriceData.Recurring.builder()
    	                                .setInterval(SessionCreateParams.LineItem.PriceData.Recurring.Interval.MONTH)
    	                                .build()
    	                        )
    	                        .setProductData(
    	                            SessionCreateParams.LineItem.PriceData.ProductData.builder()
    	                                .setName("月額有料プラン")
    	                                .build()
    	                        )
    	                        .build()
    	                )
    	                .setQuantity(1L)
    	                .build()
    	        )
    	        .setMode(SessionCreateParams.Mode.SUBSCRIPTION)
    	        .setSuccessUrl(request.getRequestURL().toString().replaceAll("/subscription/register", "") + "/subscription/success")
    	        .setCancelUrl(request.getRequestURL().toString().replaceAll("/subscription/register", "") + "/subscription/cancel")
    	        .build();

    	    try {
    	        Session session = Session.create(params);
    	        return session.getId();
    	    } catch (StripeException e) {
    	        e.printStackTrace();
    	        return null;
    	    }
    	}


     
//     public String createStripeSubscriptionSession(HttpServletRequest httpServletRequest,User user) {
//    	 String userId = (user != null && user.getId() != null) ? String.valueOf(user.getId()) : "null";
//    	 Stripe.apiKey = stripeApiKey;
//         String requestUrl = new String(httpServletRequest.getRequestURL());
//
// 
//         SessionCreateParams params = SessionCreateParams.builder()
//                 
//                 .setMode(SessionCreateParams.Mode.SUBSCRIPTION)
//                 .setSuccessUrl(requestUrl.replaceAll("/subscription/register", "") + "/login?reserved")
//                 .addLineItem(
//           		      SessionCreateParams.LineItem.builder()
//           		        .setPrice("300")
//           		        .setQuantity(1L)
//           		        .build()
//           		    )
//                 .putMetadata("userId", userId)
//                 .putMetadata("roleName", "ROLE_VIP")
//           		 
//           		    .build();
//         try {
//             Session session = Session.create(params);
//             return session.getId();
//         } catch (StripeException e) {
//             e.printStackTrace();
//             return "";
//         }
//     public String createStripeSubscriptionSession(HttpServletRequest httpServletRequest, User user) {
//    	    String userId = (user != null && user.getId() != null) ? String.valueOf(user.getId()) : "null";
//    	    Stripe.apiKey = stripeApiKey;
//    	    String requestUrl = new String(httpServletRequest.getRequestURL());
//
//    	    // プライスIDを使用してSessionを作成
//    	    SessionCreateParams params = SessionCreateParams.builder()
//    	    		.addPaymentMethodType(SessionCreateParams.PaymentMethodType.CARD)
//    	            .setMode(SessionCreateParams.Mode.SUBSCRIPTION)
//    	            .setSuccessUrl(requestUrl.replaceAll("/subscription/register", "") + "/login?reserved")
//    	            .addLineItem(SessionCreateParams.LineItem.builder()
//    	                    .setPrice("price_1Pac8eLaJzLnoVtQeqhfROIs") // プライスIDを設定
//    	                    .setQuantity(1L)
//    	                    .build())
//    	            .putMetadata("userId", userId)
//    	            .putMetadata("roleName", "ROLE_VIP")
//    	            .build();
//
//    	    try {
//    	        Session session = Session.create(params);
//    	        return session.getId();
//    	    } catch (StripeException e) {
//    	        e.printStackTrace();
//    	        return "";
//    	    }
//     } 
     public void processSubscriptionSessionCompleted(Event event) {
    	    Optional<StripeObject> optionalStripeObject = event.getDataObjectDeserializer().getObject();
    	    optionalStripeObject.ifPresent(stripeObject -> {
    	     
    	Session session = (Session) stripeObject;

    	        

    	 
    	if (session.getPaymentIntent() != null) {
    	            // Payment intent exists, proceed with existing logic
    	            
    	            
    	SessionRetrieveParams params = SessionRetrieveParams.builder().addExpand("payment_intent").build();

    	            try {
    	                session = Session.retrieve(session.getId(), params, null);
    	                Map<String, String> paymentIntentObject = session.getPaymentIntentObject().getMetadata();
    	                userService.updateRole(paymentIntentObject);
    	            } 
    	catch (StripeException e) {
    	                e.printStackTrace();
    	            }
    	        } 
    	               
    	else {
    	            // Payment intent is null, directly use metadata from the session
    	            Map<String, String> metadata = session.getMetadata();
    	            userService.updateRole(metadata);
    	        }
    	    });
    	}
 }
     