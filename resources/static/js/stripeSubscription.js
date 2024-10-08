document.addEventListener("DOMContentLoaded", function() {
    const stripe = Stripe('pk_test_51P1UH6LaJzLnoVtQKi5JED0TWxkRTWbwpQLEoyp6A0CdtIKYGltu8wmGHgvR0tKaVcPuioP7mM9Blo14M0EgC17A006gyPAOlr');
    const sessionId = subscriptionSessionId;

    if (sessionId) {
        stripe.redirectToCheckout({ sessionId: sessionId }).then(function (result) {
            // エラーハンドリング
            if (result.error) {
                console.error(result.error.message);
            }
        });
    }
});