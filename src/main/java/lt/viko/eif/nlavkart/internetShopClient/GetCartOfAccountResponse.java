
package lt.viko.eif.nlavkart.internetShopClient;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="cart" type="{generatedsoap}Cart"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "cart"
})
@XmlRootElement(name = "getCartOfAccountResponse")
public class GetCartOfAccountResponse {

    @XmlElement(required = true)
    protected Cart cart;

    /**
     * Gets the value of the cart property.
     * 
     * @return
     *     possible object is
     *     {@link Cart }
     *     
     */
    public Cart getCart() {
        return cart;
    }

    /**
     * Sets the value of the cart property.
     * 
     * @param value
     *     allowed object is
     *     {@link Cart }
     *     
     */
    public void setCart(Cart value) {
        this.cart = value;
    }

}
