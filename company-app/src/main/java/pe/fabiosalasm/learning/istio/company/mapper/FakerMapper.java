package pe.fabiosalasm.learning.istio.company.mapper;

import com.github.javafaker.Company;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pe.fabiosalasm.learning.istio.company.model.CompanyModel;

/**
 * Created by fabio.salas (fabio.salas@globant.com) on 2/10/2020
 **/
@Mapper(config = BaseMapperConfig.class)
public interface FakerMapper {
    @Mapping(target = "name", expression = "java(company.name())")
    @Mapping(target = "industry", expression = "java(company.industry() + \" (\" + company.suffix() + \")\")")
    @Mapping(target = "logo", expression = "java(company.logo())")
    @Mapping(target = "url", expression = "java(company.url())")
    @Mapping(target = "address", ignore = true)
    CompanyModel toCompanyModel(Company company);
}
