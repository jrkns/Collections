exports.IsAuthenticated = function(req, res, next){
	if(req.isAuthenticated()){
		next();
	} else {
		res.redirect('/unauthorized');
	}
}

exports.destroySession = function(req, res, next) {
	req.logout();
	req.session.destroy(function(err){
     if(err){
        console.log(err);
     } else {
			 	console.log('Log out');
        res.redirect('/');
     }
  });
}
