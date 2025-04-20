package yassine_hachguer.yassine_hachguer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import yassine_hachguer.yassine_hachguer.entities.Reclamation;
import yassine_hachguer.yassine_hachguer.repository.ReclamationRepository;

import java.util.Date;

@SpringBootApplication
public class ControlleReclamationApplication implements CommandLineRunner {
    @Autowired
    private ReclamationRepository reclamationRepository;

    public static void main(String[] args) {
        SpringApplication.run(ControlleReclamationApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        reclamationRepository.save(new Reclamation(null,new Date(),"objet 1","email 1",false));
        reclamationRepository.save(new Reclamation(null,new Date(),"objet 2","email 2",false));
        reclamationRepository.save(new Reclamation(null,new Date(),"objet 3","email 3",false));

    }
    @Bean
    PasswordEncoder passwordEncoder(){
        return  new BCryptPasswordEncoder();
    }
}
