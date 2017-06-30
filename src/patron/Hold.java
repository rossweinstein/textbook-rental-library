package patron;

import java.util.ArrayList;
import java.util.List;

public class Hold {
	
	List<Copy> currentHolds;
	
	public Hold() {
		this.currentHolds = new ArrayList<>();
	}
	
	public List<Copy> getHolds() {
		return this.currentHolds;
	}
	
	public boolean hasNoHolds() {
		return this.currentHolds.isEmpty();
	}
	
	public void addHold(Copy copy) {
		this.currentHolds.add(copy);
	}
	
	public void removeHold(Copy copy) {
		this.currentHolds.remove(copy);
	}
}
