package com.appspot.safecash.repositorio.testes;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.appspot.safecash.dados.Conta;
import com.appspot.safecash.enuns.EnumStatusConta;
import com.appspot.safecash.repositorio.RepositorioConta;
import com.appspot.safecash.repositorio.RepositorioContaBT;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;

public class TesteRepoConta {

	private final LocalServiceTestHelper helper = new LocalServiceTestHelper(
			new LocalDatastoreServiceTestConfig());

	@Before
	public void setUp() {
		helper.setUp();
	}

	@After
	public void tearDown() {
		helper.tearDown();
	}
	
	private void doTestProcurarPorPeriodo() {
		DatastoreService ds = DatastoreServiceFactory.getDatastoreService();
		RepositorioConta repo = new RepositorioContaBT(); 
		assertEquals(0, ds.prepare(new Query(Conta.class.getSimpleName())).countEntities());
		
		Date data1 = new Date(), data2 = null, data3 = null;
		try {			 
			data2 = new SimpleDateFormat("dd/MM/yyyy").parse("05/05/2010");
			data3 = new SimpleDateFormat("dd/MM/yyyy").parse("05/06/2010");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		Conta c1 = new Conta(data2, "desc1", 10.5, EnumStatusConta.ATRASADO);
		Conta c2 = new Conta(data3, "desc2", 10.5, EnumStatusConta.PAGO);
		
		repo.inserir(c1);
		repo.inserir(c2);
		assertEquals(2, ds.prepare(new Query(Conta.class.getSimpleName())).countEntities());
		
		Iterator<Conta> it = repo.procurar(data2, data1);
		assertNotNull(it);
		assertTrue(it.hasNext());
		assertEquals("desc1", it.next().getDescricao());
		assertTrue(it.hasNext());
		assertEquals("desc2", it.next().getDescricao());
		assertFalse(it.hasNext());
	}
	
	@Test
	public void testProcurar1() {
		doTestProcurarPorPeriodo();
	}

	@Test
	public void testProcurar2() {
		doTestProcurarPorPeriodo();
	}
}
