
package lt.viko.eif.nlavkart.internetShopClient.SOAP;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebResult;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;
import jakarta.xml.bind.annotation.XmlSeeAlso;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebService(name = "AccountPort", targetNamespace = "http://www.example.com/")
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
@XmlSeeAlso({
    ObjectFactory.class
})
public interface AccountPort {


    /**
     * 
     * @param getAccountRequest
     * @return
     *     returns lt.viko.eif.nlavkart.internetShopClient.SOAP.GetAccountResponse
     */
    @WebMethod
    @WebResult(name = "getAccountResponse", targetNamespace = "generatedsoap", partName = "getAccountResponse")
    public GetAccountResponse getAccount(
        @WebParam(name = "getAccountRequest", targetNamespace = "generatedsoap", partName = "getAccountRequest")
        GetAccountRequest getAccountRequest);

    /**
     * 
     * @param getCategoriesRequest
     * @return
     *     returns lt.viko.eif.nlavkart.internetShopClient.SOAP.GetCategoriesResponse
     */
    @WebMethod
    @WebResult(name = "getCategoriesResponse", targetNamespace = "generatedsoap", partName = "getCategoriesResponse")
    public GetCategoriesResponse getCategories(
        @WebParam(name = "getCategoriesRequest", targetNamespace = "generatedsoap", partName = "getCategoriesRequest")
        GetCategoriesRequest getCategoriesRequest);

    /**
     * 
     * @param getAccountsRequest
     * @return
     *     returns lt.viko.eif.nlavkart.internetShopClient.SOAP.GetAccountsResponse
     */
    @WebMethod
    @WebResult(name = "getAccountsResponse", targetNamespace = "generatedsoap", partName = "getAccountsResponse")
    public GetAccountsResponse getAccounts(
        @WebParam(name = "getAccountsRequest", targetNamespace = "generatedsoap", partName = "getAccountsRequest")
        GetAccountsRequest getAccountsRequest);

    /**
     * 
     * @param removeItemRequest
     * @return
     *     returns lt.viko.eif.nlavkart.internetShopClient.SOAP.RemoveItemResponse
     */
    @WebMethod
    @WebResult(name = "removeItemResponse", targetNamespace = "generatedsoap", partName = "removeItemResponse")
    public RemoveItemResponse removeItem(
        @WebParam(name = "removeItemRequest", targetNamespace = "generatedsoap", partName = "removeItemRequest")
        RemoveItemRequest removeItemRequest);

    /**
     * 
     * @param addItemToCartRequest
     * @return
     *     returns lt.viko.eif.nlavkart.internetShopClient.SOAP.AddItemToCartResponse
     */
    @WebMethod
    @WebResult(name = "addItemToCartResponse", targetNamespace = "generatedsoap", partName = "addItemToCartResponse")
    public AddItemToCartResponse addItemToCart(
        @WebParam(name = "addItemToCartRequest", targetNamespace = "generatedsoap", partName = "addItemToCartRequest")
        AddItemToCartRequest addItemToCartRequest);

    /**
     * 
     * @param createAccountRequest
     * @return
     *     returns lt.viko.eif.nlavkart.internetShopClient.SOAP.CreateAccountResponse
     */
    @WebMethod
    @WebResult(name = "createAccountResponse", targetNamespace = "generatedsoap", partName = "createAccountResponse")
    public CreateAccountResponse createAccount(
        @WebParam(name = "createAccountRequest", targetNamespace = "generatedsoap", partName = "createAccountRequest")
        CreateAccountRequest createAccountRequest);

    /**
     * 
     * @param getItemsRequest
     * @return
     *     returns lt.viko.eif.nlavkart.internetShopClient.SOAP.GetItemsResponse
     */
    @WebMethod
    @WebResult(name = "getItemsResponse", targetNamespace = "generatedsoap", partName = "getItemsResponse")
    public GetItemsResponse getItems(
        @WebParam(name = "getItemsRequest", targetNamespace = "generatedsoap", partName = "getItemsRequest")
        GetItemsRequest getItemsRequest);

    /**
     * 
     * @param removeItemFromCartRequest
     * @return
     *     returns lt.viko.eif.nlavkart.internetShopClient.SOAP.RemoveItemFromCartResponse
     */
    @WebMethod
    @WebResult(name = "removeItemFromCartResponse", targetNamespace = "generatedsoap", partName = "removeItemFromCartResponse")
    public RemoveItemFromCartResponse removeItemFromCart(
        @WebParam(name = "removeItemFromCartRequest", targetNamespace = "generatedsoap", partName = "removeItemFromCartRequest")
        RemoveItemFromCartRequest removeItemFromCartRequest);

    /**
     * 
     * @param removeAccountRequest
     * @return
     *     returns lt.viko.eif.nlavkart.internetShopClient.SOAP.RemoveAccountResponse
     */
    @WebMethod
    @WebResult(name = "removeAccountResponse", targetNamespace = "generatedsoap", partName = "removeAccountResponse")
    public RemoveAccountResponse removeAccount(
        @WebParam(name = "removeAccountRequest", targetNamespace = "generatedsoap", partName = "removeAccountRequest")
        RemoveAccountRequest removeAccountRequest);

    /**
     * 
     * @param getItemRequest
     * @return
     *     returns lt.viko.eif.nlavkart.internetShopClient.SOAP.GetItemResponse
     */
    @WebMethod
    @WebResult(name = "getItemResponse", targetNamespace = "generatedsoap", partName = "getItemResponse")
    public GetItemResponse getItem(
        @WebParam(name = "getItemRequest", targetNamespace = "generatedsoap", partName = "getItemRequest")
        GetItemRequest getItemRequest);

}
