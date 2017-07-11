var express = require('express');
var router = express.Router();

/* GET home page. */
router.get('/', function(req, res, next) {
  res.redirect('/unauthorized');
  //res.render('report_staff_ic', { title: 'Report', user_val: req.user.username });
});

module.exports = router;
