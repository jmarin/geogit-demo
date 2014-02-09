module.exports = function(grunt) {

		// Project configuration.
    grunt.initConfig({
        pkg: grunt.file.readJSON('package.json'),

        //The target directory of Grunt is the standard "public/" folder from Play.
        target: "../public/",

        copy: {
				  build: {
					  cwd: 'bower_components',
		        src: '**/*',
						dest: '../public/js',
						expand: true
					}
				},
        uglify: {
            options: {
                banner: '/*! <%= pkg.name %> <%= grunt.template.today("yyyy-mm-dd") %> */\n'
            },
            build: {
                src: 'src/<%= pkg.name %>.js',
                dest: '<%= target %>/<%= pkg.name %>.min.js'
            }		
				},
				watch: {
        	files: ['**/*.js', '**/*.html', '**/*.css'],
          tasks: ['default'],
					options: {
					  livereload: true
					}
        }
    });

		// Load the plugin that provides the "copy" task.
		grunt.loadNpmTasks('grunt-contrib-copy');

    // Load the plugin that provides the "uglify" task.
    grunt.loadNpmTasks('grunt-contrib-uglify');

    // Load the plugin that provides the "watch" task.
    grunt.loadNpmTasks('grunt-contrib-watch');

    // Default task(s).
    grunt.registerTask('default', ['uglify','copy']);

    // Dev
    grunt.registerTask('start', [
      'watch'
    ]);
		
};
