import glstudy.glObject.glPoint;

import java.util.Vector;


public class mapSolver {
	public map m;
	public Vector<glPoint> solution;
	public Vector<glPoint> temp;
	
	public mapSolver(map m){
		this.m = m;
		solution = new Vector<glPoint>(m.length);
	}

	public double step(int num){
		double dist = 0;
		temp = (Vector<glPoint>) m.destination.clone();
		while(temp.size() != 0){
			glPoint p;
			if(solution.size() == 0)
				p = temp.get(num);
			else
				p = solution.get(solution.size() - 1);
			
			int idx = temp.size() - 1;
			double cond0 = p.getDist(temp.get(idx));
			for(glPoint p0 : temp)
				if(!p.equals(p0)){
					double cond1 = p.getDist(p0);
					if(cond1 < cond0){
						cond0 = cond1;
						idx = temp.indexOf(p0);
					}
				}
			dist += cond0;
			solution.add(new glPoint(temp.get(idx).x, temp.get(idx).y));
			temp.remove(idx);
		}
		return dist;
	}

	public void solve(){
		step(0);
	}

	public static void main(String [] args){
		map m = new map(100, 800, 600);
		mapSolver s = new mapSolver(m);
		s.solve();
	}
}
