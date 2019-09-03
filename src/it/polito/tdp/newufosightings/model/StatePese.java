package it.polito.tdp.newufosightings.model;

public class StatePese {
	
	private State s;
	int pesoTot;

	public StatePese(State s, int pesoTot) {
		this.s=s;
		this.pesoTot=pesoTot;
	}

	public State getS() {
		return s;
	}

	public void setS(State s) {
		this.s = s;
	}

	public int getPesoTot() {
		return pesoTot;
	}

	public void setPesoTot(int pesoTot) {
		this.pesoTot = pesoTot;
	}

}
