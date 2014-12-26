var tessel = require('tessel');
var rfidlib = require('rfid-pn532');
var rfid = rfidlib.use(tessel.port['A']);
var mqtt = require('mqtt');
var http = require('http');
var client = mqtt.createClient(15351, 'm20.cloudmqtt.com', {
	username : "vidqukjx",
	password : "trSN5DfvT2Gl",
	clientId : "tessel"
});
var led1 = tessel.led[0].output(0);
var led2 = tessel.led[1].output(0);


rfid.on('ready', function (version) {
	console.log('Ready to read RFID card');

	rfid.on('data', function(card) {
		console.log('UID:', card.uid.toString('hex'));
		client.publish('nfc',card.uid.toString('hex'));

		led1.toggle();
		setTimeout(function(){
			led1.toggle();
		},500);

		var options = {
			headers: {
				'Content-Type': 'application/json'
			},
		  hostname: '192.168.178.27',
		  port: 8080,
		  path: '/api/rfidItems/last',
		  method: 'POST'
		};

		var req = http.request(options, function(res) {
		  console.log('STATUS: ' + res.statusCode);
		  console.log('HEADERS: ' + JSON.stringify(res.headers));
		  res.setEncoding('utf8');
		  res.on('data', function (chunk) {
		    console.log('BODY: ' + chunk);
		  });
		});

		req.on('error', function(e) {
		  console.log('problem with request: ' + e.message);
		});

		// write data to request body
		var data = {};
		data.id = card.uid.toString('hex');
		data.name = "";
		req.write(JSON.stringify(data));
		req.end();

	});
});

rfid.on('error', function (err) {
	console.error(err);
});
