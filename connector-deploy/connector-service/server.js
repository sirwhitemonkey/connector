var path = require('path');
var express = require('express');
var app = express();

var port = process.env.PORT || 3002;

var application_root = __dirname;

//CORS middleware
var allowCrossDomain = function(req, res, next) {
    res.header("Access-Control-Allow-Origin", "*");
    res.header("Access-Control-Allow-Headers", "X-Frame-Options,Access-Control-Allow-Origin, Access-Control-Allow-Headers, Accept, Accept-Version, Content-Length, Content-MD5, Content-Type, Date, X-Auth-Token,  Host");
    next();
};

//app.use(allowCrossDomain);
app.use(express.static(path.join(application_root, 'app')));

app.listen(port);
console.log('eCommerceConnector service listening on port ' + port);

