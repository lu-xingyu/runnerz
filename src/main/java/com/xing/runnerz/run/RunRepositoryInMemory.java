package com.xing.runnerz.run;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

@Repository
public class RunRepositoryInMemory {

    private List<Run> runs = new ArrayList<>();

    List<Run> findAll() {
        return runs;
    }

    Optional<Run> findById(Integer id) {
        return runs.stream()
                .filter(run -> run.id() == id)
                .findFirst();
        /* Stream: a pipe to calculate collection, can be called by chained operations, do not store data
         * intermediate operations(filter, map, sorted, limit, skip...), define the calculation, but won't trigger calculation, return a Stream
         *   filter returns a Stream, whose pipe only contain the filtered elements
         * terminal operation: trigger the calculation, return value can be List, Optional, long, or void
         *   findFirst: find the first one in current Stream, return an Optional Container (only contain 0 or one non-null element)
         *   get() is called on Optional to get the element
         */
    }

    void create(Run run) {
        runs.add(run);
    }

    void update(Run run, Integer id) {
        Optional<Run> existingRun = findById(id);
        if (existingRun.isPresent()) {
            runs.set(runs.indexOf(existingRun.get()), run);  // indexOf relies on equals method of the instance
        }
    }

    void delete(Integer id) {
        runs.removeIf(run -> run.id().equals(id));
    }



    @PostConstruct
    private void init() {
        runs.add(new Run(1,
                "Monday Morning Run",
                LocalDateTime.now(),
                LocalDateTime.now().plus(30, ChronoUnit.MINUTES),
                3,
                Location.INDOOR));

        runs.add(new Run(2,
                "Wednesday Evening Run",
                LocalDateTime.now(),
                LocalDateTime.now().plus(60, ChronoUnit.MINUTES),
                6,
                Location.INDOOR));
    }
}
