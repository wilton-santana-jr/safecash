package com.appspot.safecash.repositorio.testes;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.appspot.safecash.dados.Projeto;
import com.appspot.safecash.dados.Usuario;
import com.appspot.safecash.enuns.EnumPermissao;
import com.appspot.safecash.repositorio.RepositorioProjeto;
import com.appspot.safecash.repositorio.RepositorioProjetoBT;
import com.appspot.safecash.repositorio.RepositorioUsuario;
import com.appspot.safecash.repositorio.RepositorioUsuarioBT;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;

public class TesteRepoProjeto {

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

	private void doTestProcurarPorUsuario() {
		DatastoreService ds = DatastoreServiceFactory.getDatastoreService();
		RepositorioProjeto repo = new RepositorioProjetoBT();
		RepositorioUsuario repoUser = new RepositorioUsuarioBT();
		assertEquals(0, ds.prepare(new Query(Projeto.class.getSimpleName())).countEntities());
		assertEquals(0, ds.prepare(new Query(Usuario.class.getSimpleName())).countEntities());
		
		Date data1 = new Date(), data2 = null, data3 = null;
		try {			 
			data2 = new SimpleDateFormat("dd/MM/yyyy").parse("05/05/2010");
			data3 = new SimpleDateFormat("dd/MM/yyyy").parse("05/06/2010");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		repoUser.inserir(new Usuario("login1", "senha", "nome", EnumPermissao.ADMIN));
		assertEquals(1, ds.prepare(new Query(Usuario.class.getSimpleName())).countEntities());
		
		Usuario u = repoUser.procurar("login1");
		assertNotNull(u);
		
		String keyString = KeyFactory.keyToString(u.getKey());
		assertEquals(u.getKey(), KeyFactory.stringToKey(keyString));
		
		/*Projeto p1 = new Projeto(null, null, u.getKey(), "nome1", 10.10, data2, data1);
		Projeto p2 = new Projeto(null, null, u.getKey(), "nome2", 10.10, data3, data1);
		repo.inserir(p1);
		repo.inserir(p2);
		assertEquals(2, ds.prepare(new Query(Projeto.class.getSimpleName())).countEntities());
		
		Iterator<Projeto> it = repo.procurarPorResponsavel(u.getKey());
		assertNotNull(it);
		assertTrue(it.hasNext());
		assertEquals("nome1", it.next().getNome());
		assertTrue(it.hasNext());
		assertEquals("nome2", it.next().getNome());
		assertFalse(it.hasNext());*/
	}
	
	@Test
	public void testProcurar1() {
		doTestProcurarPorUsuario();
	}

	@Test
	public void testProcurar2() {
		doTestProcurarPorUsuario();
	}
}
