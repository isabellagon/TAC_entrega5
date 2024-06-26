var createError = require('http-errors');
var express = require('express');
var path = require('path');
var cookieParser = require('cookie-parser');
var logger = require('morgan');
const mongoose = require('mongoose');

var indexRouter = require('./routes/index');
var eventoRouter = require('./routes/evento');
var userRouter = require('./routes/users')

require('dotenv').config()

var app = express();

mongoose.connect("mongodb+srv://ana:rW3DXj5McBr9rWM8@cluster0.ysxb7kl.mongodb.net/?retryWrites=true&w=majority&appName=Cluster0")
.then(() => {
  console.log("MongoDB conectado, Uhhh")
})
.catch((err) => {
  console.log("MongoDB não conectado");
  console.log(err)
});

// view engine setup
app.set('views', path.join(__dirname, 'views'));
app.set('view engine', 'jade');

app.use(logger('dev'));
app.use(express.json());
app.use(express.urlencoded({ extended: false }));
app.use(cookieParser());
app.use(express.static(path.join(__dirname, 'public')));

app.use('/', indexRouter);
app.use('/evento', eventoRouter);
app.use('/users', userRouter);
app.use('/auth', require('./routes/auth'))

// catch 404 and forward to error handler
app.use(function(req, res, next) {
  next(createError(404));
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
