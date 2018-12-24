# Spring-task
port: 9191

/createAccount POST
{
	"userName": "Test User"
}

/topUp POST
{
	"userName": "Test User",
	"amount": 100
}

/withdraw POST
{
	"userName": "Test User",
	"amount": 1
}

/transfer POST
{
	"senderUserName": "Test User",
	"receiverUserName": "Test User 2",
	"amount": 100500.40
}
