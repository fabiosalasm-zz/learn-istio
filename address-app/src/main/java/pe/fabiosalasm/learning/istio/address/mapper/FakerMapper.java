package pe.fabiosalasm.learning.istio.address.mapper;

import com.github.javafaker.Address;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pe.fabiosalasm.learning.istio.address.model.AddressModel;

/**
 * Created by fabio.salas (fabio.salas@globant.com) on 2/10/2020
 **/
@Mapper(config = BaseMapperConfig.class)
public interface FakerMapper {
    @Mapping(target = "main", expression = "java(address.streetPrefix() + address.streetAddress(false) + address.streetSuffix())")
    @Mapping(target = "secondary", expression = "java(address.secondaryAddress())")
    @Mapping(target = "city", expression = "java(address.city())")
    @Mapping(target = "state", expression = "java(address.state())")
    @Mapping(target = "zipCode", expression = "java(address.zipCode())")
    AddressModel toAddressModel(Address address);
}
