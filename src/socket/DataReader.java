package socket;

import java.util.Set;
import person.Person;

public abstract class DataReader {

	private String searchCriteria;
	SearchType searchType;
	
	public abstract Set<Person> getPersons(String searchCriteria, SearchType searchType);
	
	public void setSearchCriteria(String searchCriteria) {
		this.searchCriteria = searchCriteria;
	}
	public void setSearchType(SearchType searchType) {
		this.searchType = searchType;
	}
	
	
}
