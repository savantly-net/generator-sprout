'use strict';
var Generator = require('yeoman-generator');
var chalk = require('chalk');
var yosay = require('yosay');

module.exports = Generator.extend({
  prompting: function () {
    // Have Yeoman greet the user.
    this.log(yosay(
      'Welcome to the ' + chalk.green('sprout application') + ' generator!'
    ));

    var prompts = [{
    	type: 'input',
    	name: 'appname',
    	message: 'Name of your application',
    	default: this.appname,
    	store: true
    },{
    	type: 'input',
    	name: 'description',
    	message: 'Application Description',
    	default: 'My Sprout Application',
    	store: true
    },{
    	type: 'input',
    	name: 'groupId',
    	message: 'Maven Group ID / Namespace [lower case alpha with dot delimiters and no spaces]',
    	default: this.appname,
    	store: true
    },{
    	type: 'input',
    	name: 'artifactId',
    	message: 'Maven Artifact ID',
    	default: this.appname,
    	store: true
    },{
    	type: 'input',
    	name: 'version',
    	message: 'Software Version',
    	default: '0.0.1-SNAPSHOT',
    	store: true
    }];

    return this.prompt(prompts).then(function (props) {
    	props.namespaceFolders = props.groupId.replace('.', '/');
      // To access props later use this.props.someAnswer;
      this.props = props;
    }.bind(this));
  },

	writing: function () {
		
		// dot root files
	  	this.log('writing dot files...');
		this.fs.copyTpl(
			this.templatePath('root/.*'),
			this.destinationPath('./'),
			this.props
		);
		
		// Root files
		this.log('writing root files...');
		this.fs.copyTpl(
			this.templatePath('root/*'),
			this.destinationPath('./'),
			this.props
		);
		
		// Java files
	  	this.log('writing java main files...');
		this.fs.copyTpl(
			this.templatePath('src/main/java'),
			this.destinationPath('./src/main/java/'+this.props.namespaceFolders+'/'),
			this.props
		);
	  	this.log('writing java test files...');
		this.fs.copyTpl(
			this.templatePath('src/test/java'),
			this.destinationPath('./src/test/java/'+this.props.namespaceFolders+'/'),
			this.props
		);
		
		// Resource Files
	  	this.log('writing main resource root files...');
		this.fs.copy(
			this.templatePath('src/main/resources'),
			this.destinationPath('./src/main/resources/'),
			this.props
		);
	},

  install: function () {
    //this.installDependencies();
  }
});
