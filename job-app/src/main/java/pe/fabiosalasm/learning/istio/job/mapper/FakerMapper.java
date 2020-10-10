package pe.fabiosalasm.learning.istio.job.mapper;

import com.github.javafaker.Job;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pe.fabiosalasm.learning.istio.job.model.JobModel;

/**
 * Created by fabio.salas (fabio.salas@globant.com) on 3/10/2020
 **/
@Mapper(config = BaseMapperConfig.class)
public interface FakerMapper {
    @Mapping(target = "title", expression = "java(job.title())")
    @Mapping(target = "keySkills", expression = "java(job.keySkills())")
    JobModel toJobModel(Job job);
}
