package lt.viko.eif.nlavkart.internetShopClient.SOAP.forClient;

import lt.viko.eif.nlavkart.internetShopClient.AbstractInteractor.AbstractInteractor;
import lt.viko.eif.nlavkart.internetShopClient.SOAP.*;
import lt.viko.eif.nlavkart.internetShopClient.generated.*;

public class InteractClassSoap implements AbstractInteractor {

    @Override
    public GetAccountsResponse getAccounts(){
        AccountPortService service = new AccountPortService();
        AccountPort port = service.getAccountPortSoap11();
        return port.getAccounts(new GetAccountsRequest());
    }

    @Override
    public GetAccountResponse getAccount(GetAccountRequest request) {
        AccountPortService service = new AccountPortService();
        AccountPort port = service.getAccountPortSoap11();
        return port.getAccount(request);
    }

    @Override
    public RemoveItemFromCartResponse removeItemFromCart(
            RemoveItemFromCartRequest removeItemFromCartRequest) {
        AccountPortService service = new AccountPortService();
        AccountPort port = service.getAccountPortSoap11();
        return port.removeItemFromCart(removeItemFromCartRequest);
    }

    @Override
    public RemoveAccountResponse removeAccount(RemoveAccountRequest removeAccountRequest) {
        AccountPortService service = new AccountPortService();
        AccountPort port = service.getAccountPortSoap11();
        return port.removeAccount(removeAccountRequest);
    }

    @Override
    public CreateAccountResponse createAccount(CreateAccountRequest request1) {
        AccountPortService service = new AccountPortService();
        AccountPort port = service.getAccountPortSoap11();
        return port.createAccount(request1);
    }

    @Override
    public GetCategoriesResponse getCategories(GetCategoriesRequest getCategoriesRequest) {
        AccountPortService service = new AccountPortService();
        AccountPort port = service.getAccountPortSoap11();
        return port.getCategories(getCategoriesRequest);
    }

    @Override
    public GetItemsResponse getItems(GetItemsRequest getItemsRequest) {
        AccountPortService service = new AccountPortService();
        AccountPort port = service.getAccountPortSoap11();
        return port.getItems(getItemsRequest);
    }

    @Override
    public RemoveItemResponse deleteItem(RemoveItemRequest removeItemRequest) {
        AccountPortService service = new AccountPortService();
        AccountPort port = service.getAccountPortSoap11();
        return port.removeItem(removeItemRequest);
    }

    @Override
    public AddItemToCartResponse addItemToCart(AddItemToCartRequest addItemToCartRequest) {
        AccountPortService service = new AccountPortService();
        AccountPort port = service.getAccountPortSoap11();
        return port.addItemToCart(addItemToCartRequest);
    }
}
