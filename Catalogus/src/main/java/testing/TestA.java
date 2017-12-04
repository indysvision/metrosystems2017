package testing;

public class TestA {
	int a;
	int b;
	boolean c;
	String name;
	
	public TestA name(String name) {
		this.name = name;
		return this;
	}
	
	public TestA add(int a) {
		this.a = a;
		return this;
	}
	
	public TestA buy(int b) {
		this.b = b;
		return this;
	}

	public TestA cancel(boolean c) {
		this.c = c;
		return this;
	}
	
}
