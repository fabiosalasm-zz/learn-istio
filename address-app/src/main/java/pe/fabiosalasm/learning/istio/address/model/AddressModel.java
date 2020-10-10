package pe.fabiosalasm.learning.istio.address.model;

import lombok.Data;

/**
 * Created by fabio.salas (fabio.salas@globant.com) on 2/10/2020
 **/
@Data
public class AddressModel {
    private String main;
    private String secondary;
    private String city;
    private String state;
    private String zipCode;
}
