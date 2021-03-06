package com.vojtkovszky.billinghelper

import com.android.billingclient.api.BillingClient

enum class BillingEvent {
    /**
     * Success when calling [BillingClient.startConnection]
     */
    BILLING_CONNECTED,

    /**
     * Failure when calling [BillingClient.startConnection]
     */
    BILLING_CONNECTION_FAILED,

    /**
     * Called when [BillingClient] disconnected
     */
    BILLING_DISCONNECTED,

    /**
     * Success from [BillingClient.querySkuDetailsAsync]
     */
    QUERY_SKU_DETAILS_COMPLETE,

    /**
     * Failure from [BillingClient.querySkuDetailsAsync]
     */
    QUERY_SKU_DETAILS_FAILED,

    /**
     * Success from [BillingClient.queryPurchases]
     */
    QUERY_OWNED_PURCHASES_COMPLETE,

    /**
     * Failure from [BillingClient.queryPurchases]
     */
    QUERY_OWNED_PURCHASES_FAILED,

    /**
     * Success from [BillingClient.launchBillingFlow]
     */
    PURCHASE_COMPLETE,

    /**
     * Failure from [BillingClient.launchBillingFlow]
     */
    PURCHASE_FAILED,

    /**
     * User cancelled [BillingClient.launchBillingFlow]
     */
    PURCHASE_CANCELLED,

    /**
     * Success from [BillingClient.acknowledgePurchase]
     */
    PURCHASE_ACKNOWLEDGED,

    /**
     * Failure from [BillingClient.acknowledgePurchase]
     */
    PURCHASE_ACKNOWLEDGE_FAILED,

    /**
     * Success from [BillingClient.consumeAsync]
     */
    CONSUME_PURCHASE_SUCCESS,

    /**
     * Failure from [BillingClient.consumeAsync]
     */
    CONSUME_PURCHASE_FAILED,

    /**
     * Success from [BillingClient.launchPriceChangeConfirmationFlow]
     */
    PRICE_CHANGE_CONFIRMATION_SUCCESS,

    /**
     * User cancelled [BillingClient.launchPriceChangeConfirmationFlow]
     */
    PRICE_CHANGE_CONFIRMATION_CANCELLED;


    /**
     * Is event a failure by nature.
     */
    val isFailure: Boolean
        get() = listOf(
            BILLING_CONNECTION_FAILED, QUERY_SKU_DETAILS_FAILED, QUERY_OWNED_PURCHASES_FAILED,
            PURCHASE_FAILED, CONSUME_PURCHASE_FAILED
        ).contains(this)

    /**
     * Is event a failure due to an actively initialized flow.
     */
    val isActiveActionFailure: Boolean
        get() = listOf(
            QUERY_SKU_DETAILS_FAILED, PURCHASE_FAILED, CONSUME_PURCHASE_FAILED
        ).contains(this)

    /**
     * Is event a success by nature.
     */
    val isSuccess: Boolean
        get() = listOf(
            BILLING_CONNECTED, QUERY_SKU_DETAILS_COMPLETE, QUERY_OWNED_PURCHASES_COMPLETE,
            PURCHASE_COMPLETE, PURCHASE_ACKNOWLEDGED, CONSUME_PURCHASE_SUCCESS,
            PRICE_CHANGE_CONFIRMATION_SUCCESS
        ).contains(this)

    /**
     * Is event a success due to an actively initialized flow.
     */
    val isActiveActionSuccess: Boolean
        get() = listOf(
            PURCHASE_COMPLETE, PRICE_CHANGE_CONFIRMATION_SUCCESS
        ).contains(this)
}