package pe.fabiosalasm.learning.istio.company.config;

import com.github.javafaker.Faker;
import com.wirefreethought.geodb.client.GeoDbApi;
import com.wirefreethought.geodb.client.model.GeoDbInstanceType;
import com.wirefreethought.geodb.client.net.GeoDbApiClient;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import pe.fabiosalasm.learning.istio.company.properties.MainProperties;

import java.util.Locale;

/**
 * Created by fabio.salas (fabio.salas@globant.com) on 2/10/2020
 **/
@Configuration
public class MainConfig {
    @Bean
    public Faker faker() {
        return new Faker(new Locale("en"));
    }

    @Bean
    public GeoDbApi geoDbApiClient() {
        return new GeoDbApi(new GeoDbApiClient(GeoDbInstanceType.FREE));
    }

    @Bean
    public RestTemplate addressService(MainProperties mainProperties) {
        return new RestTemplateBuilder()
                .rootUri(mainProperties.getAddressAppURL())
                .build();
    }

    @Bean
    public RestTemplate jobService(MainProperties mainProperties) {
        return new RestTemplateBuilder()
                .rootUri(mainProperties.getJobAppURL())
                .build();
    }
}
