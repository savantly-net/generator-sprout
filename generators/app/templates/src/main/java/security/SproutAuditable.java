package <%=groupId%>.security;

import java.io.Serializable;

import org.springframework.data.domain.Auditable;

public interface SproutAuditable<ID extends Serializable> extends Auditable<String, ID> {

}
