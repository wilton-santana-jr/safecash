package com.appspot.safecash.repositorio.testes;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.appspot.safecash.dados.Funcionario;
import com.appspot.safecash.repositorio.RepositorioFuncionario;
import com.appspot.safecash.repositorio.RepositorioFuncionarioBT;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;

public class TesteRepoFuncionario {
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
		RepositorioFuncionario repo = new RepositorioFuncionarioBT(); 
		assertEquals(0, ds.prepare(new Query(Funcionario.class.getSimpleName())).countEntities());
		
		Funcionario f1 = new Funcionario("nome1", 10.33, "cargo1", "01", "end1", "tel1", "e1");
		repo.inserir(f1);
		assertEquals(1, ds.prepare(new Query(Funcionario.class.getSimpleName())).countEntities());
		
		Funcionario f2 = new Funcionario("nome2", 11.33, "cargo2", "02", "end2", "tel1", "e1");
		repo.inserir(f2);
		assertEquals(2, ds.prepare(new Query(Funcionario.class.getSimpleName())).countEntities());
		
		Funcionario f3 = new Funcionario("nome3", 12.33, "cargo3", "03", "end3", "tel1", "e1");
		repo.inserir(f3);
		assertEquals(3, ds.prepare(new Query(Funcionario.class.getSimpleName())).countEntities());
		
		Funcionario f4 = new Funcionario("nome4", 13.33, "cargo4", "04", "end4", "tel1", "e1");
		repo.inserir(f4);
		assertEquals(4, ds.prepare(new Query(Funcionario.class.getSimpleName())).countEntities());
		
		Funcionario f5 = new Funcionario("nome5", 14.33, "cargo5", "05", "end5", "tel1", "e1");
		repo.inserir(f5);
		assertEquals(5, ds.prepare(new Query(Funcionario.class.getSimpleName())).countEntities());
	}

	private void doTestRemover() {
		DatastoreService ds = DatastoreServiceFactory.getDatastoreService();
		RepositorioFuncionario repo = new RepositorioFuncionarioBT(); 
		assertEquals(0, ds.prepare(new Query(Funcionario.class.getSimpleName())).countEntities());
		
		Funcionario f1 = new Funcionario("nome1", 10.33, "cargo1", "01", "end1", "tel1", "e1");
		repo.inserir(f1);
		assertEquals(1, ds.prepare(new Query(Funcionario.class.getSimpleName())).countEntities());
		
		Funcionario f2 = new Funcionario("nome2", 11.33, "cargo2", "02", "end2", "tel1", "e1");
		repo.inserir(f2);
		assertEquals(2, ds.prepare(new Query(Funcionario.class.getSimpleName())).countEntities());
		
		repo.remover(f2);
		assertEquals(1, ds.prepare(new Query(Funcionario.class.getSimpleName())).countEntities());
		
		repo.remover(f1);
		assertEquals(0, ds.prepare(new Query(Funcionario.class.getSimpleName())).countEntities());
	}

	private void doTestProcurar() {
		DatastoreService ds = DatastoreServiceFactory.getDatastoreService();
		RepositorioFuncionario repo = new RepositorioFuncionarioBT(); 
		assertEquals(0, ds.prepare(new Query(Funcionario.class.getSimpleName())).countEntities());
				
		Funcionario f1 = new Funcionario("nome1", 10.33, "cargo1", "01", "end1", "tel1", "e1");
		Funcionario f2 = new Funcionario("nome2", 11.33, "cargo2", "02", "end2", "tel1", "e1");
		repo.inserir(f1);
		repo.inserir(f2);
		assertEquals(2, ds.prepare(new Query(Funcionario.class.getSimpleName())).countEntities());
				
		Funcionario f = repo.procurar(f2.getCPF());
		assertNotNull(f);
		assertEquals(f2.getNome(), f.getNome());
		
		f = repo.procurar(f1.getKey());
		assertNotNull(f);
		assertEquals(f1.getNome(), f.getNome());
	}
	
	private void doTestAtualizar() {
		DatastoreService ds = DatastoreServiceFactory.getDatastoreService();
		RepositorioFuncionario repo = new RepositorioFuncionarioBT(); 
		assertEquals(0, ds.prepare(new Query(Funcionario.class.getSimpleName())).countEntities());
			
		Funcionario f1 = new Funcionario("nome1", 10.33, "cargo1", "01", "end1", "tel1", "e1");
		Funcionario f2 = new Funcionario("nome2", 11.33, "cargo2", "02", "end2", "tel1", "e1");
		repo.inserir(f1);
		repo.inserir(f2);
		assertEquals(2, ds.prepare(new Query(Funcionario.class.getSimpleName())).countEntities());
		
		Funcionario f3 = repo.procurar(f1.getCPF());
		f3.setCargo("novoCargo1");
		f3.setEndereco("novoEnd1");
		repo.atualizar(f3);
		Funcionario f4 = repo.procurar(f3.getKey());
		assertEquals(f4.getCargo(), "novoCargo1");
		assertEquals(f4.getEndereco(), "novoEnd1");
				
		f3 = repo.procurar(f2.getCPF());
		f3.setCargo("novoCargo2");
		f3.setEndereco("novoEnd2");
		repo.atualizar(f3);
		f4 = repo.procurar(f3.getKey());
		assertEquals(f4.getCargo(), "novoCargo2");
		assertEquals(f4.getEndereco(), "novoEnd2");
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
	
	@Test
	public void testRemover1() {
		doTestRemover();
	}

	@Test
	public void testRemover2() {
		doTestRemover();
	}
}
