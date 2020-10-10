package pe.fabiosalasm.learning.istio.address.controller;

import com.github.javafaker.Faker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.fabiosalasm.learning.istio.address.model.AddressModel;
import pe.fabiosalasm.learning.istio.address.mapper.FakerMapper;

/**
 * Created by fabio.salas (fabio.salas@globant.com) on 2/10/2020
 **/
@Slf4j
@RequiredArgsConstructor
@RestController
public class MainController {
    private final FakerMapper fakerMapper;
    private final Faker faker;
    
    @GetMapping("address")
    public AddressModel main() {
        return fakerMapper.toAddressModel(faker.address());
    }
}
