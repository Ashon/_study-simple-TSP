import glstudy.glObject.glPoint;

import java.util.Vector;


public class map {
	Vector<glPoint> destination;
	public int width;
	public int height;
	public int length;
	
	public map(int length, int width, int height){
		this.length = length;
		this.width = width;
		this.height = height;
		destination = new Vector<glPoint>(length);
		for(int i = 0; i < length; i++)
			destination.add(new glPoint(Math.random() * width, Math.random() * height));
	}
	
	public int getWidth(){
		return width;
	}
	
	public int getHeight(){
		return height;
	}
}
