package br.com.phamtecnologia.apiusuarios.Repository;

import br.com.phamtecnologia.apiusuarios.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    Usuario findByEmail(String email);

    Usuario findByEmailAndSenha(String email, String senha);
}
