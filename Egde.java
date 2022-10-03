package project;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.Timer;

public class Egde {
	private Point p1;
	private Point p2;
	private int weight;

	public Egde(Point p1, Point p2, int weight) {
		super();
		this.p1 = p1;
		this.p2 = p2;
		this.weight = weight;
	}

	public Point getP1() {
		return p1;
	}

	public void setP1(Point p1) {
		this.p1 = p1;
	}

	public Point getP2() {
		return p2;
	}

	public void setP2(Point p2) {
		this.p2 = p2;
	}

	public int getWeight() {
		return weight;
	}

	public boolean equals(Egde e) {
		if (p1.equals(e.p1) && p2.equals(e.p2) || p1.equals(e.p2) && p2.equals(e.p1)) {
			return true;
		}
		return false;
//		if (this == obj)
//			return true;
//		if (obj == null)
//			return false;
//		if (getClass() != obj.getClass())
//			return false;
//		Egde other = (Egde) obj;
//		if (p1 == null) {
//			if (other.p1 != null)
//				return false;
//		} else if (!p1.equals(other.p1))
//			return false;
//		if (p2 == null) {
//			if (other.p2 != null)
//				return false;
//		} else if (!p2.equals(other.p2))
//			return false;
//		return true;
	}

	public void draw(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		g2.setStroke(new BasicStroke(2));
		/////////////////////////
		if (p1.getY() - p2.getY() > 92) {
			g2.drawLine(p1.getX() + p1.getR(), p1.getY() - 1, p2.getX() + p2.getR(), p2.getY() + 2 * p2.getR() + 1);
		}
		if (Math.abs(p1.getY() - p2.getY()) <= 92) {
			if (p1.getX() < p2.getX()) {
				g2.drawLine(p1.getX() + p1.getR() * 2, p1.getY() + p1.getR(), p2.getX(), p2.getY() + p2.getR());
			}
			if (p1.getX() > p2.getX()) {
				g2.drawLine(p2.getX() + p2.getR() * 2, p2.getY() + p2.getR(), p1.getX(), p1.getY() + p1.getR());
			}

		}
		if (p2.getY() - p1.getY() > 92) {
			g2.drawLine(p2.getX() + p2.getR(), p2.getY() - 1, p1.getX() + p1.getR(), p1.getY() + 2 * p1.getR() + 1);
		}
		// draw weight
		int x1 = p1.getX();
		int y1 = p1.getY();
		int x2 = p2.getX();
		int y2 = p2.getY();
		int dx = Math.abs(p1.getX() - p2.getX()) / 2;
		int dy = Math.abs(p1.getY() - p2.getY()) / 2;
		if (p1.getX() < p2.getX() && p1.getY() >= p2.getY()) {
			g2.drawString("" + weight, x1 + dx, y1 - dy + 7);
		}
		if (p1.getX() >= p2.getX() && p1.getY() > p2.getY()) {
			g2.drawString("" + weight, x1 - dx+10, y1 - dy);
		}
		if (p1.getX() > p2.getX() && p1.getY() <= p2.getY()) {
			g2.drawString("" + weight, x2 + dx, y1 + dy + 7);
		}
		if (p1.getX() <= p2.getX() && p1.getY() < p2.getY()) {
			g2.drawString("" + weight, x2 - dx+15, y1 + dy);
		}

	}

	public void drawalgorithm(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		g2.setStroke(new BasicStroke(2));
		g2.setColor(Color.red);
		/////////////////////////
		if (p1.getY() - p2.getY() > 92) {
			g2.drawLine(p1.getX() + p1.getR(), p1.getY() - 1, p2.getX() + p2.getR(), p2.getY() + 2 * p2.getR() + 1);
		}
		if (Math.abs(p1.getY() - p2.getY()) <= 92) {
			if (p1.getX() < p2.getX()) {
				g2.drawLine(p1.getX() + p1.getR() * 2, p1.getY() + p1.getR(), p2.getX(), p2.getY() + p2.getR());
			}
			if (p1.getX() > p2.getX()) {
				g2.drawLine(p2.getX() + p2.getR() * 2, p2.getY() + p2.getR(), p1.getX(), p1.getY() + p1.getR());
			}

		}
		if (p2.getY() - p1.getY() > 92) {
			g2.drawLine(p2.getX() + p2.getR(), p2.getY() - 1, p1.getX() + p1.getR(), p1.getY() + 2 * p1.getR() + 1);
		}
	}

	@Override
	public String toString() {
		return "Egde [" + p1 + "," + p2 + ", weight=" + weight + "]";
	}

}
