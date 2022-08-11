package projeto.spring.data.aula.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import projeto.spring.data.aula.model.UsuarioSpringData;

@Repository
public interface InterfaceSpringDataUser extends CrudRepository<UsuarioSpringData, Long>{
	
	@Query(value = "select p from UsuarioSpringData p where p.nome like %?1%")
	public List<UsuarioSpringData> buscaPorNomeAsync (String nome);
	
	//Consultando Query por parametro
	@Query(value = "select p from UsuarioSpringData p where p.nome = :paramnome")  //Colocar o JPQL , no caso aqui retorna um objeto
	public UsuarioSpringData buscaPorNomeParam (@Param("paramnome") String paramnome);

	
	//Maneira condicional
	
	//deletar por nome Query criada que não existe no padrão.
	@Modifying   //anoto para o banco modificar
	@Transactional //anoto parastarta uma transação / abaixo comando
	@Query("delete from UsuarioSpringData u where u.nome = ?1") //delete na tabela usua.. u onde u.nome = parametro que vou passar.
	public void deletePorNome(String nome);
	
	//update condicional dois parametros
	@Modifying
	@Transactional
	@Query("update UsuarioSpringData u set u.email = ?1 where u.nome = ?2") //são dois parametros
	public void updateEmailPorNome(String email, String nome);

}
