var express = require('express');
var router = express.Router();

/* GET home page. */
router.get('/', function(req, res, next) {
  res.render('monitor_staff', { title: 'Monitoring', user_val: req.user.username });
});

module.exports = router;
