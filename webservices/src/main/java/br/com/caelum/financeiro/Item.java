package br.com.caelum.financeiro;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class Item {

	@XmlElement(required=true)
	private int quantidade;
	@XmlElement(required=true)
	private String formato;
	@XmlElement(required=true)
	private String codigo;

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public String getFormato() {
		return formato;
	}

	public void setFormato(String formato) {
		this.formato = formato;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	@Override
	public String toString() {
		return "Item [quantidade=" + quantidade + ", formato=" + formato + ", codigo=" + codigo + "]";
	}

	
	
}
