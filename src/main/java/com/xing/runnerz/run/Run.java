package com.xing.runnerz.run;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;


import javax.annotation.processing.SupportedSourceVersion;
import java.time.LocalDateTime;
/* Record: immutable class, java automatically generates:
* 1. constructor (canonical constructor: automatically assign value to variables)
* 2. getters named after variable names, e.g. run.id();
* 3. toString: ClassName[var1=.., var2=...]
* 4. hashCode() and equals() based on values of variables
* */

public record Run(
        @Id
        Integer id,
        @NotEmpty // invalid object can still be created, no errors; checks are only triggered when @Valid is used
        String title,
        LocalDateTime startedOn,
        LocalDateTime completedOn,
        @Positive
        Integer miles,
        Location location,
        @Version
        Integer version
) {
    // add logical check to canonical constructor;
    // java will automatically add check logic at the start of canonical constructor
    public Run {
        if(!completedOn.isAfter(startedOn)) {
            throw new IllegalArgumentException("Completed On must be after Started On");
        }
    }
}
