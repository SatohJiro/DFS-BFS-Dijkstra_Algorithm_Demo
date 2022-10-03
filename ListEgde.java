package project;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ListEgde {
	private List<Egde> listEgde;

	public ListEgde() {
		this.listEgde = new ArrayList<Egde>();
	}

	public List<Egde> getListEgde() {
		return listEgde;
	}

	public void addEgde(Egde i) {
		listEgde.add(i);
	}

	@Override
	public String toString() {
		return "ListEgde [listEgde=" + listEgde + "]";
	}

	public boolean isContain(Egde e) {
		if (e != null) {
			for (int i = 0; i < listEgde.size(); i++) {
				if (this.listEgde.get(i).equals(e)) {
					return true;
				}
			}
		}
		return false;
	}

}
