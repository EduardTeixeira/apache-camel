package br.com.caelum.financeiro;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Nota {
	
	@XmlElement(required=true)
	private Calendar data;

	@XmlElement(required=true)
	private BigDecimal valor;
	
	@XmlElementWrapper(required=true,name="itens")
	@XmlElement(required=true,name="item")
	private List<Item> itens;

	public Calendar getData() {
		return data;
	}

	public void setData(Calendar data) {
		this.data = data;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public List<Item> getItens() {
		return itens;
	}

	public void setItens(List<Item> itens) {
		this.itens = itens;
	}

	@Override
	public String toString() {
		return "Nota [data=" + formatData() + ", valor=" + valor + ", itens=" + itens + "]";
	}

	private String formatData() {
		if(this.data == null) {
			return "";
		}
		return new SimpleDateFormat("dd/MM/yyyy").format(this.data.getTime());
	}
	
	
	
	

}
