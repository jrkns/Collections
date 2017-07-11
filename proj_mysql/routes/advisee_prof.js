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
  var q = connection.query('SELECT Student.sid,gender,fname,sname,email,tel,probation,studyabroad,leaving FROM (Student JOIN Student_Status ON Student.sid = Student_Status.sid) JOIN Advisor ON Advisor.sid = Student.sid WHERE pab = \'' + req.user.username + '\';', function selectCb(err, results, fields) {
    if (err) {
      throw err;
    }
    console.log(q.sql);
    console.log(results);
    res.render('advisee_prof', {
      title: 'Advisees List',
      user_val: req.user.username,
      results: results
    });
  })
});

module.exports = router;
