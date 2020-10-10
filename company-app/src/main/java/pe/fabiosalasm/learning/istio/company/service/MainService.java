package pe.fabiosalasm.learning.istio.company.service;

import com.wirefreethought.geodb.client.GeoDbApi;
import com.wirefreethought.geodb.client.net.ApiException;
import com.wirefreethought.geodb.client.request.FindPlacesRequest;
import com.wirefreethought.geodb.client.request.PlaceRequestType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import pe.fabiosalasm.learning.istio.company.mapper.GeoDbMapper;
import pe.fabiosalasm.learning.istio.company.model.AddressModel;
import pe.fabiosalasm.learning.istio.company.model.JobModel;
import pe.fabiosalasm.learning.istio.company.model.PlaceModel;

import java.util.List;
import java.util.Random;
import java.util.Set;

/**
 * Created by fabio.salas (fabio.salas@globant.com) on 2/10/2020
 **/
@RequiredArgsConstructor
@Slf4j
@Component
public class MainService {
    private final RestTemplate addressService;
    private final RestTemplate jobService;
    private final GeoDbApi geoDbApiClient;
    private final GeoDbMapper geoDbMapper;


    public AddressModel getAddress() {
        try {
            return addressService.getForObject("/address", AddressModel.class);
        } catch (RestClientException rce) {
            log.error("Error en invocar al servicio 'address'", rce);
            throw rce;
        }
    }

    public List<JobModel> getOpenPositions() {
        try {
            ResponseEntity<List<JobModel>> response = jobService.exchange("/jobs", HttpMethod.GET, null,
                    new ParameterizedTypeReference<>() {
                    });
            return response.getBody();
        } catch (RestClientException rce) {
            log.error("Error en invocar al servicio 'jobs'", rce);
            throw rce;
        }
    }

    public PlaceModel getPlace() {
        try {
            var response = geoDbApiClient.findPlaces(FindPlacesRequest.builder()
                    .countryIds(Set.of("US")).namePrefix("San")
                    .minPopulation(100000)
                    .types(Set.of(PlaceRequestType.CITY))
                    .build());

            if (response.getData() != null) {
                Random r = new Random();
                return geoDbMapper.toPlaceModel(response.getData().get(r.nextInt(response.getData().size())));
            } else {
                response.getErrors().forEach(v -> log.error("error: {}", v.getMessage()));
                return null;
            }

        } catch (ApiException ae) {
            log.error("Error en invocar al servicio 'geo-db'. Error: {}", ae.getResponseBody(), ae);
            throw ae;
        }
    }
}
