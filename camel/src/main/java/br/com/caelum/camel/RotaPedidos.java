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

						routeId("rota-pedidos").

						// UTILIZANDO "seda:rota" ao invés de "direct:rota", não precisamos do multicast() 
						// multicast(). // usado para que cada rota tenha seus próprios dados de entrada
						// para que não pegue o retorno da primeira chamada "to" e envie para o segundo "to" como entrada
						
							// parallelProcessing().
							
								// timeout(500).

					to("seda:http").

					to("seda:soap");

				from("seda:http").

						routeId("rota-http").

						setProperty("pedidoId", xpath("/pedido/id/text()")).

						setProperty("email", xpath("/pedido/pagamento/email-titular/text()")).

						split().
							xpath("/pedido/itens/item").
		
						filter().
							xpath("/item/formato[text()='EBOOK']").

						setProperty("ebookId", xpath("/item/livro/codigo/text()")).
		
						// marshal(). // queremos transformar a mensagem em outro formato
							// xmljson(). // de xml para json
		
						// log("${id} \n ${body}").
		
						// setHeader("CamelFileName", simple("${id}.json")).
						// setHeader(Exchange.FILE_NAME, simple("${file:name.noext}.json")).
						// setHeader(Exchange.FILE_NAME, simple("${file:name.noext}-${header.CamelSplitIndex}.json")).

						// setHeader(Exchange.HTTP_METHOD, HttpMethods.POST).

						// setHeader(Exchange.HTTP_METHOD, HttpMethods.GET).
						
						setHeader(Exchange.HTTP_QUERY, 
								simple("clienteId=${property.email}&pedidoId=${property.pedidoId}&ebookId=${property.ebookId}")).

					to("http4://localhost:8080/webservices/ebook/item");

				from("seda:soap").

						routeId("rota-soap").

						log("chamando servico soap ${body}").

					to("mock:soap"); // para simular um endpoint

			}

		});

		context.start(); // aqui camel realmente começa a trabalhar

		Thread.sleep(20000); // esperando um pouco para dar um tempo para camel

		context.stop();

	}

}
