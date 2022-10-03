package project;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Random;
import java.util.Set;
import java.util.Stack;
import java.util.TreeMap;

public class ReadMatrix {
	String duongDi;
	Map<Point, ArrayList<Integer>> map;
	ListPoint listPoint;
	ListEgde listEgde;
	ListEgde listEgdeBrowse;
	ArrayList<Boolean> chuaXet;
	ArrayList<ArrayList<Point>> ke;

	// dijkstra
	boolean[] chuaDuyetDijkstra;
	ArrayList<Point> R;
	ArrayList<Integer> L;
	ArrayList<Point> P;
	int min;

	public ReadMatrix() {
		listEgde = new ListEgde();
		listEgdeBrowse = new ListEgde();
		map = new TreeMap<Point, ArrayList<Integer>>();
		listPoint = new ListPoint();
		listEgde = new ListEgde();
		ke = new ArrayList<ArrayList<Point>>();
	}

	public void DijkstraInit() {
		int count = 0;
		chuaDuyetDijkstra = new boolean[map.size()];
		R = new ArrayList<Point>();
		L = new ArrayList<Integer>();
		P = new ArrayList<Point>();
		Set<Point> set = map.keySet();
		for (Point point : set) {
			R.add(point);
			L.add(-1);
			chuaDuyetDijkstra[count++] = true;
			P.add(null);
		}
	}

	public int getIndexOfMin(ArrayList<Integer> array) {
		for (int i = 0; i < array.size(); i++) {
			if (array.get(i) != -1 && chuaDuyetDijkstra[i] == true) {
				min = i;
				break;
			}
		}
		for (int i = 0; i < array.size(); i++) {
			if (array.get(i) < array.get(min) && array.get(i) != -1 && chuaDuyetDijkstra[i] == true) {
				min = i;
			}
		}
		return min;
	}

	public void chuaXetInit() {
		chuaXet = new ArrayList<Boolean>();
		for (int i = 0; i < map.size(); i++) {
			chuaXet.add(true);
		}
	}

	public boolean ke(Point p1, Point p2) {
		if (map.get(p1).get(p2.getNumber()) != 0 && map.get(p2).get(p1.getNumber()) != 0) {
			return true;
		}
		return false;
	}

	public void danhSachKeInit() {
		for (int i = 0; i < map.size(); i++) {
			ke.add(new ArrayList<Point>());
		}
		Set<Point> set = map.keySet();
		for (Point point : set) {
			for (Point point2 : set) {
				if (ke(point, point2)) {
					ke.get(point.getNumber()).add(point2);
				}
			}
		}
	}

	public void ThemPoint(Point point) {
		if (map.size() == 0) {
			map.put(point, new ArrayList<Integer>());
			map.get(point).add(0);
		} else {
			Set<Point> set = map.keySet();
			for (Point point2 : set) {
				map.get(point2).add(0);
			}
			ArrayList<Integer> array = new ArrayList<Integer>();
			for (int i = 0; i < map.size() + 1; i++) {
				array.add(0);
			}
			map.put(point, array);
		}
		listPointInit();
//		System.out.println(map);
	}

// cái này sai với Jframe
	public void themCanh(int p1, int p2, int weight) {
		if (p1 == p2) {
		} else {
			map.get(getPointFromIndex(p1)).set(p2, weight);
			map.get(getPointFromIndex(p2)).set(p1, weight);
		}
		listEgdeInit();
	}

	public void printMap() {
		Set<Point> set = map.keySet();
		System.out.print("   ");
		printSet(set);
		for (Point point : set) {
			System.out.print(point);
			System.out.println(map.get(point));
		}
	}

	public void printSet(Set set) {
		for (Object object : set) {
			System.out.print(object);
		}
		System.out.println();
	}

	public void listPointInit() {
		listPoint = new ListPoint();
		Set<Point> set = map.keySet();
		for (Point point : set) {
			listPoint.addPoint(point);
		}
//		System.out.println(set);
	}

	public void listEgdeInit() {
		listEgde = new ListEgde();
		Set<Point> set = map.keySet();
		for (Point point : set) {
			for (int i = 0; i < map.get(point).size(); i++) {
				if (map.get(point).get(i) != 0) {
					Egde e = new Egde(point, getPointFromIndex(i), map.get(point).get(i));
					if (!listEgde.isContain(e)) {
						listEgde.addEgde(e);
					}
				}
			}
		}
	}

	public Point getPointFromIndex(int i) {
		int count = 0;
		Set<Point> set = map.keySet();
		for (Point point : set) {
			if (count == i) {
				return point;
			}
			count++;
		}
		return null;
	}

	public Egde getEgdeFromPoint(int p1, int p2) {
		for (int i = 0; i < listEgde.getListEgde().size(); i++) {
			if (listEgde.getListEgde().get(i).getP1().getNumber() == p1
					&& listEgde.getListEgde().get(i).getP2().getNumber() == p2
					|| listEgde.getListEgde().get(i).getP1().getNumber() == p2
							&& listEgde.getListEgde().get(i).getP2().getNumber() == p1) {
				return listEgde.getListEgde().get(i);
			}
		}
		return null;
	}

	public void BFS(int i) {
		duongDi = "Đường Duyệt:   ";
		danhSachKeInit();
		chuaXetInit();
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.add(i);
		duongDi += i + " ";
		chuaXet.set(i, false);
		while (!queue.isEmpty()) {
			int p = queue.poll();
			for (int j = 0; j < ke.get(p).size(); j++) {
				if (chuaXet.get(ke.get(p).get(j).getNumber())) {
					queue.add(ke.get(p).get(j).getNumber());
					if (!listEgdeBrowse.isContain(getEgdeFromPoint(p, ke.get(p).get(j).getNumber()))
							&& getEgdeFromPoint(p, ke.get(p).get(j).getNumber()) != null) {
						listEgdeBrowse.addEgde(getEgdeFromPoint(p, ke.get(p).get(j).getNumber()));
					}
					chuaXet.set(ke.get(p).get(j).getNumber(), false);
					duongDi += ke.get(p).get(j).getNumber() + " ";
				}
			}
		}

	}

	public void DFS(int i) {
		duongDi = "Đường Duyệt:   ";
		int[] truoc = new int[map.size()];
		truoc[i] = -1;
		danhSachKeInit();
		chuaXetInit();
		Stack<Integer> stack = new Stack<Integer>();
		stack.push(i);
		OUTER: while (!stack.isEmpty()) {
			int p = stack.pop();
			if (chuaXet.get(p) == false) {
				while (chuaXet.get(p) == false) {
					if (!stack.isEmpty()) {
						p = stack.pop();
					}
					if (stack.isEmpty()) {
						break OUTER;
					}
				}
			}
			chuaXet.set(p, false);
			duongDi += p + " ";
			if (truoc[p] != -1 && !listEgdeBrowse.isContain(getEgdeFromPoint(p, truoc[p]))
					&& getEgdeFromPoint(p, truoc[p]) != null) {
				listEgdeBrowse.addEgde(getEgdeFromPoint(p, truoc[p]));
			}
			for (int j = 0; j < ke.get(p).size(); j++) {
				if (chuaXet.get(ke.get(p).get(j).getNumber())) {
					stack.push(ke.get(p).get(j).getNumber());
					truoc[ke.get(p).get(j).getNumber()] = p;
//					System.out.println(p + " " + truoc[p]);
				}
			}
		}
	}

	public boolean checkAllFalse(ArrayList<Boolean> array) {
		for (int i = 0; i < array.size(); i++) {
			if (array.get(i) == true) {
				return false;
			}
		}
		return true;
	}

	public void printL() {
		for (int i = 0; i < L.size(); i++) {
			if (L.get(i) == -1) {
				System.out.print("  " + "X  " + "  ");
			} else {
				System.out.print("  " + L.get(i) + "    ");
			}
		}
		System.out.println();
	}

	public void printR() {
		for (int i = 0; i < R.size(); i++) {
			System.out.print("  " + R.get(i) + "  ");
		}
		System.out.println();
	}

	public void printP() {
		for (int i = 0; i < P.size(); i++) {
			if (P.get(i) == null) {
				System.out.print("  " + "X  " + "  ");
			} else {
				System.out.print("  " + P.get(i) + "  ");
			}
		}
		System.out.println();
	}

	public void printChuaDuyet() {
		for (int i = 0; i < chuaDuyetDijkstra.length; i++) {
			if (chuaDuyetDijkstra[i] == true) {
				System.out.print(" true" + "  ");
			} else {
				System.out.print(chuaDuyetDijkstra[i] + "  ");
			}
		}
		System.out.println();
	}

	public boolean chuaDuyetDijkstraXong(boolean[] chuaDuyetDijkstra) {
		boolean check = true;
		for (int i = 0; i < chuaDuyetDijkstra.length; i++) {
			if (chuaDuyetDijkstra[i] == true) {
				check = false;
			}
		}
		return check;
	}

	public void Dijkstra(int a, int b) {
		ArrayList<String> duongDiNguoc = new ArrayList<String>();
		duongDi = "Đường Duyệt:   ";
		int count = 1;
		System.out.println();
		danhSachKeInit();
		DijkstraInit();
		L.set(a, 0);
		while (chuaDuyetDijkstraXong(chuaDuyetDijkstra) == false && count <= listPoint.listPoint.size()) {
			int minLenght = getIndexOfMin(L);
			Point p = getPointFromIndex(minLenght);
			chuaDuyetDijkstra[minLenght] = false;
			for (int i = 0; i < ke.get(minLenght).size(); i++) {
				int minWeight = ke.get(minLenght).get(i).getNumber();
				if (L.get(minWeight) == -1) {
					L.set(minWeight, getEgdeFromPoint(minWeight, minLenght).getWeight() + L.get(minLenght));
					P.set(minWeight, p);
				}
				if (L.get(minWeight) > L.get(minLenght) + getEgdeFromPoint(minWeight, minLenght).getWeight()
						&& L.get(minWeight) != -1 && chuaDuyetDijkstra[minWeight] == true) {
					L.set(minWeight, L.get(minLenght) + getEgdeFromPoint(minWeight, minLenght).getWeight());
					P.set(minWeight, p);
				}
			}
			System.out.println("vong lap: " + count);
			count++;
			printChuaDuyet();
			printR();
			printL();
			printP();
//			System.out.println(L.get(b));
			System.out.println();
			System.out.println("-------------------------------------------------------");
			System.out.println();
		}
		
			Point pc = getPointFromIndex(b);
			while (!pc.equals(getPointFromIndex(a))) {
				duongDiNguoc.add(String.valueOf(pc.getNumber()));
				if (!listEgdeBrowse.isContain(getEgdeFromPoint(pc.getNumber(), P.get(pc.getNumber()).getNumber()))) {
					listEgdeBrowse.addEgde(getEgdeFromPoint(pc.getNumber(), P.get(pc.getNumber()).getNumber()));
				}
				pc = getPointFromIndex(P.get(pc.getNumber()).getNumber());
			}
			duongDiNguoc.add(String.valueOf(a));
			for (int i = duongDiNguoc.size() - 1; i >= 0; i--) {
				duongDi += duongDiNguoc.get(i) + " ";
			}

			duongDi += "| Độ dài: " + L.get(b);
		
	}

	public static void main(String[] args) {
		ReadMatrix r = new ReadMatrix();
		Point p1 = new Point(5, 5, 0);
		Point p2 = new Point(5, 5, 1);
		r.ThemPoint(p1);
		r.ThemPoint(p2);
		r.ThemPoint(new Point(5, 5, 2));
		r.ThemPoint(new Point(5, 5, 3));
		r.ThemPoint(new Point(5, 5, 4));
		r.ThemPoint(new Point(5, 5, 5));
		r.ThemPoint(new Point(5, 5, 6));
		r.ThemPoint(new Point(5, 5, 7));
		r.themCanh(0, 1, 2);
		r.themCanh(0, 5, 1);
		r.themCanh(1, 2, 2);
		r.themCanh(1, 3, 2);
		r.themCanh(1, 4, 4);
		r.themCanh(2, 7, 1);
		r.themCanh(2, 4, 3);
		r.themCanh(3, 4, 4);
		r.themCanh(3, 5, 3);
		r.themCanh(4, 6, 1);
		r.themCanh(5, 6, 7);
		r.themCanh(6, 7, 6);
		r.printMap();
		r.Dijkstra(0, 7);

		// test DFS
//		r.ThemPoint(new Point(5, 5, 0));
//		r.ThemPoint(new Point(5, 5, 1));
//		r.ThemPoint(new Point(5, 5, 2));
//		r.ThemPoint(new Point(5, 5, 3));
//		r.ThemPoint(new Point(5, 5, 4));
//		r.themCanh(0, 1, 1);
//		r.themCanh(0, 2, 1);
//		r.themCanh(1, 2, 1);
//		r.themCanh(1, 3, 1);
//		r.themCanh(4, 2, 1);
//		r.themCanh(4, 3, 1);
//		r.DFS(1);

		////////////////////////////////
//		System.out.println(r.duongDi);
//		System.out.println(r.listEgdeBrowse);
//		System.out.println(r.ke(p1, p2));
//		r.danhSachKe();
//		r.listPointInit();
//		r.listEgdeInit();

//		ArrayList<Integer> array = new ArrayList<Integer>();
//		array.add(-1);
//		array.add(5);
//		array.add(3);
//		array.add(-1);
//		array.add(1);
//		array.add(7);
//		array.add(-1);
//		System.out.println(r.getIndexOfMin(array));
	}

}
