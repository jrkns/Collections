var express = require('express');
var router = express.Router();

// mySQL connection
var app = require('../app');
var mysql = require('mysql');
var connection = mysql.createConnection(app.options);
connection.connect();

/* GET home page. */
router.get('/', function(req, res, next) {
  if (req.user.username.length > 3) { res.redirect('/unauthorized'); return; }
  var q = connection.query('SELECT Project.pjid,pname,field,description,sid,fname,sname,email,tel FROM (Project JOIN Control_Project ON Project.pjid = Control_Project.pjid) JOIN Undergraduate ON Undergraduate.pjid = Project.pjid WHERE pab = \'' + req.user.username + '\';'  , function selectCb(err, results, fields) {
    if (err) {
      throw err;
    }
    console.log(q.sql);
    console.log(results);
    res.render('project_prof', {
      title: 'Projects List',
      user_val: req.user.username,
      results: results
    });
  })
});

module.exports = router;
