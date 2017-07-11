var express = require('express');
var router = express.Router();

/* GET home page. */
router.get('/', function(req, res, next) {
  res.redirect('/unauthorized');
  //res.render('report_prof_is', { title: 'Report', user_val: req.user.username });
});

module.exports = router;
