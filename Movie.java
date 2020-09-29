/**
 * 
 * @author ellie malady
 *
 * @param <KeyType>
 * @param <ValueType>
 */
public class Movie<KeyType, ValueType> {
	private KeyType name;
	private ValueType eidr;
	private int id;

	/**
	 * creates an object of type movie
	 * 
	 * @param givenName  user inputed name of movie
	 * @param givenEidr  user inputed EIDR identification number
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
	 * returns id
	 * 
	 * @return id code of movie object
	 */
	public int getId() {
		return id;

	}
	
	/**
	 * sets id variable to new id
	 * 
	 * @param new id code of movie object
	 */
	public void setId(int newId) {
		id = newId;

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
