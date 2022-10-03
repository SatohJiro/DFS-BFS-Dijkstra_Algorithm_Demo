package project;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

public class ListPoint {
	List<Point> listPoint;

	public ListPoint() {
		super();
		this.listPoint = new ArrayList<Point>();
	}
	public void addPoint(Point point) {
		listPoint.add(point);
	}
	public void removePoint(int index) {
		listPoint.remove(index);
	}
	public void drawPoint(Graphics g) {
		for (int i = 0; i < listPoint.size(); i++) {
			listPoint.get(i).drawPoint(g);
		}
	}
	public List<Point> getListPoint() {
		return listPoint;
	}
	

}
