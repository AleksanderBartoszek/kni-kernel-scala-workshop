package basics.interop;

/**
 * Scala interfaces can be implemented in Java.
 */
public class Cat implements Pet {

	private String name;
	private int age;

	public Cat(String name, int age) {
		this.name = name;
		this.age = age;
	}

	public String kind() {
		return "Cat";
	}

	public String getName() {
		return name;
	}

	public int getAge() {
		return age;
	}

	public String speak() {
		return "Meow! Meow!";
	}
}