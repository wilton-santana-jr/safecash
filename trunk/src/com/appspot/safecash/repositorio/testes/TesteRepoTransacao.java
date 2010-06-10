package com.appspot.safecash.repositorio.testes;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.appspot.safecash.dados.Transacao;
import com.appspot.safecash.enuns.EnumOrigemTransacao;
import com.appspot.safecash.enuns.EnumTipoTransacao;
import com.appspot.safecash.repositorio.RepositorioTransacao;
import com.appspot.safecash.repositorio.RepositorioTransacaoBT;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;

public class TesteRepoTransacao {

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
	
	private void doTestInserir(){
		DatastoreService ds = DatastoreServiceFactory.getDatastoreService();
		RepositorioTransacao repo = new RepositorioTransacaoBT(); 
		assertEquals(0, ds.prepare(new Query(Transacao.class.getSimpleName())).countEntities());
		
		Transacao t1 = new Transacao("desc1", null, 11.5, EnumTipoTransacao.ENTRADA, EnumOrigemTransacao.CAIXA);
		repo.inserir(t1);
		assertEquals(1, ds.prepare(new Query(Transacao.class.getSimpleName())).countEntities());
		
		Transacao t2 = new Transacao("desc2", null, 12.5, EnumTipoTransacao.ENTRADA, EnumOrigemTransacao.CAIXA);
		repo.inserir(t2);
		assertEquals(2, ds.prepare(new Query(Transacao.class.getSimpleName())).countEntities());
		
		Transacao t3 = new Transacao("desc3", null, 11.5, EnumTipoTransacao.SAIDA, EnumOrigemTransacao.CAIXA);
		repo.inserir(t3);
		assertEquals(3, ds.prepare(new Query(Transacao.class.getSimpleName())).countEntities());
	}
	
	private void doTestRemover(){
		DatastoreService ds = DatastoreServiceFactory.getDatastoreService();
		RepositorioTransacao repo = new RepositorioTransacaoBT(); 
		assertEquals(0, ds.prepare(new Query(Transacao.class.getSimpleName())).countEntities());
		
		Transacao t1 = new Transacao("desc1", null, 11.5, EnumTipoTransacao.ENTRADA, EnumOrigemTransacao.CAIXA);
		repo.inserir(t1);
		assertEquals(1, ds.prepare(new Query(Transacao.class.getSimpleName())).countEntities());
		
		Transacao t2 = new Transacao("desc1", null, 11.5, EnumTipoTransacao.SAIDA, EnumOrigemTransacao.CAIXA);
		repo.inserir(t2);
		assertEquals(2, ds.prepare(new Query(Transacao.class.getSimpleName())).countEntities());
		
		repo.remover(t1);
		assertEquals(1, ds.prepare(new Query(Transacao.class.getSimpleName())).countEntities());
		
		repo.remover(t2);
		assertEquals(0, ds.prepare(new Query(Transacao.class.getSimpleName())).countEntities());
	}
	
	private void doTestProcurar(){
		DatastoreService ds = DatastoreServiceFactory.getDatastoreService();
		RepositorioTransacao repo = new RepositorioTransacaoBT(); 
		assertEquals(0, ds.prepare(new Query(Transacao.class.getSimpleName())).countEntities());
		
		Date data1 = new Date(), data2 = null;
		try {			 
			data2 = new SimpleDateFormat("dd/MM/yyyy").parse("05/05/2005");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		Transacao t1 = new Transacao("desc1", data1, 11.5, EnumTipoTransacao.ENTRADA, EnumOrigemTransacao.CAIXA);
		Transacao t2 = new Transacao("desc2", data1, 12.5, EnumTipoTransacao.SAIDA, EnumOrigemTransacao.CAIXA);
		Transacao t3 = new Transacao("desc3", data1, 13.5, EnumTipoTransacao.SAIDA, EnumOrigemTransacao.CONTA);
		Transacao t4 = new Transacao("desc4", data2, 14.5, EnumTipoTransacao.ENTRADA, EnumOrigemTransacao.CONTA);
		Transacao t5 = new Transacao("desc5", data2, 15.5, EnumTipoTransacao.SAIDA, EnumOrigemTransacao.CAIXA);
		repo.inserir(t1); repo.inserir(t2); repo.inserir(t3); repo.inserir(t4); repo.inserir(t5);
		assertEquals(5, ds.prepare(new Query(Transacao.class.getSimpleName())).countEntities());
		
		// testa o procurar por data
		Iterator<Transacao> it = repo.procurar(data1);
		assertEquals(true, it.hasNext());
		int counter = 0;
		while(it.hasNext()){
			it.next();
			counter++;
		}
		assertEquals(counter, 3);
		
		// testa o procurar pela chave
		it = repo.procurar(data1);
		Transacao r1 = repo.procurar(it.next().getKey());
		assertEquals(r1.getDescricao(), "desc1");
		
		// testa o procurar por enum tipo
		it = repo.procurar(EnumTipoTransacao.ENTRADA);
		assertEquals(true, it.hasNext());
		assertEquals("desc1", it.next().getDescricao());
		assertEquals("desc4", it.next().getDescricao());
		assertEquals(false, it.hasNext());
		
		// testa o procurar por enum origem
		it = repo.procurar(EnumOrigemTransacao.CONTA);
		assertEquals(true, it.hasNext());
		assertEquals("desc3", it.next().getDescricao());
		assertEquals("desc4", it.next().getDescricao());
		assertEquals(false, it.hasNext());
	}
	
	private void doTestAtualizar(){
		DatastoreService ds = DatastoreServiceFactory.getDatastoreService();
		RepositorioTransacao repo = new RepositorioTransacaoBT(); 
		assertEquals(0, ds.prepare(new Query(Transacao.class.getSimpleName())).countEntities());
		
		Date data = new Date();
		Transacao t1 = new Transacao("desc1", data, 11.5, EnumTipoTransacao.ENTRADA, EnumOrigemTransacao.CAIXA);
		Transacao t2 = new Transacao("desc2", data, 12.5, EnumTipoTransacao.SAIDA, EnumOrigemTransacao.CAIXA);
		repo.inserir(t1); repo.inserir(t2); 
		assertEquals(2, ds.prepare(new Query(Transacao.class.getSimpleName())).countEntities());
		
		Iterator<Transacao> it = repo.procurar(EnumTipoTransacao.ENTRADA);
		assertEquals(true, it.hasNext());
		Transacao r = repo.procurar(it.next().getKey());
		assertEquals("desc1", r.getDescricao());
		r.setDescricao("novaDesc");
		r.setOrigem(EnumOrigemTransacao.CONTA);
		repo.atualizar(r);
		it = repo.procurar(EnumOrigemTransacao.CONTA);
		assertEquals(true, it.hasNext());
		r = repo.procurar(it.next().getKey());
		assertEquals("novaDesc", r.getDescricao());
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

	@Test
	public void testAtualizar1() {
		doTestAtualizar();
	}
	
	@Test
	public void testAtualizar2() {
		doTestAtualizar();
	}
}
