package com.xing.runnerz.run;

import java.time.LocalDateTime;
/* Record: immutable class, java automatically generates:
* 1. constructor
* 2. getters named after variable names, e.g. run.id();
* 3. toString: ClassName[var1=.., var2=...]
* 4. hashCode() and equals() based on values of variables
* */

public record Run(
        Integer id,
        String title,
        LocalDateTime startedOn,
        LocalDateTime completedOn,
        Integer miles,
        Location location
) {

}
