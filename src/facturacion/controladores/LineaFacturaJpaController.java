/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facturacion.controladores;

import facturacion.controladores.exceptions.NonexistentEntityException;
import facturacion.controladores.exceptions.PreexistingEntityException;
import facturacion.modelo.entidades.LineaFactura;
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
public class LineaFacturaJpaController implements Serializable {

    public LineaFacturaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(LineaFactura lineaFactura) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(lineaFactura);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findLineaFactura(lineaFactura.getNumero()) != null) {
                throw new PreexistingEntityException("LineaFactura " + lineaFactura + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(LineaFactura lineaFactura) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            lineaFactura = em.merge(lineaFactura);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = lineaFactura.getNumero();
                if (findLineaFactura(id) == null) {
                    throw new NonexistentEntityException("The lineaFactura with id " + id + " no longer exists.");
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
            LineaFactura lineaFactura;
            try {
                lineaFactura = em.getReference(LineaFactura.class, id);
                lineaFactura.getNumero();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The lineaFactura with id " + id + " no longer exists.", enfe);
            }
            em.remove(lineaFactura);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<LineaFactura> findLineaFacturaEntities() {
        return findLineaFacturaEntities(true, -1, -1);
    }

    public List<LineaFactura> findLineaFacturaEntities(int maxResults, int firstResult) {
        return findLineaFacturaEntities(false, maxResults, firstResult);
    }

    private List<LineaFactura> findLineaFacturaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(LineaFactura.class));
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

    public LineaFactura findLineaFactura(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(LineaFactura.class, id);
        } finally {
            em.close();
        }
    }

    public int getLineaFacturaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<LineaFactura> rt = cq.from(LineaFactura.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
