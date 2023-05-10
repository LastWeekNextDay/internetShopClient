package lt.viko.eif.nlavkart.internetShopClient.AbstractInteractor;

import lt.viko.eif.nlavkart.internetShopClient.generated.*;

public interface AbstractInteractor {
    GetAccountsResponse getAccounts();

    GetAccountResponse getAccount(GetAccountRequest request);

    RemoveItemFromCartResponse removeItemFromCart(RemoveItemFromCartRequest removeItemFromCartRequest);

    RemoveAccountResponse removeAccount(RemoveAccountRequest removeAccountRequest);

    CreateAccountResponse createAccount(CreateAccountRequest request1);

    GetCategoriesResponse getCategories(GetCategoriesRequest getCategoriesRequest);

    GetItemsResponse getItems(GetItemsRequest getItemsRequest);

    RemoveItemResponse deleteItem(RemoveItemRequest removeItemRequest);

    AddItemToCartResponse addItemToCart(AddItemToCartRequest addItemToCartRequest);
}
