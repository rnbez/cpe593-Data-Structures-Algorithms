
public class BetterGrowableArray {
	private int[] p;
	private int size, head, tail;

	public BetterGrowableArray() { // O(n)
		size = 1;
		p = new int[size]; // O(n)
		head = tail = 0;
	}

	public BetterGrowableArray(int size) { // O(n)
		this.size = size;
		p = new int[size]; // O(n)
		head = tail = 0;
	}

	private void growRight() { // O(n)
		size += used();
		int[] temp = p;
		p = new int[size];
		for (int i = head + 1; i < tail; i++)
			p[i] = temp[i];
	}

	private void growLeft() { // O(n)
		size += used();
		int[] temp = p;
		p = new int[size];
		int offset = p.length - temp.length;
		for (int i = 0; i < tail; i++)
			p[offset + i] = temp[i];

		head = offset - 1;
		tail += offset;
	}

	public void addBack(int v) { // O(log n)
		if (head == tail)
			head--;
		if (tail == p.length)
			growRight();

		p[tail++] = v;

	}

	public void addFront(int v) { // O(n log n)
		if (head == tail)
			tail++;
		if (head == -1)
			growLeft();

		p[head--] = v;
	}

	public void removeFront() {
		if (used() > 0)
			p[++head] = 0;
	}

	public void removeBack() {// O(1)
		if (used() > 0)
			p[--tail] = 0;
	}

	public int size() { // O(1)
		return size;
	}

	public int used() { // O(1)
		return (tail - head) - 1;
	}

	public int get(int i) { // O(1)
		if (i < 0 || i >= used())
			throw new IllegalArgumentException("Index out of bounds: " + i);
		int offset = 0;
		return p[head + 1 + i];
	}

	void set(int i, int v) { // O(1)
		if (i < 0 || i >= used())
			throw new IllegalArgumentException("Index out of bounds: " + i);
		int offset = 0;
		p[offset + i] = v;
	}

}