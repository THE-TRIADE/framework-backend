package imd.ufrn.instancestudentroutine.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import imd.ufrn.framework.controller.DependentController;
import imd.ufrn.instancestudentroutine.model.DependentStudent;

@CrossOrigin
@RestController
@RequestMapping("/dependent")
public class DependentStudentControllerImpl extends DependentController<DependentStudent> {
}
