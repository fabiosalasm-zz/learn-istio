package pe.fabiosalasm.learning.istio.company.model;

import lombok.Data;

import java.util.List;

/**
 * Created by fabio.salas (fabio.salas@globant.com) on 2/10/2020
 **/
@Data
public class CompanyModel {
    private String name;
    private String industry;
    private String logo;
    private String url;
    private AddressModel address;
    private PlaceModel place;
    private List<JobModel> openPositions;
}
