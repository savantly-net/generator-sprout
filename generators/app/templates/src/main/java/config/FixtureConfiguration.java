package <%=groupId%>.config;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;

import <%=groupId%>.security.FakeContext;
import net.savantly.spring.fixture.Fixture;

@Configuration
@ConditionalOnProperty(prefix = "savantly", name = { "fixtures" })
public class FixtureConfiguration {

    @Autowired(required=false)
    List<Fixture<?>> fixtures;

    FakeContext fakeContext = new FakeContext();
    
    @PostConstruct
    public void installFixtures() {
        fakeContext.create();
        if (fixtures == null)
            return;
        for (Fixture<?> fixture : fixtures) {
            fixture.install();
        }
    }
}
