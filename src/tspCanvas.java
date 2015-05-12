import glstudy.glObject.glPoint;
import glstudy.glUtil.bufferedCanvas;
import glstudy.glUtil.eventedFrame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;


public class tspCanvas extends bufferedCanvas implements Runnable{
	public mapSolver ms;
	
	public tspCanvas(map m){
		this.ms = new mapSolver(m);
		this.setPreferredSize(new Dimension(m.getWidth(), m.getHeight()));
	}
	
	public void draw(Graphics g){
		
		g.setColor(Color.yellow);
		for(glPoint p : ms.m.destination)
			if(ms.m.destination.indexOf(p) != 0)
				g.drawLine((int)p.x, (int)p.y, (int)(ms.m.destination.get(ms.m.destination.indexOf(p) - 1).x), (int)(ms.m.destination.get(ms.m.destination.indexOf(p) - 1).y));
		
		g.setColor(Color.black);
		for(glPoint p : ms.solution)
			if(ms.solution.indexOf(p) != 0)
				g.drawLine((int)p.x, (int)p.y, (int)(ms.solution.get(ms.solution.indexOf(p) - 1).x), (int)(ms.solution.get(ms.solution.indexOf(p) - 1).y));
		
		g.setColor(Color.black);
		for(glPoint p: ms.m.destination)
			p.draw(g);
	}
	
	private volatile Thread timer;
	public void start(){
		timer = new Thread(this);
		timer.start();
	}
	public void stop(){
		timer = null;
	}
	public void run(){
		int i = 0;
		while(timer == Thread.currentThread()){
			try{
				Thread.sleep(200);
			}catch(InterruptedException e){ }
			if(i < ms.m.destination.size())
				stop();
			ms.step(i);
			repaint();
			i++;
		}
	}
	
	public static void main(String [] args){
		map m = new map(100, 800, 600);
		eventedFrame f = new eventedFrame();
		tspCanvas tsp = new tspCanvas(m);
		f.add(tsp);
		f.pack();
		f.setVisible(true);
		tsp.start();
	}
}
