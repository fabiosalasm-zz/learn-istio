package pe.fabiosalasm.learning.istio.company.controller;

import com.github.javafaker.Faker;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.fabiosalasm.learning.istio.company.mapper.FakerMapper;
import pe.fabiosalasm.learning.istio.company.model.CompanyModel;
import pe.fabiosalasm.learning.istio.company.service.MainService;

import java.util.ArrayList;

/**
 * Created by fabio.salas (fabio.salas@globant.com) on 2/10/2020
 **/
@RequiredArgsConstructor
@RestController
public class MainController {
    private final MainService mainService;
    private final FakerMapper fakerMapper;

    private final Faker faker;

    @GetMapping(value = "/company")
    public CompanyModel getCompany() {
        var company = fakerMapper.toCompanyModel(faker.company());
        var address = mainService.getAddress();
        var place = mainService.getPlace();
        var openPositions = mainService.getOpenPositions();

        company.setAddress(address);
        company.setPlace(place);
        company.setOpenPositions(openPositions == null ? new ArrayList<>() : openPositions);

        return company;
    }
}