package com.appspot.safecash.repositorio.testes;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.appspot.safecash.dados.Requisicao;
import com.appspot.safecash.dados.RequisicaoContrato;
import com.appspot.safecash.dados.RequisicaoReembolso;
import com.appspot.safecash.enuns.EnumStatusRequisicao;
import com.appspot.safecash.repositorio.RepositorioRequisicao;
import com.appspot.safecash.repositorio.RepositorioRequisicaoBT;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;

public class TesteRepoRequisicao {

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
	
	private void doTestInserir() {
		DatastoreService ds = DatastoreServiceFactory.getDatastoreService();
		RepositorioRequisicao repo = new RepositorioRequisicaoBT(); 
		assertEquals(0, ds.prepare(new Query(Requisicao.class.getSimpleName())).countEntities());
		
		Requisicao r1 = new Requisicao("desc1", EnumStatusRequisicao.OK);
		repo.inserir(r1);
		assertEquals(1, ds.prepare(new Query(Requisicao.class.getSimpleName())).countEntities());
		
		Requisicao r2 = new RequisicaoContrato("desc2", EnumStatusRequisicao.PENDENTE, "", "", "", "", 0.05, null, "");
		repo.inserir(r2);
		assertEquals(2, ds.prepare(new Query(Requisicao.class.getSimpleName())).countEntities());
		
		Requisicao r3 = new RequisicaoReembolso("desc3", EnumStatusRequisicao.OK, 15.5);
		repo.inserir(r3);
		assertEquals(3, ds.prepare(new Query(Requisicao.class.getSimpleName())).countEntities());
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
