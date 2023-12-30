package com.exeq.riobank.configurations;

import com.exeq.riobank.models.Cliente;
import com.exeq.riobank.repositories.ClienteRepo;
import com.exeq.riobank.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.configuration.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class WebAuthentication extends GlobalAuthenticationConfigurerAdapter {
  @Autowired
  private ClienteRepo clienteRepo;
  @Autowired
  private ClienteService clienteService;

  @Override
  public void init(AuthenticationManagerBuilder auth) throws Exception {

    auth.userDetailsService(inputName-> {

      Cliente cliente = clienteService.buscarClientePorEmail(inputName);

      if (cliente != null) {
        if (cliente.getEmail().contains("@admin.com")) {
          return new User(cliente.getEmail(), cliente.getPassword(), AuthorityUtils.createAuthorityList("CLIENT","ADMIN"));
        }
        return new User(cliente.getEmail(), cliente.getPassword(), AuthorityUtils.createAuthorityList("CLIENT"));
      } else {
        throw new UsernameNotFoundException("Unknown user: " + inputName);
      }

    });

    }
  @Bean
  public PasswordEncoder passwordEncoder() {
    return PasswordEncoderFactories.createDelegatingPasswordEncoder();
  }
}
