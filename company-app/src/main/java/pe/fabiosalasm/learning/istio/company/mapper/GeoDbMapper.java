package pe.fabiosalasm.learning.istio.company.mapper;

import com.wirefreethought.geodb.client.model.PopulatedPlaceSummary;
import org.mapstruct.Mapper;
import pe.fabiosalasm.learning.istio.company.model.PlaceModel;

/**
 * Created by fabio.salas (fabio.salas@globant.com) on 3/10/2020
 **/
@Mapper(config = BaseMapperConfig.class)
public interface GeoDbMapper {
    PlaceModel toPlaceModel(PopulatedPlaceSummary populatedPlaceSummary);
}
