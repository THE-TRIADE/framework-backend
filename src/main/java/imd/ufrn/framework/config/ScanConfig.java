package imd.ufrn.framework.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;


@Configuration
public class ScanConfig {
    @Profile("family-routine")
    @Configuration
    @ComponentScan(basePackages = {"imd.ufrn.framework","imd.ufrn.instancefamilyroutine"})
    class FamilyRoutineConfig {

    }

    @Profile("pet-routine")
    @Configuration
    @ComponentScan(basePackages = {"imd.ufrn.framework","imd.ufrn.instancepetroutine"})
    class PetRoutineConfig {

    }

    @Profile("student-routine")
    @Configuration
    @ComponentScan(basePackages = {"imd.ufrn.framework", "imd.ufrn.instancestudentroutine"})
    class StudentRoutineConfig {

    }
}