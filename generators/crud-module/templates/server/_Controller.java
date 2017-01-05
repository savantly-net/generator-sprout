package <%= groupId %>.modules.<%= slugifiedPluralName %>;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import <%= groupId %>.rest.BaseController;

@RestController
@RequestMapping("/<%= slugifiedPluralName %>")
public class <%= capitalizedSingularName %>Controller extends BaseController<<%= capitalizedSingularName %>, <%= capitalizedSingularName %>Repository> {

}
