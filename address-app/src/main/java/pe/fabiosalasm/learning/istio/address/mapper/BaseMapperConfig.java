package pe.fabiosalasm.learning.istio.address.mapper;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.MapperConfig;
import org.mapstruct.ReportingPolicy;

/**
 * Created by fabio.salas (fabio.salas@globant.com) on 2/10/2020
 **/
@MapperConfig(componentModel = "spring",
        typeConversionPolicy = ReportingPolicy.ERROR,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface BaseMapperConfig {
}
