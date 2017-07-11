var express = require('express');
var router = express.Router();

/* GET home page. */
router.get('/', function(req, res, next) {
  if (req.user.username.length <= 3) { res.redirect('/unauthorized'); return; }
  res.render('search_info', { title: 'Search', user_val: req.user.username, notfound_str: '' });
});

module.exports = router;
