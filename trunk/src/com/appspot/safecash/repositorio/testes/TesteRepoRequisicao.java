package com.appspot.safecash.repositorio.testes;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.util.Iterator;

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
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
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
		assertEquals(0, ds.prepare(new Query(Requisicao.class.getSimpleName()))
				.countEntities());

		Requisicao r1 = new Requisicao("desc1", EnumStatusRequisicao.OK);
		repo.inserir(r1);
		assertEquals(1, ds.prepare(new Query(Requisicao.class.getSimpleName()))
				.countEntities());

		Requisicao r2 = new RequisicaoContrato("desc2",
				EnumStatusRequisicao.PENDENTE, "", "", "", "", 0.05, null, "");
		repo.inserir(r2);
		assertEquals(1, ds.prepare(
				new Query(RequisicaoContrato.class.getSimpleName()))
				.countEntities());

		Requisicao r3 = new RequisicaoReembolso("desc3",
				EnumStatusRequisicao.OK, 15.5);
		repo.inserir(r3);
		assertEquals(1, ds.prepare(
				new Query(RequisicaoReembolso.class.getSimpleName()))
				.countEntities());

		Iterator<Requisicao> it = repo.iterator();
		Requisicao r;
		assertTrue(it.hasNext());
		r = it.next();
		assertNotNull(r);
		assertTrue(it.hasNext());
		r = it.next();
		assertNotNull(r);
		assertTrue(r instanceof RequisicaoContrato);
		assertTrue(it.hasNext());
		r = it.next();
		assertNotNull(r);
		assertTrue(r instanceof RequisicaoReembolso);
		assertFalse(it.hasNext());
	}

	private void doTestRemover() {
		DatastoreService ds = DatastoreServiceFactory.getDatastoreService();
		RepositorioRequisicao repo = new RepositorioRequisicaoBT();
		assertEquals(0, ds.prepare(new Query(Requisicao.class.getSimpleName()))
				.countEntities());

		Requisicao result1;
		Key k1;
		Requisicao r1 = new Requisicao("desc1", EnumStatusRequisicao.OK);
		k1 = KeyFactory.createKey(Requisicao.class.getSimpleName(),
				"arbitragem");
		r1.setKey(k1);
		repo.inserir(r1);
		assertEquals(1, ds.prepare(new Query(Requisicao.class.getSimpleName()))
				.countEntities());
		result1 = repo.procurar(k1);
		assertEquals(KeyFactory.keyToString(k1), KeyFactory.keyToString(result1
				.getKey()));

		Requisicao result2;
		Key k2;
		Requisicao r2 = new RequisicaoContrato("desc2",
				EnumStatusRequisicao.PENDENTE, "", "", "", "", 0.05, null, "");
		k2 = KeyFactory.createKey(RequisicaoContrato.class.getSimpleName(),
				"arbitragem");
		r2.setKey(k2);
		repo.inserir(r2);
		assertEquals(1, ds.prepare(
				new Query(RequisicaoContrato.class.getSimpleName()))
				.countEntities());
		result2 = repo.procurar(k2);
		assertEquals(KeyFactory.keyToString(k2), KeyFactory.keyToString(result2
				.getKey()));

		Requisicao result3;
		Key k3;
		Requisicao r3 = new RequisicaoReembolso("desc3",
				EnumStatusRequisicao.OK, 15.5);
		k3 = KeyFactory.createKey(RequisicaoReembolso.class.getSimpleName(),
				"arbitragem");
		r3.setKey(k3);
		repo.inserir(r3);
		assertEquals(1, ds.prepare(
				new Query(RequisicaoReembolso.class.getSimpleName()))
				.countEntities());
		result3 = repo.procurar(k3);
		assertEquals(KeyFactory.keyToString(k3), KeyFactory.keyToString(result3
				.getKey()));

		repo.remover(result1);
		assertEquals(0, ds.prepare(new Query(Requisicao.class.getSimpleName()))
				.countEntities());
		repo.remover(result2);
		assertEquals(0, ds.prepare(
				new Query(RequisicaoContrato.class.getSimpleName()))
				.countEntities());
		repo.remover(result3);
		assertEquals(0, ds.prepare(
				new Query(RequisicaoReembolso.class.getSimpleName()))
				.countEntities());
	}

	private void doTestProcurar() {
		DatastoreService ds = DatastoreServiceFactory.getDatastoreService();
		RepositorioRequisicao repo = new RepositorioRequisicaoBT();
		assertEquals(0, ds.prepare(new Query(Requisicao.class.getSimpleName()))
				.countEntities());
		assertEquals(0, ds.prepare(
				new Query(RequisicaoContrato.class.getSimpleName()))
				.countEntities());
		assertEquals(0, ds.prepare(
				new Query(RequisicaoReembolso.class.getSimpleName()))
				.countEntities());

		repo.inserir(new Requisicao("desc1", EnumStatusRequisicao.OK));
		repo.inserir(new RequisicaoReembolso("desc3", EnumStatusRequisicao.OK, 15.5));
		repo.inserir(new RequisicaoContrato("desc2", EnumStatusRequisicao.PENDENTE, "", "", "", "", 0.05, null, ""));
		Iterator<Requisicao> it = repo.procurar(EnumStatusRequisicao.OK);
		assertTrue(it.hasNext());
		assertNotNull(it.next());
		assertTrue(it.hasNext());
		assertNotNull(it.next());
		assertFalse(it.hasNext());
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
	public void testRemover2() {
		doTestRemover();
	}
	
	@Test
	public void testProcurar1() {
		doTestProcurar();
	}
	
	@Test
	public void testProcurar2() {
		doTestProcurar();
	}
}
