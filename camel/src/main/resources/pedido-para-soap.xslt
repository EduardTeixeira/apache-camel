<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output omit-xml-declaration="no" indent="yes"/>

    <xsl:template match="/pedido">
		<soap:Envelope xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/" xmlns:nota="http://financeiro.com.br/nota">
           <soap:Body>
                <nota:nota>
                    <nota:data><xsl:value-of select="dataCompra"/></nota:data>
                    <nota:valor><xsl:value-of select="pagamento/valor"/></nota:valor>
                    <nota:itens>
                        <xsl:for-each select="itens/item">
                            <nota:item>
                            	<nota:quantidade><xsl:value-of select="quantidade"/></nota:quantidade>
                            	<nota:formato><xsl:value-of select="formato"/></nota:formato>
                            	<nota:codigo><xsl:value-of select="livro/codigo"/></nota:codigo>
                            </nota:item>
                        </xsl:for-each>
                    </nota:itens>
                 </nota:nota>
            </soap:Body>
        </soap:Envelope>        
    </xsl:template>

</xsl:stylesheet>