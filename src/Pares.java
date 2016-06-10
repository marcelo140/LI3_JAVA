public class Pares<K,V> implements Serializable{
    private K first;
	private V second;

    public Pares(K first, V second) {
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
