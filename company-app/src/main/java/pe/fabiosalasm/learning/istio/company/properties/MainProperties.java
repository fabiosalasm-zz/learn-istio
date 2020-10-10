package pe.fabiosalasm.learning.istio.company.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by fabio.salas (fabio.salas@globant.com) on 2/10/2020
 **/
@ConfigurationProperties(prefix = "app")
@Data
public class MainProperties {
    private String addressAppURL;
    private String jobAppURL;
}
