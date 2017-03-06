package <%=groupId%>.config;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import co.intnt.modules.emailTemplates.EmailTemplate;
import co.intnt.modules.emailTemplates.EmailTemplateRepository;
import freemarker.template.Configuration;
import freemarker.template.Template;

@org.springframework.context.annotation.Configuration
public class FreemarkerConfiguration {
    
    private static final Logger log = LoggerFactory.getLogger(FreemarkerConfiguration.class);
    
    @Autowired
    ResourceConfiguration resourceConfig;
    
    private Configuration configuration;
    
    public FreemarkerConfiguration(){
        this.configuration = new Configuration(Configuration.VERSION_2_3_25);
    }
    
    public Template getTemplate(String templateName, String templatePath) throws IOException {
        String templateSource = resourceConfig.getResourceContents(templatePath);
        return createTemplate(templateName, templateSource);
    }
    
    private Template createTemplate(String name, String sourceCode) throws IOException {
            return new Template(name, sourceCode, configuration);
    }

}
