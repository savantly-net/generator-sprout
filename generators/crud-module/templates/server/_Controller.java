package <%= groupId %>.modules.<%= camelizedPluralName %>;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import <%= groupId %>.rest.BaseController;

@RestController
@RequestMapping("/<%= slugifiedPluralName %>")
public class <%= classifiedSingularName %>Controller extends BaseController<<%= classifiedSingularName %>, <%= classifiedSingularName %>Repository> {

}
