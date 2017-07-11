var express = require('express');
var router = express.Router();

/* GET home page. */
router.get('/', function(req, res, next) {
  res.render('edit_record', { title: 'Edit Record', user_val: req.user.username });
});

module.exports = router;
