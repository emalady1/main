@author Ellie Malady
public class Movie<KeyType, ValueType> {

	private KeyType name;
	private ValueType eidr;

	/**
	 * creates an object of type movie
	 * 
	 * @param givenName  user inputted name of movie
	 * @param givenEidr  user inputted EIDR identification number
	 * @param givenGenre user inputted genre
	 */
	public Movie(KeyType givenName, ValueType givenEidr) {
		name = givenName;
		eidr = givenEidr;

	}

	/**
	 * returns name
	 * 
	 * @return name of movie object
	 */
	public KeyType getName() {
		return name;

	}

	/**
	 * returns eidr
	 * 
	 * @return eidr code of movie object
	 */
	public ValueType getEidr() {
		return eidr;

	}

	/**
	 * sets name of movie object with new name
	 * 
	 * @param newName name to give to movie object
	 */
	public void setName(KeyType newName) {
		name = newName;
	}

	/**
	 * sets EIDR of movie object with new Eidr code
	 * 
	 * @param newEidr code to give to movie object
	 */
	public void setEidr(ValueType newEidr) {
		eidr = newEidr;
	}

}
