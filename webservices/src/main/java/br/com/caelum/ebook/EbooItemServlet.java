package br.com.caelum.ebook;

import java.io.IOException;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//http://localhost:8080/webservices/ebook/item
@WebServlet(loadOnStartup=1, urlPatterns="/ebook/item")
public class EbooItemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
	@Override
	public void init() throws ServletException {
		System.out.println("Subindo servico ebook http://localhost:8080/webservices/ebook/item");
	}
	
	
	/*
	  HTTP POST para: 
	  http://localhost:8080/webservices/ebook/item
	 */  
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		System.out.print("Ebook (HTTP) recebendo POST " + request.getRequestURI()  + " - ");
		Scanner scanner = new Scanner(request.getInputStream());
		while(scanner.hasNextLine()) {
			System.out.println(scanner.nextLine());
		}
		scanner.close();
	}
	
	/*
	  HTTP GET com os query params para 
	  http://localhost:8080/webservices/ebook/item
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String pedidoId = req.getParameter("pedidoId");
		String clienteId = req.getParameter("clienteId");
		String ebookId = req.getParameter("ebookId");

		System.out.print("Ebook (HTTP) recebendo GET " + req.getRequestURI() + " - ");
		System.out.printf("Ebook ID: %s, Cliente ID: %15s, Pedido ID: %s %n", ebookId, clienteId, pedidoId);
		
		resp.getWriter().println("service ebook ok");
	}

}
