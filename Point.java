package project;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

public class Point implements Comparable<Point> {
	private int number;
	private int x;
	private int y;
	private int r;

	public Point(int x, int y, int number) {
		super();
		this.x = x;
		this.y = y;
		this.number = number;
		r = 10;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getR() {
		return r;
	}

	public int getNumber() {
		return number;
	}

	public void setR(int r) {
		this.r = r;
	}

	public void drawPoint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
//		g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		g2.setStroke(new BasicStroke(2));
		g2.drawOval(x, y, r * 2, r * 2);
		if (number >= 10) {
			g2.drawString("" + this.number, x + r - 6, y + r + 4);

		} else {
			g2.drawString("" + this.number, x + r - 3, y + r + 4);
		}
	}

	public boolean isContain(int x, int y) {
		if (x >= getX() && x <= (getX() + 2 * getR()) && y >= getY() && y <= (getY() + 2 * getR())) {
			return true;
		}
		return false;
	}

	public boolean collisionPoint(Point other, int x, int y) {
		int px = getX();
		int py = getY();
		int pr = getR();

		int ox = other.getX();
		int oy = other.getY();
		int or = other.getR();

		int dx = px - ox;
		int dy = py - oy;
		int distance = (int) Math.sqrt(dx * dx + dy * dy);
		if (distance < pr + or) {
//			if () {
//				
//			}
			other.setX(other.getX() + x);
			other.setY(other.getY() + y);
			return true;
		}
		return false;
	}
	

	

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Point other = (Point) obj;
		if (number != other.number)
			return false;
		if (r != other.r)
			return false;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}

	@Override
	public int compareTo(Point o) {
		// TODO Auto-generated method stub
		return Integer.compare(number, o.number);
	}

	@Override
	public String toString() {
		return "P"+number+" ";
	}
	

}
