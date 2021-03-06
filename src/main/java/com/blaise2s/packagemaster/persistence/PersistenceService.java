package com.blaise2s.packagemaster.persistence;

import java.lang.reflect.Method;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Id;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

public class PersistenceService {
	private static Context ctx;
	private static EntityManagerFactory emf;

	private PersistenceService() {
		// Intentionally left empty.
	}

	static {
		try {
			ctx = new InitialContext();
			emf = (EntityManagerFactory) ctx.lookup("java:comp/env/jdbc/emf");
		} catch (NamingException e) {
			System.out.println("Failed to create entity manager factory.");
			e.printStackTrace();
		}
	}

	public static <T> T search(Class<T> entityClass, Object primaryKey) {
		EntityManager em = emf.createEntityManager();
		T object = em.find(entityClass, primaryKey);
		em.close();
		return object;
	}

	public static <T> List<T> search(String namedQueryName, Class<T> resultClass) {
		EntityManager em = emf.createEntityManager();
		List<T> results = em.createNamedQuery(namedQueryName, resultClass).getResultList();
		em.close();
		return results;
	}

	public static <T> List<T> search(String namedQueryName, Class<T> resultClass, Object... params) {
		EntityManager em = emf.createEntityManager();
		TypedQuery<T> query = em.createNamedQuery(namedQueryName, resultClass);
		for (int i = 0; i < params.length; i++) {
			query.setParameter(i + 1, params[i]);
		}
		List<T> results = query.getResultList();
		em.close();
		return results;
	}

	public static boolean persist(Object o) {
		boolean success = false;
		try {
			EntityManager em = emf.createEntityManager();
			em.getTransaction().begin();
			em.persist(o);
			em.flush();
			em.getTransaction().commit();
			em.close();
			success = true;
		} catch (Exception e) {
			System.out.println("Failed to persist data.");
			e.printStackTrace();
		}
		return success;
	}

	public static Object persist(Object o, Class<?> objectClass) {
		Object id = null;
		try {
			Method method = null;
			Class<?> clazz = Class.forName(objectClass.getName());
			for (Method m : clazz.getMethods()) {
				if (m.isAnnotationPresent(Id.class)) {
					method = m;
					break;
				}
			}

			EntityManager em = emf.createEntityManager();
			em.getTransaction().begin();
			em.persist(o);
			em.flush();
			if (method != null) {
				id = method.invoke(o);
			}
			em.getTransaction().commit();
			em.close();
		} catch (Exception e) {
			System.out.println("Failed to persist data.");
			e.printStackTrace();
		}
		return id;
	}

	public static boolean update(String namedQueryName, Object... params) {
		boolean success = false;
		try {
			EntityManager em = emf.createEntityManager();
			Query query = em.createNamedQuery(namedQueryName);
			for (int i = 0; i < params.length; i++) {
				query.setParameter(i + 1, params[i]);
			}
			em.getTransaction().begin();
			query.executeUpdate();
			em.getTransaction().commit();
			em.close();
			success = true;
		} catch (Exception e) {
			System.out.println("Failed to update data.");
			e.printStackTrace();
		}
		return success;
	}

	public static boolean update(Object o) {
		boolean success = false;
		try {
			EntityManager em = emf.createEntityManager();
			em.getTransaction().begin();
			em.merge(o);
			em.getTransaction().commit();
			em.close();
			success = true;
		} catch (Exception e) {
			System.out.println("Failed to update data.");
			e.printStackTrace();
		}
		return success;
	}

	public static boolean delete(Class<?> entityClass, Object primaryKey) {
		boolean success = false;
		try {
			Object object = search(entityClass, primaryKey);
			EntityManager em = emf.createEntityManager();
			em.getTransaction().begin();
			if (!em.contains(object)) {
				em.remove(em.merge(object));
			}
			em.getTransaction().commit();
			em.close();
			success = true;
		} catch (Exception e) {
			System.out.println("Failed to remove data.");
			e.printStackTrace();
		}
		return success;
	}

	public static boolean delete(Object o) {
		boolean success = false;
		try {
			EntityManager em = emf.createEntityManager();
			em.getTransaction().begin();
			if (!em.contains(o)) {
				em.remove(em.merge(o));
			}
			em.getTransaction().commit();
			em.close();
			success = true;
		} catch (Exception e) {
			System.out.println("Failed to remove data.");
			e.printStackTrace();
		}
		return success;
	}
}
