package br.com.caelum.camel;

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.http4.HttpMethods;
import org.apache.camel.impl.DefaultCamelContext;

public class RotaPedidos {

	public static void main(String[] args) throws Exception {

		CamelContext context = new DefaultCamelContext();

		context.addRoutes(new RouteBuilder() {

			@Override
			public void configure() throws Exception {

				from("file:pedidos?delay=5s&noop=true").

					setProperty("pedidoId", xpath("/pedido/id/text()")).

					setProperty("clientId", xpath("/pedido/pagamento/email-titular/text()")).

					split()
						.xpath("/pedido/itens/item").
	
					filter()
						.xpath("/item/formato[text()='EBOOK']").

						setProperty("ebookId", xpath("/item/livro/codigo/text()")).

	
					marshal(). // queremos transformar a mensagem em outro formato
						xmljson(). // de xml para json
	
					log("${id} \n ${body}").
	
					// setHeader("CamelFileName", simple("${id}.json")).
					// setHeader(Exchange.FILE_NAME, simple("${file:name.noext}.json")).
					// setHeader(Exchange.FILE_NAME, simple("${file:name.noext}-${header.CamelSplitIndex}.json")).

					// setHeader(Exchange.HTTP_METHOD, HttpMethods.POST).

					setHeader(Exchange.HTTP_METHOD, HttpMethods.GET).
					
					setHeader(Exchange.HTTP_QUERY, simple("ebookId=${property.ebookId}&pedidoId=${property.pedidoId}&clientId=${property.clientId}")).

				to("http4://localhost:8080/webservices/ebook/item");

			}

		});

		context.start(); // aqui camel realmente começa a trabalhar

		Thread.sleep(20000); // esperando um pouco para dar um tempo para camel

		context.stop();

	}

}
