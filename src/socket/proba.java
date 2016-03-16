package socket;

public class proba {

	public static void main(String[] args) {
		CSVDataReader csvr = new CSVDataReader(System.getProperty("user.dir")+"\\src\\socket\\persons.csv");
		csvr.getPersons("Java", SearchType.Optional);
	}

}
