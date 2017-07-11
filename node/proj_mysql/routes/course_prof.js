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
  var q = connection.query('SELECT * FROM Teach JOIN Course ON Teach.cid = Course.cid  WHERE Teach.pab = \'' + req.user.username + '\';'  , function selectCb(err, results, fields) {
    if (err) {
      throw err;
    }
    console.log(q.sql);
    console.log(results);
    res.render('course_prof', {
      title: 'Courses Manager',
      user_val: req.user.username,
      results: results
    });
  })
});


module.exports = router;
