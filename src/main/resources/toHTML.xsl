<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:ns2="generatedsoap">
    <xsl:template match="/">
        <html>
            <head>
                <title>Account Details</title>
                <style>
                    body {
                    font-family: Arial, sans-serif;
                    font-size: 14px;
                    background-color: #f5f5f5;
                    color: #333;
                    }
                    h1 {
                    font-size: 24px;
                    color: #333;
                    }
                    table {
                    width: 100%;
                    border-collapse: collapse;
                    margin-top: 20px;
                    }
                    th, td {
                    text-align: left;
                    padding: 8px;
                    border-bottom: 1px solid #ddd;
                    }
                    th {
                    background-color: #f2f2f2;
                    color: #333;
                    }
                    .cart-header {
                    font-size: 18px;
                    font-weight: bold;
                    margin-top: 40px;
                    margin-bottom: 20px;
                    }
                    .cart-total {
                    margin-top: 20px;
                    }
                </style>
            </head>
            <body>
                <h1>Account Details</h1>
                <table>
                    <tr>
                        <th>ID</th>
                        <td><xsl:value-of select="/ns2:Account/id"/></td>
                    </tr>
                    <tr>
                        <th>Username</th>
                        <td><xsl:value-of select="/ns2:Account/username"/></td>
                    </tr>
                    <tr>
                        <th>Password</th>
                        <td><xsl:value-of select="/ns2:Account/password"/></td>
                    </tr>
                </table>
                <div class="cart-header">Cart</div>
                <xsl:if test="/ns2:Account/cart/items">
                    <table>
                        <tr>
                            <th>ID</th>
                            <th>Name</th>
                            <th>Description</th>
                            <th>Category</th>
                            <th>Price</th>
                        </tr>
                            <xsl:for-each select="/ns2:Account/cart/items">
                                <tr>
                                    <td><xsl:value-of select="id"/></td>
                                    <td><xsl:value-of select="name"/></td>
                                    <td><xsl:value-of select="description"/></td>
                                    <td><xsl:value-of select="category/name"/></td>
                                    <td><xsl:value-of select="price"/></td>
                                </tr>
                            </xsl:for-each>
                            <tr class="cart-total">
                                <th colspan="4">Total</th>
                                <td><xsl:value-of select="sum(/ns2:Account/cart/items/price)"/></td>
                            </tr>
                    </table>
                </xsl:if>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>