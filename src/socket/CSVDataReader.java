package socket;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

import person.Person;

public class CSVDataReader extends DataReader {

	private String csvFilePath;
	private Set<Person> persons;
	
	CSVDataReader(String csvFilePath) {
		this.csvFilePath = csvFilePath;
	}

	@Override
	public Set<Person> getPersons(String searchCriteria, SearchType searchType) {
		try {
			FileReader fr = new FileReader(csvFilePath);
			BufferedReader br = new BufferedReader(fr);
			String csvLine;
			
			while ((csvLine = br.readLine()) != null) {
				StringTokenizer st = new StringTokenizer(csvLine, ",", true);
				boolean previousWasComma = true;
				List<String> personData = new ArrayList<>();
			
				while (st.countTokens() != 0) {
					String oneToken = st.nextToken();
					
					if (st.countTokens() == 0 && !oneToken.equals(",")) {
						System.out.println("Employed");
						personData.add(oneToken);
					} else if (previousWasComma && oneToken.equals(",")) {
						System.out.println("No data");
						personData.add("");
					} else if (previousWasComma && !oneToken.equals(",")) {
						System.out.println(oneToken);
						personData.add(oneToken);
						previousWasComma = false;
					} 
				
					if (oneToken.equals(",")) {
						previousWasComma = true;
					}
				}
				
				List<Person> personList = new ArrayList<>();
				int i = 0;
				for (Person onePerson : personList) {
					if (onePerson.getName().equals(personData.get(0))) {
						
					}
					i++;
				}
				personList.add(new Person(personData.get(0), personData.get(1)));
				
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return persons;
	}
}