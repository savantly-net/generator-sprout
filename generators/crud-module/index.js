'use strict';

var util = require('util'),
	inflections = require('underscore.inflections'),
	s = require('underscore.string'),
	_ = require('lodash'),
	mkdirp = require('mkdirp'),
	Generator = require('yeoman-generator'),
	clientModulesFolder = 'src/main/resources/public/modules/',
	serverModulesFolderTemplate = 'src/main/java/{{namespaceFolders}}/modules/{{camelizedPluralName}}/',
	yosay = require('yosay'),
	chalk = require('chalk'),
	S = require('string');

module.exports = Generator.extend({
	
	init: function(){
		this.props = {};
		this.log(yosay(
		      'Welcome to the ' + chalk.green('sprout module') + ' generator!'
		    ));
		this.template = function(source, destination){
			this.fs.copyTpl(
				this.templatePath(source),
				this.destinationPath(destination),
				this.props
			);
		};
	},
	
	askForName: function () {
		var prompts = [{
			type: 'input',
			name: 'name',
			default: '',
			message: 'What is the name of the module?'
		},{
			type: 'input',
			name: 'groupId',
			message: 'Group ID / Namespace for server module',
			store: true
		}];

		return this.prompt(prompts).then(function (props) {
			this.props.groupId = props.groupId;
	    	this.props.namespaceFolders = props.groupId.replace('.', '/');
	    	this.props.name = props.name;
	
	    	this.props.slugifiedName = s(this.props.name).slugify().value();
	
	    	this.props.slugifiedPluralName = inflections.pluralize(this.props.slugifiedName);
	    	this.props.slugifiedSingularName = inflections.singularize(this.props.slugifiedName);
	
	    	this.props.camelizedPluralName = s(this.props.slugifiedPluralName).camelize().value();
	    	this.props.camelizedSingularName = s(this.props.slugifiedSingularName).camelize().value();
	
	    	this.props.classifiedPluralName = s(this.props.slugifiedPluralName).classify().value();
	    	this.props.classifiedSingularName = s(this.props.slugifiedSingularName).classify().value();
	
	    	this.props.humanizedPluralName = s(this.props.slugifiedPluralName).humanize().value();
	    	this.props.humanizedSingularName = s(this.props.slugifiedSingularName).humanize().value();
	
	    	this.props.capitalizedSingularName = s(this.props.humanizedSingularName).capitalize().value();
		}.bind(this));
	},
	askForModuleFolders: function() {
	    var prompts = [{
	    	type: 'checkbox',
	    	name: 'clientFolders',
	    	message: 'Which client-side folders would you like your module to include?',
	    	choices: [{
	    		value: 'addCSSFolder',
	    		name: 'css',
	    		checked: false
	    	}, {
	    		value: 'addImagesFolder',
    			name: 'img',
    			checked: false
	    	}, {
	    		value: 'addDirectivesFolder',
	    		name: 'directives',
	    		checked: false
	    	}, {
	    		value: 'addFiltersFolder',
	    		name: 'filters',
	    		checked: false
	    	}]
	    }, {
	    	type: 'confirm',
	    	name: 'addMenuItems',
	    	message: 'Would you like to add the CRUD module links to a menu?',
	    	default: true
		}];

    return this.prompt(prompts).then(function(props) {
      var clientFolders = {},
        serverFolders = {};

      _.forEach(props.clientFolders, function(prop) {
        clientFolders[prop] = true;
      });
      _.forEach(props.serverFolders, function(prop) {
        serverFolders[prop] = true;
      });

      this.props.clientFolders = clientFolders;
      this.props.serverFolders = serverFolders;

      this.props.addMenuItems = props.addMenuItems;
    }.bind(this));
  },

  askForMenuId: function() {
    if (this.props.addMenuItems) {
      var prompts = [{
        name: 'menuId',
        message: 'What is your menu identifier(Leave it empty and press ENTER for the default "topbar" menu)?',
        default: 'topbar'
      }];

      return this.prompt(prompts).then(function(props) {
        this.props.menuId = props.menuId;
      }.bind(this));
    }
  },

	renderClientModule: function() {
		// Create module folder
		var moduleFolder = clientModulesFolder + this.props.slugifiedPluralName;
		mkdirp.sync(moduleFolder);
		
		// Create module supplemental folders
		if (this.props.clientFolders.addCSSFolder) {
		  mkdirp.sync(moduleFolder + '/css');
		}
		
		if (this.props.clientFolders.addImagesFolder) {
		  mkdirp.sync(moduleFolder + '/img');
		}
		
		if (this.props.clientFolders.addDirectivesFolder) {
		  mkdirp.sync(moduleFolder + '/directives');
		}
		
		if (this.props.clientFolders.addFiltersFolder) {
		  mkdirp.sync(moduleFolder + '/filters');
		}
		
		// Render angular module files
		var routesFile = moduleFolder + '/config/' + this.props.slugifiedPluralName + '.routes.js';
		this.log('Rendering angular routes file: ' + routesFile);
		this.template('client/config/_.routes.js', routesFile);
		
		var controllerFile = moduleFolder + '/controllers/' + this.props.slugifiedPluralName + '.controller.js';
		this.log('Rendering angular controller file: ' + controllerFile);
		this.template('client/controllers/_.controller.js', controllerFile);
		
		var serviceFile = moduleFolder + '/services/' + this.props.slugifiedPluralName + '.service.js';
		this.log('Rendering angular service file: ' + serviceFile);
		this.template('client/services/_.service.js', serviceFile);
		
		// Render angular module views
		this.log('Rendering angular module views: ' + moduleFolder + '/views/');
		this.template('client/views/*', moduleFolder + '/views/');
		
		// Render menu configuration
		if (this.props.addMenuItems) {
			this.log('Rendering menu configuration: ' + moduleFolder + '/config/' + this.props.slugifiedPluralName + '.config.js');
			this.template('client/config/_.config.js', moduleFolder + '/config/' + this.props.slugifiedPluralName + '.config.js');
		}
		
		// Render angular module definition
		this.log('Rendering angular module definition: ' + moduleFolder + '/' + this.props.slugifiedPluralName + '.module.js');
		this.template('client/_.module.js', moduleFolder + '/' + this.props.slugifiedPluralName + '.module.js');

	},
  
	renderServerModule: function(){

    	var serverModulesFolder = S(serverModulesFolderTemplate).template(this.props).s;
    	
		// Entity
		var entityFile = serverModulesFolder + this.props.classifiedSingularName + '.java';
		this.log('Rendering server entityFile: ' + entityFile);
		this.template('server/_Entity.java', entityFile);
		
		// Repository
		var repositoryFile = serverModulesFolder + this.props.classifiedSingularName + 'Repository.java';
		this.log('Rendering server repositoryFile: ' + repositoryFile);
		this.template('server/_Repository.java', repositoryFile);
		
		// Repository Configuration
		var repositoryConfigurationFile = serverModulesFolder + this.props.classifiedSingularName + 'RepositoryConfiguration.java';
		this.log('Rendering server repositoryConfigurationFile: ' + repositoryConfigurationFile);
		this.template('server/_RepositoryConfiguration.java', repositoryConfigurationFile);

	}
});