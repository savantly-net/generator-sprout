package <%=groupId%>.modules.organizations;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.OneToMany;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnore;

import <%=groupId%>.modules.PersistedModule;
import <%=groupId%>.modules.user.SproutUser;

@Entity
@EntityListeners({AuditingEntityListener.class})
public class Organization extends PersistedModule {
    
    private String name;
    private Set<SproutUser> members;
    
    public Organization(){
            this.members = new HashSet<SproutUser>();
    }
    
    public Organization(String name){
        this.name = name;
        this.members = new HashSet<SproutUser>();
    }

    public String getName() {
            return name;
    }

    public void setName(String name) {
            this.name = name;
    }

    @OneToMany
    @JsonIgnore
    public Set<SproutUser> getMembers() {
            return members;
    }

    public void setMembers(Set<SproutUser> members) {
            this.members = members;
    }
    
    public void addMember(SproutUser member){
            this.members.add(member);
    }
}
