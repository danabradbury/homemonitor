var gcm = require('node-gcm');

var message = new gcm.Message({
    data: {
        key1: 'PhoneGap Build rocks!',
		title: 'PhoneGap Build rocks!',
		message: 'PhoneGap Build rocks!'
    },
    notification: {
        title: "PhoneGap Build rocks!",
        icon: "ic_launcher",
        body: "This is a notification that will be displayed ASAP."
    },
	sound : 'beep.wav'
});


var regIds = ['APA91bENtnLbamGMQa9I7AqB28svmttUz-YTFtEGpqTBV6S_k83I6XR5Ma1DeLUU_o-xUHd_Sow3uZi4ixXIiEaCjz4uLMMuix1yHaCNY7gvQhne8d37YSWn0gN9gsrg8tjF3GGcBTob'];

// Set up the sender with you API key
var sender = new gcm.Sender('AIzaSyCET8o3rFTzSt8y8Qdli8PcENpaNGnp7-c');

// Now the sender can be used to send messages
sender.send(message, { registrationIds: regIds }, function (err, result) {
    if(err) console.error(err);
    else    console.log(result);
});

// Send to a topic, with no retry this time
sender.sendNoRetry(message, { topic: '/topics/global' }, function (err, result) {
    if(err) console.error(err);
    else    console.log(result);
});