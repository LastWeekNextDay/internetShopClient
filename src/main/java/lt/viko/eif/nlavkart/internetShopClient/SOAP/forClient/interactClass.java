package lt.viko.eif.nlavkart.internetShopClient.SOAP.forClient;

import lt.viko.eif.nlavkart.internetShopClient.SOAP.AccountPort;
import lt.viko.eif.nlavkart.internetShopClient.SOAP.AccountPortService;

public class interactClass {
    public static AccountPortService service;
    public static AccountPort port;

    public static void init(){
        service = new AccountPortService();
        port = service.getAccountPortSoap11();
    }
}
