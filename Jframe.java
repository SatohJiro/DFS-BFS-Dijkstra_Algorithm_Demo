package project;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.Iterator;

public class Jframe extends JFrame implements ActionListener {
	private JLabel jlbThemCanh, jlbThemDinh, jlbPadding, jlbMargin, jlbPadingHead, jlbDiChuyen, jlbDuyet, jlbWeight;
	private JButton jbtThemCanh, jbtThemDinh, jbtDiChuyen, jbtDuyet, jbtDuyetDijikstra, jbtClear;
	DefaultComboBoxModel firstPoint = new DefaultComboBoxModel();
	DefaultComboBoxModel SecondPoint = new DefaultComboBoxModel();
	DefaultComboBoxModel duyet = new DefaultComboBoxModel();
	DefaultComboBoxModel pointBrowse = new DefaultComboBoxModel();
	DefaultComboBoxModel weightEgde = new DefaultComboBoxModel();
	JComboBox firstPointCombo, secondPointCombo, duyetCombo, pointBrowseCombo, weightEgdeCombo, firstPointComboDijkstra,
			secondPointComboDijkstra;

	private JMenuBar menuBar;
	private JMenu file;
	private JMenuItem nw, exit;

	PaintPanel paintPanel;
	Graphics g;

	int countForAddPoint = 0;

	public Jframe() {

		/////////////////////////////////////////////// menu
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		menuBar.add(file = new JMenu("File"));
		file.setMnemonic('F');
//		file.add(nw = new JMenuItem("New"));
//		nw.addActionListener(this);
//		nw.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
//		file.addSeparator();
		file.add(exit = new JMenuItem("Exit"));
		exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, ActionEvent.CTRL_MASK));
		exit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		/////////////////////////////////////// p1
		Dimension dimeTextFiled = new Dimension(50, 25);
		Dimension dimeLabel = new Dimension(65, 25);
		Dimension dimeButton = new Dimension(28, 26);
		Dimension dimeLabelHead = new Dimension(32, 25);
		jlbMargin = new JLabel("-----------------------------------------");
		jlbMargin.setPreferredSize(new Dimension(170, 25));
		jlbPadding = new JLabel();
		jlbPadding.setPreferredSize(dimeLabel);

		JPanel p1 = new JPanel();
		p1.setPreferredSize(new Dimension(190, 600));

		p1.add(jlbThemDinh = new JLabel("Thêm Đỉnh"));
		jlbThemDinh.setPreferredSize(dimeLabel);
		p1.add(jbtThemDinh = new JButton(new ImageIcon(getClass().getResource("add.jpg"))));
		jbtThemDinh.setHorizontalAlignment(SwingConstants.CENTER);
		jbtThemDinh.setPreferredSize(dimeButton);
		jbtThemDinh.addActionListener(this);

		p1.add(jlbMargin = new JLabel("-----------------------------------------"));
		jlbThemCanh = new JLabel("Thêm Cạnh");
		p1.add(jlbPadingHead = new JLabel());
		jlbPadingHead.setPreferredSize(dimeLabelHead);
		p1.add(jlbThemCanh);
		p1.add(jlbPadding);
		p1.add(jlbPadding = new JLabel());
		jlbPadding.setPreferredSize(new Dimension(15, 25));
		p1.add(new JLabel("P1"));
		p1.add(jlbPadding = new JLabel());
		jlbPadding.setPreferredSize(new Dimension(20, 25));
		p1.add(new JLabel("P2"));
		p1.add(jlbPadding = new JLabel());
		jlbPadding.setPreferredSize(new Dimension(20, 25));
		p1.add(new JLabel("W"));
		p1.add(jlbPadding = new JLabel());
		jlbPadding.setPreferredSize(new Dimension(50, 25));

		firstPointCombo = new JComboBox(firstPoint);
		firstPointCombo.addActionListener(this);
		secondPointCombo = new JComboBox(SecondPoint);
		secondPointCombo.addActionListener(this);
		weightEgdeCombo = new JComboBox<>(weightEgde);
		for (int i = 1; i < 31; i++) {
			weightEgde.addElement(i);
		}
		weightEgdeCombo.addActionListener(this);
		JScrollPane firstListScrollPane = new JScrollPane(firstPointCombo);
		JScrollPane secondListScrollPane = new JScrollPane(secondPointCombo);
		JScrollPane weightListScrollPaane = new JScrollPane(weightEgdeCombo);
		jbtThemCanh = new JButton(new ImageIcon(getClass().getResource("connect1.png")));
		jbtThemCanh.setPreferredSize(dimeButton);
		jbtThemCanh.addActionListener(this);
		p1.add(firstListScrollPane);
		p1.add(secondListScrollPane);
		p1.add(weightListScrollPaane);
		p1.add(jbtThemCanh);

		p1.add(jlbMargin = new JLabel("-----------------------------------------"));
		p1.add(jlbDiChuyen = new JLabel("Di Chuyển"));
		jlbDiChuyen.setPreferredSize(dimeLabel);
		p1.add(jbtDiChuyen = new JButton(new ImageIcon(getClass().getResource("move.png"))));
		jbtDiChuyen.addActionListener(this);
		jbtDiChuyen.setPreferredSize(dimeButton);

		p1.add(jlbMargin = new JLabel("-----------------------------------------"));
		p1.add(jlbDuyet = new JLabel("algorithm browse"));
		duyet.addElement("Duyệt DFS");
		duyet.addElement("Duyệt BFS");
		duyetCombo = new JComboBox<>(duyet);
		JScrollPane duyetScrollPane = new JScrollPane(duyetCombo);
		p1.add(duyetScrollPane);

		pointBrowseCombo = new JComboBox<>(pointBrowse);
		JScrollPane pointBrowseScrollPane = new JScrollPane(pointBrowseCombo);
		p1.add(pointBrowseScrollPane);
		p1.add(jbtDuyet = new JButton(new ImageIcon(getClass().getResource("duyet1.jpg"))));
		jbtDuyet.setPreferredSize(dimeButton);
		jbtDuyet.addActionListener(this);

		///////////////////////////////
		p1.add(jlbMargin = new JLabel("-----------------------------------------"));
		p1.add(jlbPadingHead = new JLabel());
		jlbPadingHead.setPreferredSize(new Dimension(20, 25));
		p1.add(jlbDuyet = new JLabel("Find The Shortest Path"));
		p1.add(new JLabel("   "));
		p1.add(jlbDuyet = new JLabel("Dijkstra"));
		add(p1, BorderLayout.WEST);
		firstPointComboDijkstra = new JComboBox(firstPoint);
		firstPointComboDijkstra.addActionListener(this);
		secondPointComboDijkstra = new JComboBox(SecondPoint);
		secondPointComboDijkstra.addActionListener(this);
		JScrollPane firstListScrollPaneDijikstra = new JScrollPane(firstPointComboDijkstra);
		JScrollPane secondListScrollPaneDijikstra = new JScrollPane(secondPointComboDijkstra);
		p1.add(firstListScrollPaneDijikstra);
		p1.add(secondListScrollPaneDijikstra);
		p1.add(jbtDuyetDijikstra = new JButton(new ImageIcon(getClass().getResource("kinhlup.jpg"))));
		jbtDuyetDijikstra.setPreferredSize(dimeButton);
		jbtDuyetDijikstra.addActionListener(this);
		p1.add(jlbMargin = new JLabel("-----------------------------------------"));
		p1.add(jbtClear = new JButton("Clear Algorithm"));
		jbtClear.setEnabled(false);
		jbtClear.addActionListener(this);

//		jbtThemDinh.setPreferredSize(dimeButton);

		// p2
		paintPanel = new PaintPanel();
		g = paintPanel.getGraphics();
		paintPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		getContentPane().add(paintPanel, BorderLayout.CENTER);
		repaint();

		setTitle("Semina Project");
		setSize(800, 600);
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(Jframe.EXIT_ON_CLOSE);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == jbtThemDinh) {
			jbtThemCanh.setEnabled(false);
			jbtDuyet.setEnabled(false);
			jbtDuyetDijikstra.setEnabled(false);
			countForAddPoint++;
			jbtDiChuyen.setIcon(new ImageIcon(getClass().getResource("move1.png")));
			jbtDiChuyen.setEnabled(false);
			jbtThemDinh.setIcon(new ImageIcon(getClass().getResource("add1.png")));
			paintPanel.setType(paintPanel.THEM_DINH);
			pointBrowse.removeAllElements();
			if (firstPoint.getSize() > 0) {
				firstPoint.removeAllElements();
			}
			if (countForAddPoint % 2 == 0) {
				countForAddPoint = 0;
				jbtDiChuyen.setIcon(new ImageIcon(getClass().getResource("move.png")));
				jbtDiChuyen.setEnabled(true);
				jbtThemDinh.setIcon(new ImageIcon(getClass().getResource("add.jpg")));
				jbtThemCanh.setEnabled(true);
				jbtDuyet.setEnabled(true);
				jbtDuyetDijikstra.setEnabled(true);
				for (int i = 0; i < paintPanel.matrix.listPoint.getListPoint().size(); i++) {
					firstPoint.addElement(i);
					pointBrowse.addElement(i);
//					SecondPoint.addElement(i);
					validate();
				}
				paintPanel.setType(0);
			}

		}
		if (e.getSource() == firstPointCombo) {
			if (SecondPoint.getSize() > 0) {
				SecondPoint.removeAllElements();
			}
			for (int i = 0; i < paintPanel.matrix.listPoint.getListPoint().size(); i++) {
				SecondPoint.addElement(i);
				validate();
			}
			if (firstPointCombo.getSelectedIndex() != -1) {
				SecondPoint.removeElementAt(firstPointCombo.getSelectedIndex());
			}
			validate();
		}
		if (e.getSource() == jbtThemCanh) {

			paintPanel.setType(paintPanel.THEM_CANH);
			paintPanel.matrix.themCanh((int) firstPointCombo.getSelectedItem(),
					(int) secondPointCombo.getSelectedItem(), (int) weightEgdeCombo.getSelectedItem());
		}
		if (e.getSource() == jbtDiChuyen) {
			countForAddPoint++;
			jbtDiChuyen.setIcon(new ImageIcon(getClass().getResource("move1.png")));
			jbtThemDinh.setEnabled(false);
			jbtThemDinh.setIcon(new ImageIcon(getClass().getResource("add1.png")));
			paintPanel.setType(paintPanel.DI_CHUYEN);
			if (countForAddPoint % 2 == 0) {
				countForAddPoint = 0;
				jbtDiChuyen.setIcon(new ImageIcon(getClass().getResource("move.png")));
				jbtThemDinh.setEnabled(true);
				jbtThemDinh.setIcon(new ImageIcon(getClass().getResource("add.jpg")));
				paintPanel.setType(0);
			}
			validate();
		}
		if (e.getSource() == jbtDuyet) {
			jbtClear.setEnabled(true);
			paintPanel.matrix.listEgdeBrowse.getListEgde().removeAll(paintPanel.matrix.listEgdeBrowse.getListEgde());
			if (String.valueOf(duyetCombo.getSelectedItem()).equals("Duyệt BFS")) {
				paintPanel.matrix.BFS((int) pointBrowseCombo.getSelectedItem());
				paintPanel.isDuyet = true;
				paintPanel.jlbDuyet.setText("BFS ||");
				paintPanel.jlbDuongDi.setText(paintPanel.matrix.duongDi);
				repaint();
			}
			if (String.valueOf(duyetCombo.getSelectedItem()).equals("Duyệt DFS")) {
				paintPanel.matrix.DFS((int) pointBrowseCombo.getSelectedItem());
				paintPanel.isDuyet = true;
				paintPanel.jlbDuyet.setText("DFS ||");
				paintPanel.jlbDuongDi.setText(paintPanel.matrix.duongDi);
				repaint();
			}
		}
		if (e.getSource() == jbtDuyetDijikstra) {
			jbtClear.setEnabled(true);
			paintPanel.matrix.listEgdeBrowse.getListEgde().removeAll(paintPanel.matrix.listEgdeBrowse.getListEgde());
			paintPanel.matrix.Dijkstra((int) firstPointComboDijkstra.getSelectedItem(),
					(int) secondPointComboDijkstra.getSelectedItem());
			paintPanel.isDuyet = true;
			paintPanel.jlbDuyet.setText("Dijkstra ||");
			paintPanel.jlbDuongDi.setText(paintPanel.matrix.duongDi);
			repaint();
		}
		if (e.getSource() == jbtClear) {
			paintPanel.matrix.listEgdeBrowse.getListEgde().removeAll(paintPanel.matrix.listEgdeBrowse.getListEgde());
			paintPanel.isDuyet = false;
			paintPanel.jlbDuyet.setText("null");
			paintPanel.jlbDuongDi.setText("");
			jbtClear.setEnabled(false);
		}
//		if (e.getSource() == nw) {
//			paintPanel = new PaintPanel();
//			g = paintPanel.getGraphics();
//			paintPanel.setBorder(BorderFactory.createLineBorder(Color.black));
//			getContentPane().add(paintPanel, BorderLayout.CENTER);
//		}
		validate();
		repaint();

	}

	public static void main(String[] args) {
		JFrame jFrame = new Jframe();
	}
}
