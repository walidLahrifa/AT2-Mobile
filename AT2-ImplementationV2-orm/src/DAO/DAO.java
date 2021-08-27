/**
 * Author :           @ENNIARI
 * Creation Date :    11/08/2021
 * Last Update Date : 11/08/2021
 * Description :      DAO Interface Class for all Database Tables
 * 
 */
package DAO;

import java.util.List;
import java.util.Optional;

public interface DAO<T> {

	/* Get Object by Id from database */
	Optional<T> getbyid(int id);

	/* Get All Objects from database */
	List<T> getAll();

	/* Save a Object into a database */
	void save(T t);

	/* Update an Object into a database */
	void update(T t, String[] params);

	/* Delete an Object from a database */
	void delete(T t);

	T getbyidentifier(String identifier);

}