var express = require('express');
var router = express.Router();

/* GET home page. */
router.get('/', function(req, res, next) {
  res.render('login_prof_f', { title: 'Home' });
});

module.exports = router;
