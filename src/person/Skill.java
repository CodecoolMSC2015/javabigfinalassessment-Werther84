package person;

public class Skill {
	
	private String name;
	private String description;
	private double rate;
	
	public Skill(String name, String description, double rate) {
		this.name = name;
		this.description = description;
		this.rate = rate;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public double getRate() {
		return rate;
	}	
}
