/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facturacion.controladores;

import facturacion.controladores.exceptions.NonexistentEntityException;
import facturacion.controladores.exceptions.PreexistingEntityException;
import facturacion.modelo.entidades.Boo;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author miltonlab
 */
public class BooJpaController implements Serializable {

    public BooJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Boo boo) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(boo);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findBoo(boo.getId()) != null) {
                throw new PreexistingEntityException("Boo " + boo + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Boo boo) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            boo = em.merge(boo);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = boo.getId();
                if (findBoo(id) == null) {
                    throw new NonexistentEntityException("The boo with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Boo boo;
            try {
                boo = em.getReference(Boo.class, id);
                boo.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The boo with id " + id + " no longer exists.", enfe);
            }
            em.remove(boo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Boo> findBooEntities() {
        return findBooEntities(true, -1, -1);
    }

    public List<Boo> findBooEntities(int maxResults, int firstResult) {
        return findBooEntities(false, maxResults, firstResult);
    }

    private List<Boo> findBooEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Boo.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Boo findBoo(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Boo.class, id);
        } finally {
            em.close();
        }
    }

    public int getBooCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Boo> rt = cq.from(Boo.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
