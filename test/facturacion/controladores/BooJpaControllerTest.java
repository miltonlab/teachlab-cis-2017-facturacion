/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facturacion.controladores;

import facturacion.modelo.entidades.Boo;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

/**
 *
 * @author miltonlab
 */
public class BooJpaControllerTest {
    
    EntityManagerFactory emf; 
    
    public BooJpaControllerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        emf = Persistence.createEntityManagerFactory("FacturacionPU");
        
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getEntityManager method, of class BooJpaController.
     */
    @Ignore
    @Test
    public void testGetEntityManager() {
        System.out.println("getEntityManager");
        BooJpaController instance = null;
        EntityManager expResult = null;
        EntityManager result = instance.getEntityManager();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of create method, of class BooJpaController.
     */
    @Test
    public void testCreate() throws Exception {
        System.out.println("create");
        Boo boo = new Boo();
        boo.setId(1);
        boo.setFecha(new java.util.Date());
        boo.setHora(new java.util.Date());
        BooJpaController instance = new BooJpaController(emf);
        instance.create(boo);
        System.out.println("Entidad  'Boo' creada correctamente !!!");
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of edit method, of class BooJpaController.
     */
    @Ignore
    @Test
    public void testEdit() throws Exception {
        System.out.println("edit");
        Boo boo = null;
        BooJpaController instance = null;
        instance.edit(boo);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of destroy method, of class BooJpaController.
     */
    @Ignore
    @Test
    public void testDestroy() throws Exception {
        System.out.println("destroy");
        Integer id = null;
        BooJpaController instance = null;
        instance.destroy(id);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findBooEntities method, of class BooJpaController.
     */
    @Ignore
    @Test
    public void testFindBooEntities_0args() {
        System.out.println("findBooEntities");
        BooJpaController instance = null;
        List<Boo> expResult = null;
        List<Boo> result = instance.findBooEntities();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findBooEntities method, of class BooJpaController.
     */
    @Ignore
    @Test
    public void testFindBooEntities_int_int() {
        System.out.println("findBooEntities");
        int maxResults = 0;
        int firstResult = 0;
        BooJpaController instance = null;
        List<Boo> expResult = null;
        List<Boo> result = instance.findBooEntities(maxResults, firstResult);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findBoo method, of class BooJpaController.
     */
    @Ignore
    @Test
    public void testFindBoo() {
        System.out.println("findBoo");
        Integer id = null;
        BooJpaController instance = null;
        Boo expResult = null;
        Boo result = instance.findBoo(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getBooCount method, of class BooJpaController.
     */
    @Ignore
    @Test
    public void testGetBooCount() {
        System.out.println("getBooCount");
        BooJpaController instance = null;
        int expResult = 0;
        int result = instance.getBooCount();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
