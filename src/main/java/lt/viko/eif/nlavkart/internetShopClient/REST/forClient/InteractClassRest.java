package lt.viko.eif.nlavkart.internetShopClient.REST.forClient;

import lt.viko.eif.nlavkart.internetShopClient.AbstractInteractor.AbstractInteractor;
import lt.viko.eif.nlavkart.internetShopClient.REST.util.JSONJavaObjectConverter;
import lt.viko.eif.nlavkart.internetShopClient.generated.*;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.io.StringReader;

public class InteractClassRest implements AbstractInteractor {

    @Override
    public GetAccountsResponse getAccounts() {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("http://localhost:8080/accounts")
                .build();

        Response response = null;
        String body = null;
        try {
            response = client.newCall(request).execute();
            body = response.body().string();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        GetAccountsResponse accountsResponse = new GetAccountsResponse();
        JSONObject parsedResponse = null;
        try {
            parsedResponse = (JSONObject) new JSONParser().parse(new StringReader(body));
        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }
        JSONArray accounts = (JSONArray) parsedResponse.get("accounts");
        for (Object account : accounts) {
            JSONObject accountJson = (JSONObject) account;
            Account account1 = JSONJavaObjectConverter.convertJson(accountJson.toJSONString(),
                    Account.class);
            accountsResponse.getAccounts().add(account1);
        }
        return accountsResponse;
    }

    @Override
    public GetAccountResponse getAccount(GetAccountRequest request) {
        String url = "http://localhost:8080/accounts/";
        OkHttpClient client = new OkHttpClient();
        GetAccountResponse response = new GetAccountResponse();
        if (request.getAccountId() >= 0){
            url += request.getAccountId();
        } else if (!request.getUsername().equals("")){
            url += request.getUsername();
        } else {
            response.setAccount(null);
            return response;
        }
        Request request1 = new Request.Builder()
                .url(url)
                .build();
        Response response1 = null;
        String body = null;
        try {
            response1 = client.newCall(request1).execute();
            body = response1.body().string();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        JSONObject parsedResponse = null;
        try {
            parsedResponse = (JSONObject) new JSONParser().parse(new StringReader(body));
        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }
        JSONObject accountJson = (JSONObject) parsedResponse.get("account");
        Account account = JSONJavaObjectConverter.convertJson(accountJson.toJSONString(),
                Account.class);
        response.setAccount(account);
        return response;
    }

    @Override
    public RemoveItemFromCartResponse removeItemFromCart(RemoveItemFromCartRequest removeItemFromCartRequest) {
        String url = "http://localhost:8080/accounts/carts/removeItem";
        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(JSONJavaObjectConverter.convertObject(
                removeItemFromCartRequest),
                okhttp3.MediaType.parse("application/json; charset=utf-8"));

        Request request = new Request.Builder()
                .url(url)
                .put(body)
                .build();

        Response response1 = null;
        String responseBody = null;
        try {
            response1 = client.newCall(request).execute();
            responseBody = response1.body().string();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        JSONObject parsedResponse = null;
        try {
            parsedResponse = (JSONObject) new JSONParser().parse(
                    new StringReader(responseBody));
        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }
        RemoveItemFromCartResponse response = JSONJavaObjectConverter.convertJson(
                parsedResponse.toJSONString(), RemoveItemFromCartResponse.class);
        return response;
    }

    @Override
    public RemoveAccountResponse removeAccount(RemoveAccountRequest removeAccountRequest) {
        String url = "http://localhost:8080/accounts/remove";
        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(JSONJavaObjectConverter.convertObject(
                removeAccountRequest),
                okhttp3.MediaType.parse("application/json; charset=utf-8"));

        Request request = new Request.Builder()
                .url(url)
                .put(body)
                .build();

        Response response1 = null;
        String responseBody = null;
        try {
            response1 = client.newCall(request).execute();
            responseBody = response1.body().string();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        RemoveAccountResponse response = JSONJavaObjectConverter.convertJson(
                responseBody, RemoveAccountResponse.class);
        return response;
    }

    @Override
    public CreateAccountResponse createAccount(CreateAccountRequest request1) {
        String url = "http://localhost:8080/accounts/create";
        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(JSONJavaObjectConverter.convertObject(
                request1),
                okhttp3.MediaType.parse("application/json; charset=utf-8"));

        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();

        Response response1 = null;
        String responseBody = null;
        try {
            response1 = client.newCall(request).execute();
            responseBody = response1.body().string();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        CreateAccountResponse response = JSONJavaObjectConverter.convertJson(
                responseBody, CreateAccountResponse.class);
        return response;
    }

    @Override
    public GetCategoriesResponse getCategories(GetCategoriesRequest getCategoriesRequest) {
        String url = "http://localhost:8080/categories";
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();

        Response response1 = null;
        String responseBody = null;
        try {
            response1 = client.newCall(request).execute();
            responseBody = response1.body().string();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        GetCategoriesResponse response = JSONJavaObjectConverter.convertJson(
                responseBody, GetCategoriesResponse.class);
        return response;
    }

    @Override
    public GetItemsResponse getItems(GetItemsRequest getItemsRequest) {
        String url = "http://localhost:8080/items";
        if (getItemsRequest.getCategoryId() >= 0){
            url += "/category/" + getItemsRequest.getCategoryId();
        }
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();

        Response response1 = null;
        String responseBody = null;
        try {
            response1 = client.newCall(request).execute();
            responseBody = response1.body().string();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        GetItemsResponse response = JSONJavaObjectConverter.convertJson(
                responseBody, GetItemsResponse.class);
        return response;
    }

    @Override
    public RemoveItemResponse deleteItem(RemoveItemRequest removeItemRequest) {
        String url = "http://localhost:8080/items/remove";
        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(JSONJavaObjectConverter.convertObject(
                removeItemRequest),
                okhttp3.MediaType.parse("application/json; charset=utf-8"));

        Request request = new Request.Builder()
                .url(url)
                .put(body)
                .build();

        Response response1 = null;
        String responseBody = null;
        try {
            response1 = client.newCall(request).execute();
            responseBody = response1.body().string();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        RemoveItemResponse response = JSONJavaObjectConverter.convertJson(
                responseBody, RemoveItemResponse.class);
        return response;
    }

    @Override
    public AddItemToCartResponse addItemToCart(AddItemToCartRequest addItemToCartRequest) {
        String url = "http://localhost:8080/accounts/carts/addItem";
        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(JSONJavaObjectConverter.convertObject(
                addItemToCartRequest),
                okhttp3.MediaType.parse("application/json; charset=utf-8"));

        Request request = new Request.Builder()
                .url(url)
                .put(body)
                .build();

        Response response1 = null;
        String responseBody = null;
        try {
            response1 = client.newCall(request).execute();
            responseBody = response1.body().string();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        AddItemToCartResponse response = JSONJavaObjectConverter.convertJson(
                responseBody, AddItemToCartResponse.class);
        return response;
    }
}
