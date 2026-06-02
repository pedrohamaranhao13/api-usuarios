package br.com.phamtecnologia.apiusuarios.components;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtComponent {

    private static final String SECRET_KEY = "minha-chave-secreta-super-segura";
    private static final long TEMPO_EXPERIRACAO = 1000*60*60;

    public String gerarToken(long id, String nome, String email) {

        Date dataGeracao = new Date();
        Date dataExpiracao = new Date(dataGeracao.getTime() + TEMPO_EXPERIRACAO);

        return Jwts.builder()
                .setSubject(email)
                .claim("id", id)
                .claim("nome", nome)
                .claim("email", email)
                .claim("dataGeracao", dataGeracao)
                .claim("dataExpiracao", dataExpiracao)
                .setIssuedAt(dataGeracao)
                .setExpiration(dataExpiracao)
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();

    }
}
