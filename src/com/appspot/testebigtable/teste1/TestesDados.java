package com.appspot.testebigtable.teste1;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.Iterator;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;

public class TestesDados {

	private final LocalServiceTestHelper helper =
        				new LocalServiceTestHelper(new LocalDatastoreServiceTestConfig());
	
	@Before
    public void setUp() {
        helper.setUp();
    }

    @After
    public void tearDown() {
        helper.tearDown();
    }

    // run this test twice to prove we're not leaking any state across tests
    private void doTestInserir() {
        DatastoreService ds = DatastoreServiceFactory.getDatastoreService();
        
        assertEquals(0, ds.prepare(new Query(Pessoa.class.getSimpleName())).countEntities());
        
        RepositorioPessoa repo = new RepositorioPessoa();
        repo.inserir(new Pessoa("nome", "nacionalidade", new Date()));
        repo.inserir(new Pessoa("nome2", "nacionalidade2", new Date()));
        assertEquals(2, ds.prepare(new Query(Pessoa.class.getSimpleName())).countEntities());
    }
    
    private void doTestRemover(){
    	DatastoreService ds = DatastoreServiceFactory.getDatastoreService();
    	assertEquals(0, ds.prepare(new Query(Pessoa.class.getSimpleName())).countEntities());
    	
    	RepositorioPessoa repo = new RepositorioPessoa();
    	repo.inserir(new Pessoa("nome", "nacionalidade", new Date()));
        repo.inserir(new Pessoa("nome2", "nacionalidade2", new Date()));
    	
        Iterator<Pessoa> p = repo.procurar("nome2");
    	assertNotNull(p);
    	assertTrue(p.hasNext());    	
    	repo.remover(p.next().getId());
    	assertEquals(1, ds.prepare(new Query(Pessoa.class.getSimpleName())).countEntities());
    	
    	p = repo.procurar("nome");
    	assertNotNull(p);
    	assertTrue(p.hasNext());    	
    	repo.remover(p.next().getId());
    	assertEquals(0, ds.prepare(new Query(Pessoa.class.getSimpleName())).countEntities());
    }

    @Test
    public void testInserir1() {
        doTestInserir();
    }
    
    @Test
    public void testInserir2() {
        doTestInserir();
    }
    
    @Test
    public void testRemover1() {
        doTestRemover();
    }
    
    @Test
    public void testRemover() {
        doTestRemover();
    }
}
