var http = require('http');

var HandleRequest = function(request,response){
    console.log('Получен запрос на URL:' + request.url);
    response.writeHead(200);
    response.end('Hellow World!');
};
var www = http.createServer(HandleRequest);
www.listen(8080);