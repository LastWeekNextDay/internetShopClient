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
}
