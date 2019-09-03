package it.polito.tdp.newufosightings.model;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.newufosightings.db.NewUfoSightingsDAO;
import it.polito.tdp.newufosightings.db.ViciniPesati;

public class Model {
	
	private NewUfoSightingsDAO dao;
	private  SimpleWeightedGraph<State,DefaultWeightedEdge> grafo;
	private Map<String,State> idMap;
	
	public Model() {
		this.dao = new NewUfoSightingsDAO ();
		this.idMap= new HashMap<>();
	}

	public void creaGrafo(int anno, int giorni) {
		this.grafo= new  SimpleWeightedGraph<>(DefaultWeightedEdge.class);
		dao.loadAllStates(idMap);
		
		Graphs.addAllVertices(this.grafo, idMap.values());
		
		for(ViciniPesati vp : dao.getAllPesi(anno,giorni,idMap)) {
			Graphs.addEdgeWithVertices(this.grafo, vp.getVp1(), vp.getVp2(),vp.getPeso());
		}
		
		
		System.out.println("Grafo creato, vertici: "+grafo.vertexSet().size());
		System.out.println("# archi: "+grafo.edgeSet().size());
		
	}

	public SimpleWeightedGraph<State, DefaultWeightedEdge> getGrafo() {
		return grafo;
	}

	public void setGrafo(SimpleWeightedGraph<State, DefaultWeightedEdge> grafo) {
		this.grafo = grafo;
	}

	public List<StatePese> getVicini()  {
			List<StatePese> caccc = new LinkedList<>();
			int pesoTot=0;
			
		for(State s : this.grafo.vertexSet()) {
			List<State> vicini = Graphs.neighborListOf(grafo, s);
			
			for(State neighbor : vicini){
				DefaultWeightedEdge edge = grafo.getEdge(s, neighbor);
				double peso = grafo.getEdgeWeight(edge);
				pesoTot += peso;	
			}
			caccc.add(new StatePese(s,pesoTot));
		}
					 
			return caccc;
		}
		
	}
	
	


