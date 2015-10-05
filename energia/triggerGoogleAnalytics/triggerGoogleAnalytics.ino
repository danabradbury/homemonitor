/*
used to send events or page hits to GA based on digital input states
 */

//GA tracking id UA-30396326-3

//example measurement protocal page view
//http://www.google-analytics.com/collect?v=1&tid=UA-30396326-3&cid=123&t=pageview&dp=%2FsumpPump

//example measurement protocal event
//http://www.google-analytics.com/collect?v=1&tid=UA-30396326-3&cid=123&t=event&ec=home%20movement&ea=door%20open&el=bedroom

#include <SPI.h>
#include <Ethernet.h>

// Enter a MAC address for your controller below.
// Newer Ethernet shields have a MAC address printed on a sticker on the shield
byte mac[] = {  0x00, 0x1A, 0x96, 0x02, 0xAF, 0x23 };
//IPAddress server(173,194,33,104); // Google
IPAddress server(216,58,217,132); // Google Analytics

// Initialize the Ethernet client library
// with the IP address and port of the server 
// that you want to connect to (port 80 is default for HTTP):
EthernetClient client;

// digital pin PJ_0 and PJ_1 have a pushbuttons attached. 
// These pins alos have constants defined for convience, PUSH1 and PUSH2
//lets give them a name in case we want to swtich to different inputs
int input1 = PUSH1;
int input2 = PUSH2;

// the setup routine runs once when you press reset:
void setup() {
  // initialize serial communication at 9600 bits per second:
  Serial.begin(9600); // msp430g2231 must use 4800
  
  // make the on-board pushbutton's pin an input pullup:
  pinMode(input1, INPUT_PULLUP);// released = HIGH, pressed = LOW
  pinMode(input2, INPUT_PULLUP);
  
  pinMode(D1_LED, OUTPUT);
  pinMode(D2_LED, OUTPUT);
  
  // start the Ethernet connection:
  if (Ethernet.begin(mac) == 0) {
    Serial.println("Failed to configure Ethernet using DHCP");
    // no point in carrying on, so do nothing forevermore:
    for(;;)
      ;
  }
  
  // give the Ethernet a second to initialize:
  delay(1000);
  Serial.println("connecting on startup...");

  // if you get a connection, report back via serial:
  if (client.connect(server, 80)) {
    Serial.println("connected");
    client.println("GET www.google-analytics.com/collect?v=1&tid=UA-30396326-3&cid=123&t=event&ec=home&ea=door%20open HTTP/1.0");
    client.println();
    delay(3000);
    //for debugging dump the response to the serial
    while (client.available()) {
      char c = client.read();
      Serial.print(c);
    }
    Serial.println();
    Serial.println("startup event fired");
  } 
  else {
    // didn't get a connection to the server:
    Serial.println("connection failed");
  }
   
}

// the loop routine runs over and over again forever:
void loop() {
  
  // read push button states:
  int button1State = digitalRead(input1);
  int button2State = digitalRead(input2);
  
  boolean flag = false;
  
  if(button1State){
   digitalWrite(D1_LED, LOW); 
   flag = false;
  }else if(!flag){
    digitalWrite(D1_LED, HIGH); 
    // send an event to GA, button state low is active, lets only
    //fire an event when a button is pushed
    fireGaEvent(1);
    flag = true;
  }
  if(button2State){
   digitalWrite(D2_LED, LOW); 
  }else{
   digitalWrite(D2_LED, HIGH);
   // send an event to GA, button state low is active, lets only
   //fire an event when a button is pushed
   fireGaEvent(2); 
  }
  
  // print out the state of the button:
  //Serial.print(button1State);
  //Serial.print("\t");
  //Serial.println(button2State);
  
  delay(50);        // delay in between reads for stability
}

void fireGaEvent(int arg1){
  Serial.println("fireGaEvent");
  Serial.println(arg1);
  client.connect(server, 80);
  if (client.connect(server, 80)) {
    Serial.println("connected");
    // Make a HTTP request:
    client.println("GET www.google-analytics.com/collect?v=1&tid=UA-30396326-3&cid=123&t=event&ec=home&ea=door%20open HTTP/1.0");
    client.println();
  } else {
    //didn't get a connection to the server:
   Serial.println("connection failed");
  }
}



