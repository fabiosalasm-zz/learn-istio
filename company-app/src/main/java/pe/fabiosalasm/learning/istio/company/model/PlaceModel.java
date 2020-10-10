package pe.fabiosalasm.learning.istio.company.model;

import lombok.Data;

/**
 * Created by fabio.salas (fabio.salas@globant.com) on 3/10/2020
 **/
@Data
public class PlaceModel {
    private String name;
    private String country;
    private String countryCode;
    private Double latitude;
    private Double longitude;
}
