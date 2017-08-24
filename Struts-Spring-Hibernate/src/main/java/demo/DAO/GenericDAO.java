package demo.DAO;

import java.io.Serializable;
import java.util.List;

import org.hibernate.CacheMode;
import org.hibernate.Criteria;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

public abstract class GenericDAO<E, Id extends Serializable> extends HibernateDaoSupport {

	private Class<E> persistentClass;

	public Class<E> getPersistentClass() {
		return persistentClass;
	}

	public GenericDAO(Class<E> persistentClass, SessionFactory sessionFactory) {
		this.persistentClass = persistentClass;
		this.sessionFactory = sessionFactory;
	}

	public GenericDAO(Class<E> persistentClass) {
		this.persistentClass = persistentClass;
	}

	@Autowired
	private SessionFactory sessionFactory;

	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@SuppressWarnings("unchecked")
	public E findById(Id id) {

		return (E) getSession().get(getPersistentClass(), id);
	}

	@SuppressWarnings({ "unchecked", "deprecation" })
	public E findById(Id id, boolean lock) {
		E entity;

		getSession().setCacheMode(CacheMode.PUT);
		if (lock)
			entity = (E) getSession().get(getPersistentClass(), id, LockMode.UPGRADE);
		else
			entity = (E) getSession().get(getPersistentClass(), id);

		if (entity != null)
			getSession().refresh(entity);
		if (entity == null) {
			getSession().load(getPersistentClass(), id);
		}
		if (entity != null)
			getSession().refresh(entity);

		return entity;
	}

	public List<E> findAll() {
		return findByCriteria();
	}

	@SuppressWarnings("unchecked")
	// exampleInstance la mau Object
	// excludeProperty la mot mang String chua ten cac property ma ta muon
	// dua vao tieu chi tim kiem
	public List<E> findByExample(E exampleInstance, String[] excludeProperty) {
		Criteria crit = getSession().createCriteria(getPersistentClass());
		Example example = Example.create(exampleInstance);
		for (String exclude : excludeProperty) {
			example.excludeProperty(exclude);
		}
		crit.add(example);
		return crit.list();
	}

	// return number of row when Searching
	public int count(E exampleInstance, String[] excludeProperty, boolean isLike) {
		Criteria crit = getSession().createCriteria(getPersistentClass());
		Example example = Example.create(exampleInstance);
		for (String exclude : excludeProperty) {
			example.excludeProperty(exclude);
		}
		if (isLike) {
			example.enableLike(MatchMode.ANYWHERE).ignoreCase();
		}
		return (Integer) crit.add(example).setProjection(Projections.rowCount()).uniqueResult();
	}

	public Long count() {
		return (Long) getSession().createCriteria(this.getPersistentClass()).setProjection(Projections.rowCount())
				.uniqueResult();
	}

	public int count(Criterion... criterion) {
		Criteria crit = getSession().createCriteria(getPersistentClass());
		for (Criterion c : criterion) {
			crit.add(c);
		}
		return (Integer) crit.setProjection(Projections.rowCount()).uniqueResult();
	}

	public E save(E entity) {
		getSession().saveOrUpdate(entity);
		getSession().flush();

		return entity;
	}

	public void delete(E entity) {
		getSession().delete(entity);
		getSession().flush();
	}

	@SuppressWarnings("unchecked")
	public List<E> findByCriteria(Criterion... criterion) {
		Criteria crit = getSession().createCriteria(getPersistentClass());
		for (Criterion c : criterion) {
			crit.add(c);
		}
		return crit.list();
	}

	@SuppressWarnings("unchecked")
	public List<E> list(Integer offset, Integer maxResults) {
		return getSession().createCriteria(getPersistentClass()).setFirstResult(offset != null ? offset : 0)
				.setMaxResults(maxResults != null ? maxResults : 15).list();
	}
}
