package projeto.spring.data.aula;

import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import projeto.spring.data.aula.dao.InterfaceSpringDataUser;
import projeto.spring.data.aula.model.UsuarioSpringData;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:META-INF/spring-config.xml" })

public class AppSpringDataTest {

	@Autowired
	private InterfaceSpringDataUser interfaceStringDataUser;

	@Test // Cadastrando Banco de dados
	public void testeInsert() {

		UsuarioSpringData usuarioSpringData = new UsuarioSpringData();

		usuarioSpringData.setEmail("Larissa@hotmail.com");
		usuarioSpringData.setIdade(3);
		usuarioSpringData.setLogin("teste 123");
		usuarioSpringData.setSenha("123");
		usuarioSpringData.setNome("Lucas");

		interfaceStringDataUser.save(usuarioSpringData); // pode fazer varias ações como deletetar , pesquisar

		System.out.println("Usuario cadastrado -> " + interfaceStringDataUser.count());

	}

	@Test // Consulta Banco de dados
	public void testeConsulta() {

		Optional<UsuarioSpringData> usuarioSpringData = interfaceStringDataUser.findById(2L); // find retorna um objeto
																								// optional
		System.out.println(usuarioSpringData.get().getEmail());
		System.out.println(usuarioSpringData.get().getIdade());
		System.out.println(usuarioSpringData.get().getLogin());
		System.out.println(usuarioSpringData.get().getNome());
		System.out.println(usuarioSpringData.get().getSenha());
		System.out.println(usuarioSpringData.get().getId());

	}

	@Test // atualizar
	public void testeUpdate() {

		Optional<UsuarioSpringData> usuarioSpringData = interfaceStringDataUser.findById(3L);

		UsuarioSpringData data = usuarioSpringData.get();

		data.setNome("Alex  Update Spring Data");
		data.setIdade(25);

		interfaceStringDataUser.save(data);
	}

	@Test // Deletar objeto consultado
	public void testeDelete() {
		Optional<UsuarioSpringData> usuarioSpringData = interfaceStringDataUser.findById(6L);

		interfaceStringDataUser.delete(usuarioSpringData.get());
	}

	@Test // consulta por @query
	public void testeConsultaNome() {
		List<UsuarioSpringData> list = interfaceStringDataUser.buscaPorNomeAsync("Saulo");

		for (UsuarioSpringData usuarioSpringData : list) {
			System.out.println(usuarioSpringData.getEmail());
			System.out.println(usuarioSpringData.getIdade());
			System.out.println(usuarioSpringData.getLogin());
			System.out.println(usuarioSpringData.getNome());
			System.out.println(usuarioSpringData.getSenha());
			System.out.println(usuarioSpringData.getId());
			System.out.println("---------------------------");
		}
	}

	@Test // consulta por @query
	public void testeConsultaNomeParam() {
		UsuarioSpringData usuarioSpringData = interfaceStringDataUser.buscaPorNomeParam("Lucas");

		System.out.println(usuarioSpringData.getEmail());
		System.out.println(usuarioSpringData.getIdade());
		System.out.println(usuarioSpringData.getLogin());
		System.out.println(usuarioSpringData.getNome());
		System.out.println(usuarioSpringData.getSenha());
		System.out.println(usuarioSpringData.getId());
		System.out.println("---------------------------");
	}

	@Test // deletar por condição metodo @modify e transaction
	public void testeDeletePorNome() {
		interfaceStringDataUser.deletePorNome("Lucas");
	}

	@Test // atualizar por condição metodo @modify e transaction 2 parametros
	public void testeUpdateEmailPorNome() {
		
		interfaceStringDataUser.updateEmailPorNome("teste email springData@gmail","Lucas");
	}
}
