package com.xing.runnerz.run;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/*
* @RestController = @Controller + @ResponseBody
* @Controller: this is a controller class, which can process HTTP request
* @ResponsesBody: the return value of methods in this class should be serialized into json format and be wrapped into HTTP Request Body
 *  */
@RestController
@RequestMapping("/api/runs")
public class RunController {

    private final RunRepository runRepository;

    /* When Spring initialize all the beans: Dependency Injection
    * if it fins that one Bean is dependent on the other, it will first create the dependency bean(if there isn't one in the container, and create current bean
    * */
    @Autowired // tell Spring that this constructor"s dependencies should be injected by Spring; if there is only one constructor, annotation can be omitted
    public RunController(RunRepository runRepository) {
        this.runRepository = runRepository;
    }

    @GetMapping("")
    List<Run> findAll() {
        return runRepository.findAll();
    }

    @GetMapping("/{id}")
    Run findById(@PathVariable Integer id) {  // extract the id from the path and convert it to integer, by default the new variable has the same name
        Optional<Run> run = runRepository.findById(id);
        if(run.isEmpty()) {
            throw new RunNotFoundException();
        }
        return run.get();
    }

    // Run findById(@PathVariable("id") Integer someId) {...} this also works, the passed in variable is someId
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    void create(@Valid @RequestBody Run run) {
        runRepository.save(run);
    }
    // Spring will convert RequestBody to Run object and pass it to this function, the para name in constructor must be the same as the json field name
    // Spring will validate the passing object is valid based on rules given in Run variables

    @ResponseStatus(HttpStatus.NO_CONTENT) // only set to NO_CONTENT when method is successfully executed
    @PutMapping("/{id}")
    void update(@Valid @RequestBody Run run, @PathVariable Integer id) {
        runRepository.save(run);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    void delete(@PathVariable Integer id) {
        runRepository.delete(runRepository.findById(id).get());
    }

    @GetMapping("/location/{location}")
    List<Run> findByLocation(@PathVariable String location) {
        return runRepository.findAllByLocation(location);
    }


}
