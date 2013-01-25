package rak248.opt;

import java.util.ArrayList;

import rak248.util.VisualizableIRNode;
import rak248.xi.ir.TempIR;

public class InterferenceGraphNode {
	private TempIR temp;
	private ArrayList<InterferenceGraphNode> neighbors;
	private ArrayList<InterferenceGraphNode> coalesce;
	
	public InterferenceGraphNode(TempIR temp){
		this.temp = temp;
		neighbors = new ArrayList<InterferenceGraphNode>();
		coalesce = new ArrayList<InterferenceGraphNode>();
	}
	
	public int hashCode(){
		return temp.getId().hashCode();
	}
	
	public void addNeighbor(InterferenceGraphNode temp){
		neighbors.add(temp);
	}
	
	public ArrayList<InterferenceGraphNode> getNeighbors() {
		return neighbors;
	}
	public void setNeighbors(ArrayList<InterferenceGraphNode> neighbors) {
		this.neighbors = neighbors;
	}
	public TempIR getTemp() {
		return temp;
	}
	public void setTemp(TempIR temp) {
		this.temp = temp;
	}
	
	public String toString(){
		String ret = "";
		for (InterferenceGraphNode ign: neighbors){
			ret += ign.getTemp().prettyPrint() + ",";
		}
		return "["+temp.prettyPrint()+": "+ret+"]";
	}

	public ArrayList<InterferenceGraphNode> getCoalesce() {
		return coalesce;
	}

	public void setCoalesce(ArrayList<InterferenceGraphNode> coalesce) {
		this.coalesce = coalesce;
	}

	public boolean connects(String string) {
		for (InterferenceGraphNode ign: neighbors){
			return ign.getTemp().getId().equals(string);
		}
		return false;
	}

	public void combine(InterferenceGraphNode ign2) {
		coalesce.add(ign2);
		for (InterferenceGraphNode node: ign2.getNeighbors()){
			if (!neighbors.contains(node)){
				neighbors.add(node);
			}
		}
		
	}
	
	public int hashcode(){
		return temp.getId().hashCode();
	}
	
}
