package net.win;

import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

@SuppressWarnings("unchecked")
public abstract class BaseDAO<T> extends HibernateDaoSupport {
	Class<T> entityClass;

	public BaseDAO() {
		entityClass = (Class<T>) ((ParameterizedType) this.getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];
	}

	@Resource(name = "sessionFactory")
	public void setSuperSessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	/**
	 * 保存
	 * 
	 * @param t
	 * @return
	 * @throws Exception
	 */
	public Long save(T t) throws Exception {
		return (Long) getSession().save(t);
	}

	/**
	 * 保存
	 * 
	 * @param t
	 * @return
	 * @throws Exception
	 */
	public void persist(T t) throws Exception {
		getSession().persist(t);
	}

	/**
	 * 查找
	 * 
	 * @param id
	 * @return
	 */
	public T get(Long id) {
		return (T) getSession().get(entityClass, id);
	}

	/**
	 * 查找
	 * 
	 * @param id
	 * @return
	 */
	public T load(Long id) {
		return (T) getSession().load(entityClass, id);
	}

	/**
	 * 更新
	 * 
	 * @param t
	 * @throws Exception
	 */
	public void update(T t) throws Exception {
		getSession().update(t);
	}

	/**
	 * 保存或则更新
	 * 
	 * @param t
	 * @throws Exception
	 */
	public void saveOrUpdate(T t) throws Exception {
		getSession().saveOrUpdate(t);
	}

	/**
	 * 保存或则更新全部
	 * 
	 * @param entities
	 * @throws Exception
	 */
	public void saveOrUpdateAll(final Collection<T> entitys) throws Exception {
		for (Iterator it = entitys.iterator(); it.hasNext();) {
			getSession().saveOrUpdate(it.next());
		}
	}

	/**
	 * 根据对象删除
	 * 
	 * @param t
	 * @throws Exception
	 */
	public void delete(T t) throws Exception {
		getSession().delete(t);
	}

	/**
	 * 删除根据hql
	 * 
	 * @param t
	 * @throws Exception
	 */
	public int deleteByHql(String hql, String[] names, Object[] values)
			throws Exception {
		Query query = getSession().createQuery(hql);
		for (int i = 0; i < names.length; i++) {
			query.setParameter(names[i], values[i]);
		}
		return query.executeUpdate();
	}

	/**
	 * 删除根据native sql
	 * 
	 * @param t
	 * @throws Exception
	 */
	public int deleteBySQL(String sql, String[] names, Object[] values)
			throws Exception {
		Query query = getSession().createSQLQuery(sql);
		for (int i = 0; i < names.length; i++) {
			query.setParameter(names[i], values[i]);
		}
		return query.executeUpdate();
	}

	/**
	 * 删除全部
	 * 
	 * @param entitys
	 * @throws Exception
	 */
	public void deleteAll(final Collection<T> entitys) throws Exception {
		for (Iterator it = entitys.iterator(); it.hasNext();) {
			getSession().delete(it.next());
		}
	}

	/**
	 * 根据ID删除
	 * 
	 * @param id
	 * @throws Exception
	 */
	public void deleteById(Long id) throws Exception {
		delete(load(id));
	}

	/**
	 * 批量保存
	 */
	public void saveAll(Collection<T> collection) throws Exception {
		saveOrUpdateAll(collection);
	}

	/**
	 * 根据HQL查询
	 * 
	 * @param queryString
	 * @return
	 * @throws Exception
	 */
	public List list(String queryString) {
		Query query = getSession().createQuery(queryString);
		return query.list();
	}

	/**
	 * 根据HQL查询
	 * 
	 * @param queryString
	 * @return
	 * @throws Exception
	 */
	public T uniqueResult(String queryString) {
		Query query = getSession().createQuery(queryString);
		return (T) query.uniqueResult();
	}

	/**
	 * 根据HQL查询
	 * 
	 * @param queryString
	 * @return
	 * @throws Exception
	 */
	public T uniqueResult(String queryString, String[] argNames, Object[] args)
			throws Exception {
		Query query = getSession().createQuery(queryString);
		int argNum;
		if ((argNum = argNames.length) != args.length)
			throw new IllegalArgumentException(
					"Length of paramNames array must match length of values array");
		for (int i = 0; i < argNum; i++) {
			query.setParameter(argNames[i], args[i]);
		}
		return (T) query.uniqueResult();
	}

	/**
	 * 根据HQL查询
	 * 
	 * @param queryString
	 * @return
	 * @throws Exception
	 */
	public T uniqueResult(String queryString, String name, Object value)
			throws Exception {
		return uniqueResult(queryString, new String[] { name },
				new Object[] { value });
	}

	/**
	 * 根据HQL查询
	 * 
	 * @param queryString
	 * @return
	 * @throws Exception
	 */
	public Object uniqueResultObject(String queryString, String[] argNames,
			Object[] args) throws Exception {
		Query query = getSession().createQuery(queryString);
		int argNum;
		if ((argNum = argNames.length) != args.length)
			throw new IllegalArgumentException(
					"Length of paramNames array must match length of values array");
		for (int i = 0; i < argNum; i++) {
			query.setParameter(argNames[i], args[i]);
		}
		return query.uniqueResult();
	}

	/**
	 * 根据HQL查询
	 * 
	 * @param queryString
	 * @return
	 * @throws Exception
	 */
	public Object uniqueResultObject(String queryString) throws Exception {
		Query query = getSession().createQuery(queryString);
		return query.uniqueResult();
	}

	/**
	 * 根据HQL查询
	 * 
	 * @param queryString
	 * @return
	 * @throws Exception
	 */
	public Object uniqueResultObject(String queryString, String name,
			Object value) throws Exception {
		return uniqueResultObject(queryString, new String[] { name },
				new Object[] { value });
	}

	/**
	 * 带一个参数的HQL查询
	 * 
	 * @param queryString
	 * @param value
	 * @return
	 * @throws Exception
	 */
	public List list(String queryString, Object value) throws Exception {
		Object[] values = { value };
		try {
			return list(queryString, values);
		} catch (Exception e) {
			throw new Exception(e);
		}

	}

	/**
	 * 带需带参数的HQL查询
	 * 
	 * @param queryString
	 * @param values
	 * @return
	 * @throws Exception
	 */
	public List list(String queryString, Object[] values) throws Exception {
		Query query = getSession().createQuery(queryString);
		for (int i = 0; i < values.length; i++) {
			query.setParameter(i, values[i]);
		}
		return query.list();

	}

	/**
	 * 带需带参数的HQL查询
	 * 
	 * @param queryString
	 * @param values
	 * @return
	 * @throws Exception
	 */
	public List list(String queryString, String name, Object value)
			throws Exception {
		return list(queryString, new String[] { name }, new Object[] { value });

	}

	/**
	 * 分页的HQL查询
	 * 
	 * @param queryString
	 * @param start
	 * @param limit
	 * @return
	 * @throws Exception
	 */
	public List pageQuery(String queryString, Integer start, Integer limit)
			throws Exception {
		Object[] values = {};
		try {
			return pageQuery(queryString, values, start, limit);
		} catch (Exception e) {
			throw new Exception(e);
		}

	}

	/**
	 * 带一个参数的分页HQL查询
	 * 
	 * @param queryString
	 * @param value
	 * @param start
	 * @param limit
	 * @return
	 * @throws Exception
	 */
	public List pageQuery(String queryString, Object value, Integer start,
			Integer limit) throws Exception {
		Object[] values = { value };
		try {
			return pageQuery(queryString, values, start, limit);
		} catch (Exception e) {
			throw new Exception(e);
		}
	}

	/**
	 * 带很多参数的分页查询
	 * 
	 * @param queryString
	 * @param values
	 * @param start
	 * @param limit
	 * @return
	 * @throws Exception
	 */
	public List pageQuery(String queryString, Object[] values, Integer start,
			Integer limit) throws Exception {
		Query query = getSession().createQuery(queryString);
		for (int i = 0; i < values.length; i++) {
			query.setParameter(i, values[i]);
		}
		query.setFirstResult(start).setMaxResults(limit);
		return query.list();
	}

	/**
	 * 指定参数名的查询
	 * 
	 * @param queryString
	 * @param argNames
	 * @param args
	 * @return
	 * @throws Exception
	 */
	public List list(String queryString, String[] argNames, Object[] args)
			throws Exception {

		Query query = getSession().createQuery(queryString);
		int argNum;
		if ((argNum = argNames.length) != args.length)
			throw new IllegalArgumentException(
					"Length of paramNames array must match length of values array");
		for (int i = 0; i < argNum; i++) {
			query.setParameter(argNames[i], args[i]);
		}
		return query.list();

	}

	/**
	 * 指定参数名的分页查询
	 * 
	 * @param queryString
	 * @param argNames
	 * @param args
	 * @param start
	 * @param limit
	 * @return
	 * @throws Exception
	 */
	public List pageQuery(String queryString, String[] argNames, Object[] args,
			Integer start, Integer limit) throws Exception {
		Query query = getSession().createQuery(queryString);
		if (argNames.length != args.length)
			throw new Exception("参数名称List跟参数值List的长度不匹配!");
		for (int i = 0; i < argNames.length; i++) {
			query.setParameter(argNames[i], args[i]);
		}
		query.setFirstResult(start).setMaxResults(limit);
		return query.list();

	}

	/**
	 * 指定参数名的分页查询
	 * 
	 * @param queryString
	 * @param argNames
	 * @param args
	 * @param start
	 * @param limit
	 * @return
	 * @throws Exception
	 */
	public List pageQuery(String queryString, String name, Object value,
			Integer start, Integer limit) throws Exception {
		return pageQuery(queryString, new String[] { name },
				new Object[] { value }, start, limit);

	}

	/**
	 * 查询所有
	 */
	public List<T> listAll() throws Exception {
		String hql = "from " + entityClass.getSimpleName();
		Query queryObject = getSession().createQuery(hql);
		return queryObject.list();
	}
}
