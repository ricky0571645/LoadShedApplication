int incomingByte = 0;   // for incoming serial data

void setup() {
        Serial.begin(9600);     // opens serial port, sets data rate to 9600 bps
}

void loop() {

        // send data only when you receive data:
        if (Serial.available() > 0) {
                // read the incoming byte:
                incomingByte = Serial.read();

                if(incomingByte == 'r')
                   digitalWrite(13, HIGH);             
                else if (incomingByte == 'g')
                  digitalWrite(11, HIGH);
                else if (incomingByte == 'b')
                  digitalWrite(9, HIGH);
                else
                {
                  digitalWrite(13, LOW);
                  digitalWrite(11, LOW);
                  digitalWrite(9, LOW);
                }
                delay(500);
                digitalWrite(13, LOW);
                digitalWrite(11, LOW);
                digitalWrite(9, LOW);

        }
}
