package br.com.caelum.financeiro;

import javax.jws.Oneway;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.ParameterStyle;
import javax.jws.soap.SOAPBinding.Style;
import javax.jws.soap.SOAPBinding.Use;

@WebService(targetNamespace="http://financeiro.com.br/nota") 
@SOAPBinding(use=Use.LITERAL,style=Style.DOCUMENT,parameterStyle=ParameterStyle.BARE)
public class FinanceiroSoapService {
	
	
	public FinanceiroSoapService() {
		System.out.println("Subindo serviço SOAP: http://localhost:8080/webservices/financeiro");
	}
	
	@WebMethod @Oneway
	public void cadastraNota(@WebParam(name="nota") Nota nota) {
		
		System.out.println("Financeiro (SOAP) recebendo: "  + nota);

	}
}
