package br.com.caelum.camel;

import java.util.Calendar;

public class Negociacao {

	private double preco;
	private int quantidade;
	private Calendar data;

	// getters e setters

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public Calendar getData() {
		return data;
	}

	public void setData(Calendar data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "Negociacao [preco=" + preco + ", quantidade=" + quantidade + "]";
	}

}