package pe.fabiosalasm.learning.istio.job.config;

import com.github.javafaker.Faker;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Locale;

/**
 * Created by fabio.salas (fabio.salas@globant.com) on 3/10/2020
 **/
@Configuration
public class MainConfig {
    @Bean
    public Faker faker() {
        return new Faker(new Locale("en"));
    }
}
