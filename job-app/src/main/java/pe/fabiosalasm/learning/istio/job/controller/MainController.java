package pe.fabiosalasm.learning.istio.job.controller;

import com.github.javafaker.Faker;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.fabiosalasm.learning.istio.job.mapper.FakerMapper;
import pe.fabiosalasm.learning.istio.job.model.JobModel;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created by fabio.salas (fabio.salas@globant.com) on 3/10/2020
 **/
@RequiredArgsConstructor
@RestController
public class MainController {
    private final FakerMapper fakerMapper;
    private final Faker faker;

    @GetMapping(value = "/jobs")
    public List<JobModel> getJobs() {
        return IntStream.range(0, 5)
                .mapToObj(v -> fakerMapper.toJobModel(faker.job()))
                .collect(Collectors.toList());
    }
}
