package rak248.opt;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import rak248.xi.ir.ExpressionIR;
import rak248.xi.ir.LabelIR;
import rak248.xi.ir.MoveIR;
import rak248.xi.ir.TempIR;

public class Coalescing {

	public static void combineMove(HashSet<CFGNode> m, HashMap<String, InterferenceGraphNode> h, int i){
		for (CFGNode node: m){
			
			if (node.getIrnode() instanceof MoveIR && !(node.getIrnode().getChildren().get(1) instanceof LabelIR)){
				MoveIR move = (MoveIR) node.getIrnode();
				ExpressionIR temp1 = (ExpressionIR) move.getChildren().get(0);
				ExpressionIR temp2 = (ExpressionIR) move.getChildren().get(1);
				if (temp1 instanceof TempIR && temp2 instanceof TempIR
						&& !((TempIR) temp2).getId().startsWith("_args")){
					InterferenceGraphNode ign1 = h.get(((TempIR) temp1).getId());
					boolean contain = ign1.connects(((TempIR) temp2).getId());
					if (!contain){
						InterferenceGraphNode ign2 = h.get(((TempIR) temp2).getId()); 
						//System.out.println("TEMP@@@: "+ign1+"::"+ign2);
						int max = maxNeighbors(ign1,ign2);
						if (max < i){
							ign1.combine(ign2);
							h.remove(temp2);
						}
					}
				}
			}
		}
		
	}

	private static int maxNeighbors(InterferenceGraphNode ign1,
			InterferenceGraphNode ign2) {
		int i = ign1.getNeighbors().size()+ign2.getNeighbors().size();
		for (InterferenceGraphNode node1: ign1.getNeighbors()){
			for (InterferenceGraphNode node2: ign2.getNeighbors()){
				if (node1.getTemp().getId().equals(node2.getTemp().getId())){
					i--;
				}
			}
		}
		return i;
	}

}
