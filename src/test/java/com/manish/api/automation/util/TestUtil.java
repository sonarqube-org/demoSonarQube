package com.manish.api.automation.util;


import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestUtil {

    public static final String BASE_URL = "https://fed-gateway.uw3.azure.dev.link.launch321.naxgrp.com/";
    public static final String FEATURE_USER_ONBOARDING = "User Onboarding";
    public static final String FEATURE_PAYMENT = "Payment Service";
    public static final String FEATURE_MERCHANT = "Merchant Service";
    public static final Integer STATUS_CODE_SUCCESS = 200;
    public static final String INVALID_RANDOM_USER_ID = "0e889403-f591-48fb-bc9c-07473d791595";
    public static final String INVALID_RANDOM_ADDRESS_ID = "76bafe89-d9cb-46fd-b623-ea35103a6e3b";
    public static final String INVALID_RANDOM_CONTACT_ID = "3b276f92-8c40-45b0-981f-2d6591eb70b6";
    public static final String FEATURE_CREATE_WALLET = "Wallet Creation";
    public static final String FEATURE_GET_WALLET = "Wallet Retrieval";
    public static final String FEATURE_GET_BALANCE_BY_WALLET_ID = "Get Balance by Wallet ID";
    public static final String FEATURE_ADD_GIFT_CARD_AMOUNT = "Add Gift Card Amount on Wallet";
    public static final String FEATURE_CREATE_LEDGER_TRANSACTION = "Create Ledger Transaction";
    public static final String FEATURE_GET_WALLET_TRANSACTION_LIST = "Get Wallet Transaction List";
    public static final String FEATURE_UPDATE_ORDER_WITH_NEW_PRODUCT = "Update Order With New Product";
    public static final String FEATURE_UPDATE_ORDER_WITH_PRICE_CHANGE  = "Update Order With Price";
    public static final String FEATURE_CREATE_ORDER = "Create Order";
    public static final String FEATURE_ORDER_SERVICE = "Order Service";
    public static final String FEATURE_WALLET_SERVICE = "Wallet Service";

    public static final String FEATURE_ORDER_IN_CART = "Order In Cart";
    public static final String FEATURE_DELETE_ORDER_ONE_PRODUCT = "Delete One Product from Order";
    public static final String FEATURE_CREATE_TRANSACTION = "Create Transaction";
    public static final String GET_ALL_USERS_FOR_MERCHANT_QUERY = "GET_ALL_USERS_FOR_MERCHANT_QUERY";
    public static final String GET_ALL_USERS_BY_USERID_QUERY = "GET_ALL_USERS_BY_USERID_QUERY";
    public static final String CREATE_USER_MUTATION = "CREATE_USER_MUTATION";
    public static final String UPDATE_USER_MUTATION = "UPDATE_USER_MUTATION";
    public static final String CHECKOUT_ORDER_MUTATION = "CHECKOUT_ORDER_MUTATION";
    public static final String CREATE_USER_ADDRESS_MUTATION = "CREATE_USER_ADDRESS_MUTATION";
    public static final String UPDATE_USER_ADDRESS_MUTATION = "UPDATE_USER_ADDRESS_MUTATION";
    public static final String CREATE_CONTACT_MUTATION = "CREATE_CONTACT_MUTATION";
    public static final String UPDATE_CONTACT_MUTATION = "UPDATE_CONTACT_MUTATION";
    public static final String CREATE_USER_BY_ADDRESS_MUTATION = "CREATE_USER_BY_ADDRESS_MUTATION";
    public static final String CREATE_AN_USER_WITH_INVALID_EMAIL_ID_START_MUTATION = "CREATE_AN_USER_WITH_INVALID_EMAIL_ID";
    public static final String GET_AN_USER_WHEN_DOES_NOT_EXIST_IN_DATABASE_MUTATION = "GET_AN_USER_WHEN_DOES_NOT_EXIST_IN_DATABASE";
    public static final String INITIATE_PAYMENT_MUTATION = "INITIATE_PAYMENT_MUTATION";
    public static final String UPDATE_PAYMENT_MUTATION = "UPDATE_PAYMENT_MUTATION";
    public static final String MERCHANT_LIST_QUERY = "MERCHANT_LIST_QUERY";
    public static final String FETCH_A_MERCHANT_QUERY = "FETCH_A_MERCHANT_QUERY";
    public static final String GET_ADDRESS_LIST_BY_USERID = "GET_ADDRESS_LIST_BY_USERID";
    public static final String GET_CONTACT_LIST_BY_USERID = "GET_CONTACT_LIST_BY_USERID";
    public static final String GET_PRODUCT_ITEMS_BY_MERCHANT_ID = "GET_PRODUCT_ITEMS_BY_MERCHANT_ID";
    public static final String GET_PRODUCTS_BY_MERCHANT_ID = "GET_PRODUCTS_BY_MERCHANT_ID";
    public static final String ACTIVATE_GIFTCARD_BY_CODE = "ACTIVATE_GIFTCARD_BY_CODE";
    public static final String GET_AN_ORDER_BY_ID = "GET_AN_ORDER_BY_ID";
    public static Map<String, Object> entityMapObject = new HashMap<>();


    protected void writeResponseToFile(String fileName, String responseBody) throws IOException {
        try (FileWriter writer = new FileWriter(fileName)) {
            writer.write(responseBody);
        }
    }

    protected String readResponseFromFile(String fileName) throws IOException {
        return new String(Files.readAllBytes(Paths.get(fileName)), StandardCharsets.UTF_8);
    }

    public static String readGraphQLMutationFromFile(String fileName) {
        try (InputStream inputStream = TestUtil.class.getResourceAsStream(fileName)) {
            if (inputStream != null) {
                return new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
            } else {
                throw new IllegalArgumentException("File not found: " + fileName);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    protected void writeRequestToFile(String fileName, String requestBody) throws IOException {
        try (FileWriter writer = new FileWriter(fileName)) {
            writer.write(requestBody);
        }
    }
    protected String extractWalletIdFromJson(String jsonResponse) {
        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(jsonResponse, JsonObject.class);

        if (jsonObject.has("data") && jsonObject.get("data").isJsonObject()) {
            JsonObject dataObject = jsonObject.getAsJsonObject("data");


            if (dataObject.has("createWallet") && dataObject.get("createWallet").isJsonObject()) {
                JsonObject createWalletObject = dataObject.getAsJsonObject("createWallet");

                if (createWalletObject.has("id")) {
                    return createWalletObject.get("id").getAsString();
                }
            }
        }

        throw new IllegalArgumentException("Wallet ID not found in the JSON response.");
    }

    protected String extractOrderIdFromJson(String jsonResponse) {
        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(jsonResponse, JsonObject.class);

        if (jsonObject.has("data") && jsonObject.get("data").isJsonObject()) {
            JsonObject dataObject = jsonObject.getAsJsonObject("data");


            if (dataObject.has("createOrder") && dataObject.get("createOrder").isJsonObject()) {
                JsonObject createWalletObject = dataObject.getAsJsonObject("createOrder");

                if (createWalletObject.has("id")) {
                    return createWalletObject.get("id").getAsString();
                }
            }
        }

        throw new IllegalArgumentException("Order ID not found in the JSON response.");
    }

    protected String extractUserIdFromCreateOrderJson(String jsonResponse) {
        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(jsonResponse, JsonObject.class);

        if (jsonObject.has("data") && jsonObject.get("data").isJsonObject()) {
            JsonObject dataObject = jsonObject.getAsJsonObject("data");


            if (dataObject.has("createOrder") && dataObject.get("createOrder").isJsonObject()) {
                JsonObject createObject = dataObject.getAsJsonObject("createOrder");

                if (createObject.has("userId")) {
                    return createObject.get("userId").getAsString();
                }
            }
        }

        throw new IllegalArgumentException("User ID not found in the JSON response.");
    }

    protected String extractOrderIdFromOrderInCartJson(String jsonResponse) {
        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(jsonResponse, JsonObject.class);

        if (jsonObject.has("data") && jsonObject.get("data").isJsonObject()) {
            JsonObject dataObject = jsonObject.getAsJsonObject("data");


            if (dataObject.has("orderInCart") && dataObject.get("orderInCart").isJsonObject()) {
                JsonObject createObject = dataObject.getAsJsonObject("orderInCart");

                if (createObject.has("id")) {
                    return createObject.get("id").getAsString();
                }
            }
        }
        throw new IllegalArgumentException("Order ID not found in the JSON response.");
    }

    public static String readMutationFromFile(String fileName) throws IOException {
        try {
            return new String(Files.readAllBytes(Paths.get(fileName)));
        } catch (NoSuchFileException e) {
            throw new IllegalArgumentException("File not found: " + fileName);
        }
    }

    public static String extractMutation(String mutations, String mutationName) {
        String patternString = "(?s)###" + mutationName + "_START###(.*?)###" + mutationName + "_END###";
        Pattern pattern = Pattern.compile(patternString, Pattern.DOTALL);
        Matcher matcher = pattern.matcher(mutations);

        if (matcher.find()) {
            return matcher.group(1).trim();
        } else {
            throw new IllegalArgumentException("Mutation not found: " + mutationName);
        }
    }
}
