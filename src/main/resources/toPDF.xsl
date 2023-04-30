<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:ns2="generatedsoap">
    <xsl:template match="/">
        <fo:root xmlns:fo="http://www.w3.org/1999/XSL/Format">
            <fo:layout-master-set>
                <fo:simple-page-master master-name="page" page-height="11in" page-width="8.5in" margin-top="0.5in" margin-bottom="0.5in" margin-left="0.5in" margin-right="0.5in">
                    <fo:region-body margin-top="1in" margin-bottom="1in"/>
                </fo:simple-page-master>
            </fo:layout-master-set>
            <fo:page-sequence master-reference="page">
                <fo:flow flow-name="xsl-region-body">
                    <fo:block font-size="24pt" font-weight="bold">Account Information</fo:block>
                    <fo:block font-size="18pt" margin-top="0.5in">Username: <xsl:value-of select="/ns2:Account/username"/></fo:block>
                    <fo:block font-size="18pt">Password: <xsl:value-of select="/ns2:Account/password"/></fo:block>
                    <fo:block font-size="18pt" margin-top="0.5in">Cart:</fo:block>
                    <xsl:if test="/ns2:Account/cart/items">
                            <fo:table table-layout="fixed">
                            <fo:table-header>
                                <fo:table-row>
                                    <fo:table-cell font-weight="bold"><fo:block>Item Name</fo:block></fo:table-cell>
                                    <fo:table-cell font-weight="bold"><fo:block>Description</fo:block></fo:table-cell>
                                    <fo:table-cell font-weight="bold"><fo:block>Category</fo:block></fo:table-cell>
                                    <fo:table-cell font-weight="bold"><fo:block>Price</fo:block></fo:table-cell>
                                </fo:table-row>
                            </fo:table-header>
                                <fo:table-body>
                                    <xsl:for-each select="/ns2:Account/cart/items">
                                        <fo:table-row>
                                            <fo:table-cell><fo:block><xsl:value-of select="name"/></fo:block></fo:table-cell>
                                            <fo:table-cell><fo:block><xsl:value-of select="description"/></fo:block></fo:table-cell>
                                            <fo:table-cell><fo:block><xsl:value-of select="category/name"/></fo:block></fo:table-cell>
                                            <fo:table-cell><fo:block><xsl:value-of select="price"/></fo:block></fo:table-cell>
                                        </fo:table-row>
                                    </xsl:for-each>
                                </fo:table-body>
                            </fo:table>
                    </xsl:if>
                </fo:flow>
            </fo:page-sequence>
        </fo:root>
    </xsl:template>
</xsl:stylesheet>
