package br.com.caelum.camel;

import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;

public class RotaDirectEProducerTest {

	public static void main(String[] args) throws Exception {

		CamelContext context = new DefaultCamelContext();

		context.addRoutes(new RouteBuilder() {

			@Override
			public void configure() throws Exception {

				from("direct:soap")

						.routeId("rota-soap")

						.log("chamando servico soap ${body}")
						
					    .throwException(new RuntimeException())

						.to("mock:soap");

			}

		});

		context.start();

		ProducerTemplate producer = context.createProducerTemplate();

		producer.sendBody("direct:soap", "<pedido> ... </pedido>");

		Thread.sleep(10000);

		context.stop();

	}

}
