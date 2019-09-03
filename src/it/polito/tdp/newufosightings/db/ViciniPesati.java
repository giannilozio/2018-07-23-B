package it.polito.tdp.newufosightings.db;

import it.polito.tdp.newufosightings.model.State;

public class ViciniPesati {

	State vp1;
	State vp2;
	int peso;
	
	public ViciniPesati(State vp1, State vp2, int peso) {
		this.vp1=vp1;
		this.vp2=vp2;
		this.peso=peso;
	}

	public State getVp1() {
		return vp1;
	}

	public void setVp1(State vp1) {
		this.vp1 = vp1;
	}

	public State getVp2() {
		return vp2;
	}

	public void setVp2(State vp2) {
		this.vp2 = vp2;
	}

	public int getPeso() {
		return peso;
	}

	public void setPeso(int peso) {
		this.peso = peso;
	}

}
