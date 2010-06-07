package com.appspot.testebigtable.teste2;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.jdo.PersistenceManager;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.appspot.testebigtable.teste1.PMF;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;

public class TesteRelacionamento {

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

    private void doTestInserir() {
    	DatastoreService ds = DatastoreServiceFactory.getDatastoreService();
        
    	assertEquals(0, ds.prepare(new Query(Conta.class.getSimpleName())).countEntities());
    	assertEquals(0, ds.prepare(new Query(Transacao.class.getSimpleName())).countEntities());
    	
    	PersistenceManager pm = PMF.get().getPersistenceManager();
    	Transacao t1 = new Transacao("t1", null, 1.5, EnumTipoTransacao.ENTRADA, EnumOrigemTransacao.CAIXA);
    	Transacao t2 = new Transacao("t2", null, 2.5, EnumTipoTransacao.SAIDA, EnumOrigemTransacao.CAIXA);
    	Transacao t3 = new Transacao("t3", null, 3.5, EnumTipoTransacao.ENTRADA, EnumOrigemTransacao.CONTA);
    	
    	List<Transacao> ts = new ArrayList<Transacao>();
    	ts.add(t1);
    	ts.add(t2);
    	Conta c = new Conta(null, "descricao", 11.25, EnumStatusConta.ATRASADO, ts);
    	
    	try{
    		pm.makePersistent(t3);
        	assertEquals(0, ds.prepare(new Query(Conta.class.getSimpleName())).countEntities());
        	assertEquals(1, ds.prepare(new Query(Transacao.class.getSimpleName())).countEntities());
    		
    		pm.makePersistent(c);
        	assertEquals(1, ds.prepare(new Query(Conta.class.getSimpleName())).countEntities());
        	assertEquals(3, ds.prepare(new Query(Transacao.class.getSimpleName())).countEntities());
    	} finally{
    		pm.close();
    	}    	
    }    

    @Test
    public void testInserir1() {
        doTestInserir();
    }
    
    @Test
    public void testInserir2() {
        doTestInserir();
    }
}
