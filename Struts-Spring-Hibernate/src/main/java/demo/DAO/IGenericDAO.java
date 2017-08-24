package demo.DAO;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.Criterion;

public interface IGenericDAO<E, Id extends Serializable> extends Serializable {
	public E findById(Id id);

	public E findById(Id id, boolean lock);

	public List<E> findAll();

	public List<E> findByExample(E exampleInstance, String[] excludeProperty);

	public int count(E exampleInstance, String[] excludeProperty, boolean isLike);

	public Long count();

	public int count(Criterion... criterion);

	public E save(E entity);

	public void delete(E entity);

	public List<E> findByCriteria(Criterion... criterion);

	public List<E> list(Integer offset, Integer maxResults);
}
