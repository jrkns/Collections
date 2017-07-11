var express = require('express');
var router = express.Router();

// mySQL connection
var app = require('../app');
var mysql = require('mysql');
var connection = mysql.createConnection(app.options);
connection.connect();

/* GET home page. */
router.get('/', function(req, res, next) {
  if (req.user.username.length <= 3) { res.redirect('/unauthorized'); return; }
  var q = connection.query(
    'SELECT fname AS info FROM Staff WHERE ssn=\'' + req.user.username
  + '\' UNION ALL SELECT sname FROM Staff WHERE ssn=\'' + req.user.username
  + '\' UNION ALL SELECT role FROM Staff WHERE ssn=\'' + req.user.username
  + '\' UNION ALL SELECT Faculty.fname AS n FROM Faculty JOIN Staff ON Staff.fid = Faculty.fid WHERE Staff.ssn=\'' + req.user.username
  + '\' UNION ALL SELECT dname FROM Department JOIN Staff ON Staff.did = Department.did WHERE Staff.ssn=\'' + req.user.username + '\';', function selectCb(err, results, fields) {
    if (err) {
      throw err;
    }
    console.log(q.sql);
    console.log(results);
    res.render('index_staff', {
      title: 'Home',
      user_val: req.user.username,
      results: results
    });
  })
});
module.exports = router;
