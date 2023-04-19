package lt.viko.eif.nlavkart.internetShopClient.SOAP.forClient;

import lt.viko.eif.nlavkart.internetShopClient.SOAP.*;

public class InteractClass {
    public static AccountPortService service;
    public static AccountPort port;

    public void init(){
        service = new AccountPortService();
        port = service.getAccountPortSoap11();
    }

    public GetAccountsResponse getAccounts(){
        return port.getAccounts(new GetAccountsRequest());
    }

    public GetAccountResponse getAccount(GetAccountRequest request) {
        return port.getAccount(request);
    }

    public RemoveItemFromCartResponse removeItemFromCart(RemoveItemFromCartRequest removeItemFromCartRequest) {
        return port.removeItemFromCart(removeItemFromCartRequest);
    }

    public RemoveAccountResponse removeAccount(RemoveAccountRequest removeAccountRequest) {
        return port.removeAccount(removeAccountRequest);
    }

    public CreateAccountResponse createAccount(CreateAccountRequest request1) {
        return port.createAccount(request1);
    }

    public GetCategoriesResponse getCategories(GetCategoriesRequest getCategoriesRequest) {
        return port.getCategories(getCategoriesRequest);
    }

    public GetItemsResponse getItems(GetItemsRequest getItemsRequest) {
        return port.getItems(getItemsRequest);
    }

    public RemoveItemResponse deleteItem(RemoveItemRequest removeItemRequest) {
        return port.removeItem(removeItemRequest);
    }

    public AddItemToCartResponse addItemToCart(AddItemToCartRequest addItemToCartRequest) {
        return port.addItemToCart(addItemToCartRequest);
    }
}
