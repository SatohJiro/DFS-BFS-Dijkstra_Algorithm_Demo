package project;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class PaintPanel extends JPanel implements MouseMotionListener, MouseListener ,Runnable{
	ReadMatrix matrix;
	public static int THEM_DINH = 1;
	public static int THEM_CANH = 2;
	public static int DUYET_DINH = 3;
	public static int DI_CHUYEN = 4;
	public int TYPE = 0;
	public project.Point point;
	public Egde egde;
	public project.Point p1;
	public project.Point p2;
	public boolean isDuyet= false;
	public JLabel jlbDuyet,jlbDuyetAlgorithm ,jlbDuongDi;

	public PaintPanel() {
		// TODO Auto-generated constructor stub
		matrix = new ReadMatrix();
//		setBackground(Color.white);
		addMouseListener(this);
		addMouseMotionListener(this);
		add(jlbDuyetAlgorithm= new JLabel("Thuật Toán Duyệt: "),BorderLayout.NORTH);
		add(jlbDuyet= new JLabel("null"),BorderLayout.NORTH);
		add(jlbDuongDi= new JLabel(),BorderLayout.CENTER);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		for (int i = 0; i < matrix.listPoint.getListPoint().size(); i++) {
			matrix.listPoint.getListPoint().get(i).drawPoint(g);
		}
		for (int i = 0; i < matrix.listEgde.getListEgde().size(); i++) {
			matrix.listEgde.getListEgde().get(i).draw(g);
		}
		if (isDuyet==true) {
			for (int i = 0; i < matrix.listEgdeBrowse.getListEgde().size(); i++) {
				matrix.listEgdeBrowse.getListEgde().get(i).drawalgorithm(g);
			}
		}else {
			
		}
		validate();
		repaint();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		switch (TYPE) {
		case 1:
			point = new project.Point(e.getX(), e.getY(), matrix.map.size());
			matrix.ThemPoint(point);
			break;
//		case 2:
//			egde = new Egde(getP1(), getP2());
//			matrix.listPoint.addPoint(point);
//			matrix.listEgde.addEgde(egde);
//			break;
		}
		validate();
		repaint();

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		int xm = e.getX();
		int ym = e.getY();

		if (getTYPE() == DI_CHUYEN) {
			for (int i = 0; i < matrix.listPoint.getListPoint().size(); i++) {
				if (matrix.listPoint.getListPoint().get(i).isContain(xm + 2, ym + 2)) {
					matrix.listPoint.getListPoint().get(i).setX(e.getX() - 5);
					matrix.listPoint.getListPoint().get(i).setY(e.getY() - 5);
					validate();
					repaint();
				}

			}
		}

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseDragged(MouseEvent e) {
		int xm = e.getX();
		int ym = e.getY();
		// TODO Auto-generated method stub
		if (getTYPE() == DI_CHUYEN) {
			for (int i = 0; i < matrix.listPoint.getListPoint().size(); i++) {
				int tempx = e.getX() - 5 - matrix.listPoint.getListPoint().get(i).getX();
				int tempy = e.getY() - 5 - matrix.listPoint.getListPoint().get(i).getY();
				for (int j = 0; j < matrix.listPoint.getListPoint().size(); j++) {

					if (i != j) {
						if (matrix.listPoint.getListPoint().get(i).isContain(xm + 2, ym + 2)) {
							matrix.listPoint.getListPoint().get(i).setX(e.getX() - 5);
							matrix.listPoint.getListPoint().get(i).setY(e.getY() - 5);
							matrix.listPoint.getListPoint().get(i)
									.collisionPoint(matrix.listPoint.getListPoint().get(j), tempx, tempy);
							validate();
							repaint();
						}
					}
				}

			}
		}

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method

	}

	public void setType(int a) {
		this.TYPE = a;

	}

	public int getTYPE() {
		return TYPE;
	}

	public void setTYPE(int tYPE) {
		TYPE = tYPE;
	}

	public project.Point getP1() {
		return p1;
	}

	public void setP1(project.Point p1) {
		this.p1 = p1;
	}

	public project.Point getP2() {
		return p2;
	}

	public void setP2(project.Point p2) {
		this.p2 = p2;
	}

	public void setEgde(Egde egde) {
		this.egde = egde;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

}
