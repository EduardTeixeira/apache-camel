package br.com.caelum.camel;

import org.apache.activemq.camel.component.ActiveMQComponent;
import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;

public class RotaEnviaPedidos {

	public static void main(String[] args) throws Exception {

		CamelContext context = new DefaultCamelContext();
		context.addComponent("activemq", ActiveMQComponent.activeMQComponent("tcp://localhost:61616"));

		context.addRoutes(new RouteBuilder() {

			@Override
			public void configure() throws Exception {

				from("file:pedidos?noop=true")

						.to("activemq:queue:pedidos");

			}

		});

		context.start(); // aqui camel realmente começa a trabalhar

		Thread.sleep(20000); // esperando um pouco para dar um tempo para camel

		context.stop();

	}

}
