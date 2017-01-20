package <%=groupId%>.config.jpa;

import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;
import org.springframework.boot.orm.jpa.hibernate.SpringPhysicalNamingStrategy;

public class JpaNamingStrategy extends SpringPhysicalNamingStrategy  {

    public static final JpaNamingStrategy INSTANCE = new JpaNamingStrategy();
    
    private String prefix = "web_app_";

    
    @Override
    public Identifier toPhysicalTableName(Identifier name, JdbcEnvironment jdbcEnvironment) {
        return new Identifier(prefix + name.getText(), name.isQuoted());
    }
}
