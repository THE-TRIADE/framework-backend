package imd.ufrn.instancefamilyroutine.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import imd.ufrn.framework.controller.DependentController;
import imd.ufrn.instancefamilyroutine.model.DependentStandard;

@CrossOrigin
@RestController
@RequestMapping("/dependent")
public class DependentControllerImpl extends DependentController<DependentStandard> {
}
