import java.io.Serializable;

public class Par<K,V> implements Serializable{
    private K first;
	private V second;

    public Par(K first, V second) {
    	this.first = first;
		this.second = second;
	}

	public K first() {
		return first;
	}

	public V second() {
		return second;
	}

}
