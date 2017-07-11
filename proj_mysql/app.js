// !!!!!!!!!!!!!!!!!! Setup mySQL Password Here !!!!!!!!!!!!!!!!!!!!!
var options = {
    host: 'localhost',
    user: 'root',
    password: '12761',
    database: 'DB_Project'
};

exports.options = options;

var express = require('express');
var path = require('path');
var favicon = require('serve-favicon');
var logger = require('morgan');
var cookieParser = require('cookie-parser');
var bodyParser = require('body-parser');
var mysql = require('mysql');

// Authentication
SALT_WORK_FACTOR = 12;
var user = require('./routes/user');
var db = require('./models');
var passport = require('passport');
var passportConfig = require('./config/passport');
var session = require('express-session');
var application = require('./routes/application');
var unauthorized = require('./routes/unauthorized');
var MySQLStore = require('express-mysql-session')(session);

var index = require('./routes/index');
var about = require('./routes/about');
var login_prof_f = require('./routes/login_prof_f');
var login_staff = require('./routes/login_staff');
var login_staff_f = require('./routes/login_staff_f');

var index_prof = require('./routes/index_prof');
var index_bprof = require('./routes/index_bprof');
var profile_prof = require('./routes/profile_prof');
var course_prof = require('./routes/course_prof');
var course_manage_prof = require('./routes/course_manage_prof');
var advisee_prof = require('./routes/advisee_prof');
var project_prof = require('./routes/project_prof');
var search_info_prof = require('./routes/search_info_prof');
var report_prof = require('./routes/report_prof');

var index_staff = require('./routes/index_staff');
var profile_staff = require('./routes/profile_staff');
var edit_record = require('./routes/edit_record');
var report_staff = require('./routes/report_staff');
var search_info = require('./routes/search_info');

var app = express();

// view engine setup
app.set('views', path.join(__dirname, 'views'));
app.set('view engine', 'pug');

// uncomment after placing your favicon in /public
//app.use(favicon(path.join(__dirname, 'public', 'favicon.ico')));
//app.use(logger('dev'));
app.use(bodyParser.json());
app.use(bodyParser.urlencoded({ extended: false }));
app.use(cookieParser());
app.use(express.static(path.join(__dirname, 'public')));
app.use(session({ key: 'session_cookie_name',
    secret: 'session_cookie_secret',
    store: sessionStore,
    resave: false,
    saveUninitialized: true, }));
app.use(passport.initialize());
app.use(passport.session());

// Session store
var sessionStore = new MySQLStore(options);

// Professor Authenticate Checker
app.get('/index_prof', application.IsAuthenticated, index_prof);
app.get('/index_bprof', application.IsAuthenticated, index_bprof);
app.get('/profile_prof', application.IsAuthenticated, profile_prof);
app.get('/course_prof', application.IsAuthenticated, course_prof);
app.get('/course_manage_prof', application.IsAuthenticated, course_manage_prof);
app.get('/advisee_prof', application.IsAuthenticated, advisee_prof);
app.get('/project_prof', application.IsAuthenticated, project_prof);
app.get('/search_info_prof', application.IsAuthenticated, search_info_prof);
app.get('/report_prof', application.IsAuthenticated, report_prof);

// Staff Authenticate Checker
app.get('/index_staff', application.IsAuthenticated, index_staff);
app.get('/profile_staff', application.IsAuthenticated, profile_staff);
app.get('/edit_record', application.IsAuthenticated, edit_record);
app.get('/report_staff', application.IsAuthenticated, report_staff);
app.get('/search_info', application.IsAuthenticated, search_info);

// Authentication Listener
app.post('/authenticate_prof',
  passport.authenticate('local',{
	successRedirect: '/index_prof',
	failureRedirect: '/login_prof_f',
  })
);

app.post('/authenticate_staff',
  passport.authenticate('local',{
	successRedirect: '/index_staff',
	failureRedirect: '/login_staff_f',
  })
);

// Logout Listener
app.get('/logout', application.destroySession);

var connection = mysql.createConnection(options);
connection.connect();

// Query Listener
var edit_record_query = require('./routes/edit_record_query');
app.use('/edit_record_query', edit_record_query);
app.post('/edit_record_query', function(req, res){
    connection.query(req.body.input_query,
    function (err, results) {
      if (err) {
        console.error(err);
        res.render('edit_record_query', { title: 'Edit Record',results: err , user_val: req.user.username, comment: 'ERROR : You should take 2110422 DB MGT SYS DESIGN course before using this!',color_var: 'red',previous: req.body.input_query});
        return;
      }
      console.log(results);
      res.render('edit_record_query', { title: 'Edit Record', user_val: req.user.username, results: results, comment: 'SUCCESS : SQL statements is executed.', color_var: 'green', previous: req.body.input_query});
    }
  );
});

var search_info_s = require('./routes/search_info_s');
app.use('/search_info_s', search_info_s);
app.post('/search_info_s', function(req, res){
    connection.query('SELECT * FROM Student WHERE fname=\''
    + req.body.input_fname + '\' OR sid=\'' + req.body.input_sid +'\' OR sname=\''
    + req.body.input_sname + '\' OR email=\'' + req.body.input_email +'\' OR tel=\''
    + req.body.input_tel + '\' OR ssn=\'' + req.body.input_ssn + '\' OR syear = \'' + (2017-req.body.input_year) +'\';',
    function (err, results) {
      if (err) {
        console.error(err);
        return;
      }
      if (results.length <= 0) {
        console.log(results);
        res.render('search_info', { title: 'Search', user_val: req.user.username, notfound_str: 'Search results not found.' });
      }
      console.log(results);
      res.render('search_info_s', { title: 'Search', user_val: req.user.username, results: results });
    }
  );
});

var search_info_p = require('./routes/search_info_p');
app.use('/search_info_p', search_info_p);
app.post('/search_info_p', function(req, res){
    connection.query('SELECT * FROM Professor WHERE fname=\''
    + req.body.input_fname + '\' OR pab=\'' + req.body.input_pab +'\' OR sname=\''
    + req.body.input_sname + '\' OR email=\'' + req.body.input_email +'\' OR tel=\''
    + req.body.input_tel + '\' OR ssn=\'' + req.body.input_ssn + '\';',
    function (err, results) {
      if (err) {
        console.error(err);
        return;
      }
      if (results.length <= 0) {
        console.log(results);
        res.render('search_info', { title: 'Search', user_val: req.user.username, notfound_str: 'Search results not found.' });
      }
      console.log(results);
      res.render('search_info_p', { title: 'Search', user_val: req.user.username, results: results });
    }
  );
});

var search_info_f = require('./routes/search_info_f');
app.use('/search_info_f', search_info_f);
app.post('/search_info_f', function(req, res){
    connection.query('SELECT * FROM Staff WHERE fname=\''
    + req.body.input_fname + '\' OR role=\'' + req.body.input_role +'\' OR sname=\''
    + req.body.input_sname + '\' OR email=\'' + req.body.input_email +'\' OR tel=\''
    + req.body.input_tel + '\' OR ssn=\'' + req.body.input_ssn + '\';',
    function (err, results) {
      if (err) {
        console.error(err);
        return;
      }
      if (results.length <= 0) {
        console.log(results);
        res.render('search_info', { title: 'Search', user_val: req.user.username, notfound_str: 'Search results not found.' });
      }
      console.log(results);
      res.render('search_info_f', { title: 'Search', user_val: req.user.username, results: results });
    }
  );
});

var report_staff_e = require('./routes/report_staff_e');
app.use('/report_staff_e', report_staff_e);
app.post('/report_staff_e', function(req, res){
    var query_str = 'SELECT * FROM Enroll JOIN Course ON Enroll.cid = Course.cid WHERE sid = \'' + req.body.input_e_sid + '\' AND eyear = \''+ req.body.input_e_eyear + '\' AND semester = \''+ req.body.input_e_semester +'\';';
    if (req.body.input_e_eyear == 'All' && req.body.input_e_semester == 'All') {
      query_str = 'SELECT * FROM Enroll JOIN Course ON Enroll.cid = Course.cid WHERE sid = \'' + req.body.input_e_sid + '\';';
    } else if (req.body.input_e_eyear == 'All') {
      query_str = 'SELECT * FROM Enroll JOIN Course ON Enroll.cid = Course.cid WHERE sid = \'' + req.body.input_e_sid + '\' AND semester = \''+ req.body.input_e_semester +'\';';
    } else if (req.body.input_e_semester == 'All') {
      query_str = 'SELECT * FROM Enroll JOIN Course ON Enroll.cid = Course.cid WHERE sid = \'' + req.body.input_e_sid + '\' AND eyear = \''+ req.body.input_e_eyear +'\';';
    }
    var q = connection.query(query_str,
    function (err, results) {
      if (err) {
        console.error(err);
        return;
      }
      console.log(q.sql);
      if (results.length <= 0) {
        console.log(results);
        res.render('report_staff', { title: 'Report', user_val: req.user.username, notfound_str: 'Search results not found.' });
      }
      console.log(results);
      res.render('report_staff_e', { title: 'Enrollment Report', user_val: req.user.username, results: results });
    }
  );
});

var report_staff_a = require('./routes/report_staff_a');
app.use('/report_staff_a', report_staff_a);
app.post('/report_staff_a', function(req, res){
    var q = connection.query('SELECT *,DATE_FORMAT(fromd, \'%d/%m/%Y\') AS fromd2,DATE_FORMAT(tod, \'%d/%m/%Y\') AS tod2 FROM Join_Activity JOIN Activity ON Join_Activity.aid = Activity.aid WHERE Join_Activity.sid = \'' + req.body.input_a_sid + '\';',
    function (err, results) {
      if (err) {
        console.error(err);
        return;
      }
      console.log(q.sql);
      if (results.length <= 0) {
        console.log(results);
        res.render('report_staff', { title: 'Report', user_val: req.user.username, notfound_str: 'Search results not found.' });
      }
      console.log(results);
      res.render('report_staff_a', { title: 'Activity Report', user_val: req.user.username, results: results });
    }
  );
});

var report_staff_p = require('./routes/report_staff_p');
app.use('/report_staff_p', report_staff_p);
app.post('/report_staff_p', function(req, res){
    var q = connection.query('SELECT *,DATE_FORMAT(bdate, \'%d/%m/%Y\') AS bdate2 FROM Rule JOIN Parent ON Rule.ssn = Parent.ssn JOIN Postalcode ON Postalcode.district = Parent.district WHERE Rule.sid = \'' + req.body.input_p_sid + '\';',
    function (err, results) {
      if (err) {
        console.error(err);
        return;
      }
      console.log(q.sql);
      if (results.length <= 0) {
        console.log(results);
        res.render('report_staff', { title: 'Report', user_val: req.user.username, notfound_str: 'Search results not found.' });
      }
      console.log(results);
      res.render('report_staff_p', { title: 'Parent Report', user_val: req.user.username, results: results });
    }
  );
});

var report_staff_is = require('./routes/report_staff_is');
app.use('/report_staff_is', report_staff_is);
app.post('/report_staff_is', function(req, res){
    var q = connection.query('SELECT *,Intern.email AS cemail,Intern.tel AS ctel,DATE_FORMAT(fromd, \'%d/%m/%Y\') AS fromd2,DATE_FORMAT(tod, \'%d/%m/%Y\') AS tod2 FROM Intern JOIN Company ON Intern.coid = Company.coid JOIN Student ON Student.sid = Intern.sid JOIN Postalcode ON Postalcode.district = Company.district WHERE Student.sid = \'' + req.body.input_is_sid + '\';',
    function (err, results) {
      if (err) {
        console.error(err);
        return;
      }
      console.log(q.sql);
      if (results.length <= 0) {
        console.log(results);
        res.render('report_staff', { title: 'Report', user_val: req.user.username, notfound_str: 'Search results not found.' });
      }
      console.log(results);
      res.render('report_staff_is', { title: 'Internship Report', user_val: req.user.username, results: results });
    }
  );
});

var report_staff_ic = require('./routes/report_staff_ic');
app.use('/report_staff_ic', report_staff_ic);
app.post('/report_staff_ic', function(req, res){
    var q = connection.query('SELECT *,DATE_FORMAT(fromd, \'%d/%m/%Y\') AS fromd2,DATE_FORMAT(tod, \'%d/%m/%Y\') AS tod2 FROM Intern JOIN Company ON Intern.coid = Company.coid JOIN Student ON Student.sid = Intern.sid WHERE Company.coid = \'' + req.body.input_ic_cid + '\' OR Company.cname = \''+  req.body.input_ic_cname +'\';',
    function (err, results) {
      if (err) {
        console.error(err);
        return;
      }
      console.log(q.sql);
      if (results.length <= 0) {
        console.log(results);
        res.render('report_staff', { title: 'Report', user_val: req.user.username, notfound_str: 'Search results not found.' });
      }
      console.log(results);
      res.render('report_staff_ic', { title: 'Internship Report', user_val: req.user.username, results: results });
    }
  );
});

var report_staff_c = require('./routes/report_staff_c');
app.use('/report_staff_c', report_staff_e);
app.post('/report_staff_c', function(req, res){
    var query_str = 'SELECT Course.cid,Course.cname,Course.credit,Department.dname,Faculty.fname AS faname,Enroll.eyear,Enroll.semester,Enroll.section,Teach.pab,Enroll.grade,Student.fname,Student.sname,Student.sid FROM Course JOIN Teach ON Course.cid = Teach.cid JOIN Professor ON Professor.pab = Teach.pab JOIN Enroll ON Enroll.cid = Course.cid JOIN Student ON Student.sid = Enroll.sid JOIN Department ON Department.did = Course.did JOIN Faculty ON Faculty.fid = Course.fid WHERE Course.cid = \'' + req.body.input_c_cid + '\';';
    var q = connection.query(query_str,
    function (err, results) {
      if (err) {
        console.error(err);
        return;
      }
      console.log(q.sql);
      console.log(results);
      if (results.length <= 0) {
        console.log(results);
        res.render('report_staff', { title: 'Report', user_val: req.user.username, notfound_str: 'Search results not found.' });
      }
      console.log(results);
      res.render('report_staff_c', { title: 'Course Report', user_val: req.user.username, results: results });
    }
  );
});

var search_info_prof_s = require('./routes/search_info_prof_s');
app.use('/search_info_prof_s', search_info_prof_s);
app.post('/search_info_prof_s', function(req, res){
    var q = connection.query('SELECT * FROM Student WHERE fname=\''
    + req.body.input_fname + '\' OR sid=\'' + req.body.input_sid +'\' OR sname=\''
    + req.body.input_sname + '\' OR email=\'' + req.body.input_email +'\' OR tel=\''
    + req.body.input_tel + '\' OR ssn=\'' + req.body.input_ssn + '\' OR syear = \'' + (2017-req.body.input_year) +'\';',
    function (err, results) {
      if (err) {
        console.error(err);
        return;
      }
      console.log(q.sql);
      if (results.length <= 0) {
        console.log(results);
        res.render('search_info_prof', { title: 'Search', user_val: req.user.username, notfound_str: 'Search results not found.' });
      }
      console.log(results);
      res.render('search_info_prof_s', { title: 'Search', user_val: req.user.username, results: results });
    }
  );
});

var search_info_prof_p = require('./routes/search_info_prof_p');
app.use('/search_info_prof_p', search_info_prof_p);
app.post('/search_info_prof_p', function(req, res){
    var q = connection.query('SELECT * FROM Professor WHERE fname=\''
    + req.body.input_fname + '\' OR pab=\'' + req.body.input_pab +'\' OR sname=\''
    + req.body.input_sname + '\' OR email=\'' + req.body.input_email +'\' OR tel=\''
    + req.body.input_tel + '\' OR ssn=\'' + req.body.input_ssn + '\';',
    function (err, results) {
      if (err) {
        console.error(err);
        return;
      }
      console.log(q.sql);
      if (results.length <= 0) {
        console.log(results);
        res.render('search_info_prof', { title: 'Search', user_val: req.user.username, notfound_str: 'Search results not found.' });
      }
      console.log(results);
      res.render('search_info_prof_p', { title: 'Search', user_val: req.user.username, results: results });
    }
  );
});

var search_info_prof_f = require('./routes/search_info_prof_f');
app.use('/search_info_prof_f', search_info_prof_f);
app.post('/search_info_prof_f', function(req, res){
    var q = connection.query('SELECT * FROM Staff WHERE fname=\''
    + req.body.input_fname + '\' OR role=\'' + req.body.input_role +'\' OR sname=\''
    + req.body.input_sname + '\' OR email=\'' + req.body.input_email +'\' OR tel=\''
    + req.body.input_tel + '\' OR ssn=\'' + req.body.input_ssn + '\';',
    function (err, results) {
      if (err) {
        console.error(err);
        return;
      }
      console.log(q.sql);
      if (results.length <= 0) {
        console.log(results);
        res.render('search_info_prof', { title: 'Search', user_val: req.user.username, notfound_str: 'Search results not found.' });
      }
      console.log(results);
      res.render('search_info_prof_f', { title: 'Search', user_val: req.user.username, results: results });
    }
  );
});

var report_prof_e = require('./routes/report_prof_e');
app.use('/report_prof_e', report_prof_e);
app.post('/report_prof_e', function(req, res){
    var query_str = 'SELECT * FROM Enroll JOIN Course ON Enroll.cid = Course.cid WHERE sid = \'' + req.body.input_e_sid + '\' AND eyear = \''+ req.body.input_e_eyear + '\' AND semester = \''+ req.body.input_e_semester +'\';';
    if (req.body.input_e_eyear == 'All' && req.body.input_e_semester == 'All') {
      query_str = 'SELECT * FROM Enroll JOIN Course ON Enroll.cid = Course.cid WHERE sid = \'' + req.body.input_e_sid + '\';';
    } else if (req.body.input_e_eyear == 'All') {
      query_str = 'SELECT * FROM Enroll JOIN Course ON Enroll.cid = Course.cid WHERE sid = \'' + req.body.input_e_sid + '\' AND semester = \''+ req.body.input_e_semester +'\';';
    } else if (req.body.input_e_semester == 'All') {
      query_str = 'SELECT * FROM Enroll JOIN Course ON Enroll.cid = Course.cid WHERE sid = \'' + req.body.input_e_sid + '\' AND eyear = \''+ req.body.input_e_eyear +'\';';
    }
    var q = connection.query(query_str,
    function (err, results) {
      if (err) {
        console.error(err);
        return;
      }
      console.log(q.sql);
      if (results.length <= 0) {
        console.log(results);
        res.render('report_prof', { title: 'Report', user_val: req.user.username, notfound_str: 'Search results not found.' });
      }
      console.log(results);
      res.render('report_prof_e', { title: 'Enrollment Report', user_val: req.user.username, results: results });
    }
  );
});

var report_prof_a = require('./routes/report_prof_a');
app.use('/report_prof_a', report_prof_a);
app.post('/report_prof_a', function(req, res){
    var q = connection.query('SELECT *,DATE_FORMAT(fromd, \'%d/%m/%Y\') AS fromd2,DATE_FORMAT(tod, \'%d/%m/%Y\') AS tod2 FROM Join_Activity JOIN Activity ON Join_Activity.aid = Activity.aid WHERE Join_Activity.sid = \'' + req.body.input_a_sid + '\';',
    function (err, results) {
      if (err) {
        console.error(err);
        return;
      }
      console.log(q.sql);
      if (results.length <= 0) {
        console.log(results);
        res.render('report_prof', { title: 'Report', user_val: req.user.username, notfound_str: 'Search results not found.' });
      }
      console.log(results);
      res.render('report_prof_a', { title: 'Activity Report', user_val: req.user.username, results: results });
    }
  );
});

var report_prof_p = require('./routes/report_prof_p');
app.use('/report_prof_p', report_prof_p);
app.post('/report_prof_p', function(req, res){
    var q = connection.query('SELECT *,DATE_FORMAT(bdate, \'%d/%m/%Y\') AS bdate2 FROM Rule JOIN Parent ON Rule.ssn = Parent.ssn JOIN Postalcode ON Postalcode.district = Parent.district WHERE Rule.sid = \'' + req.body.input_p_sid + '\';',
    function (err, results) {
      if (err) {
        console.error(err);
        return;
      }
      console.log(q.sql);
      if (results.length <= 0) {
        console.log(results);
        res.render('report_prof', { title: 'Report', user_val: req.user.username, notfound_str: 'Search results not found.' });
      }
      console.log(results);
      res.render('report_prof_p', { title: 'Parent Report', user_val: req.user.username, results: results });
    }
  );
});

var report_prof_is = require('./routes/report_prof_is');
app.use('/report_prof_is', report_prof_is);
app.post('/report_prof_is', function(req, res){
    var q = connection.query('SELECT *,Intern.email AS cemail,Intern.tel AS ctel,DATE_FORMAT(fromd, \'%d/%m/%Y\') AS fromd2,DATE_FORMAT(tod, \'%d/%m/%Y\') AS tod2 FROM Intern JOIN Company ON Intern.coid = Company.coid JOIN Student ON Student.sid = Intern.sid JOIN Postalcode ON Postalcode.district = Company.district WHERE Student.sid = \'' + req.body.input_is_sid + '\';',
    function (err, results) {
      if (err) {
        console.error(err);
        return;
      }
      console.log(q.sql);
      if (results.length <= 0) {
        console.log(results);
        res.render('report_prof', { title: 'Report', user_val: req.user.username, notfound_str: 'Search results not found.' });
      }
      console.log(results);
      res.render('report_prof_is', { title: 'Internship Report', user_val: req.user.username, results: results });
    }
  );
});

var report_prof_ic = require('./routes/report_prof_ic');
app.use('/report_prof_ic', report_prof_ic);
app.post('/report_prof_ic', function(req, res){
    var q = connection.query('SELECT *,DATE_FORMAT(fromd, \'%d/%m/%Y\') AS fromd2,DATE_FORMAT(tod, \'%d/%m/%Y\') AS tod2 FROM Intern JOIN Company ON Intern.coid = Company.coid JOIN Student ON Student.sid = Intern.sid WHERE Company.coid = \'' + req.body.input_ic_cid + '\' OR Company.cname = \''+  req.body.input_ic_cname +'\';',
    function (err, results) {
      if (err) {
        console.error(err);
        return;
      }
      console.log(q.sql);
      console.log(results);
      if (results.length <= 0) {
        res.render('report_prof', { title: 'Report', user_val: req.user.username, notfound_str: 'Search results not found.' });
      }
      res.render('report_prof_ic', { title: 'Internship Report', user_val: req.user.username, results: results });
    }
  );
});

app.use('/', index);
app.use('/index', index);
app.use('/user', user);
app.use('/about', about);
app.use('/unauthorized', unauthorized);
app.use('/login_prof_f', login_prof_f);
app.use('/login_staff_f', login_staff_f);

app.use('/index_prof', index_prof);
app.use('/index_bprof', index_bprof);
app.use('/profile_prof', profile_prof);
app.use('/course_prof', course_prof);
app.use('/course_manage_prof', course_manage_prof);
app.use('/advisee_prof', advisee_prof);
app.use('/project_prof', project_prof);
app.use('/search_info_prof', search_info_prof);
app.use('/report_prof', report_prof);

app.use('/index_staff', index_staff);
app.use('/profile_staff', profile_staff);
app.use('/edit_record', edit_record);
app.use('/report_staff', report_staff);
app.use('/search_info', search_info);
app.use('/login_staff', login_staff);

// Crate admin default user
db
  .sequelize
  .sync()
  .complete(function(err){
	if (err) {
		throw err[0]
	} else {
		db.User.find({where: {username: 'PFS'}}).success(function (user){
			if (!user) {
				db.User.build({username: 'PFS', password: 'ggez'}).save();
			};
		});
    db.User.find({where: {username: 'BPF'}}).success(function (user){
			if (!user) {
				db.User.build({username: 'BPF', password: 'ggez'}).save();
			};
		});
    db.User.find({where: {username: '0000000000007'}}).success(function (user){
			if (!user) {
				db.User.build({username: '0000000000007', password: 'ggez'}).save();
			};
		});
	}
})


// catch 404 and forward to error handler
app.use(function(req, res, next) {
  var err = new Error('Not Found');
  err.status = 404;
  next(err);
});

// error handler
app.use(function(err, req, res, next) {
  // set locals, only providing error in development
  res.locals.message = err.message;
  res.locals.error = req.app.get('env') === 'development' ? err : {};

  // render the error page
  res.status(err.status || 500);
  res.render('error');
});

module.exports = app;
