var app = require('../app');

var fs = require('fs')
 , path = require('path')
 , Sequelize = require('sequelize')
 , lodash = require('lodash')
 , sequelize = new Sequelize(app.options.database, app.options.user, app.options.password)
 , db = {}

fs
  .readdirSync(__dirname)
  .filter(function(file) {
	return (file.indexOf('.') !== 0) && (file !== 'index.js')
   })
   .forEach(function(file) {
   	 var model = sequelize.import(path.join(__dirname, file))
	 db[model.name] = model
   })

module.exports = lodash.extend({
	sequelize: sequelize,
	Sequelize: Sequelize
}, db)
