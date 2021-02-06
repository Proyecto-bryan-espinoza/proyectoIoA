#include <OneWire.h>
#include <DallasTemperature.h>

const int DS18B20_PIN = 7; // Pin del sensor

OneWire oneWire(DS18B20_PIN); // Creamos objeto OneWire
DallasTemperature ds18b20(&oneWire); // Creamos objeto sensor

float temperatura; // Variable para almacenar la temperatura

void setup(void)
{
  Serial.begin(9600); // Inicializa el puerto serie
  ds18b20.begin(); // Inicializa el DS18B20
  pinMode(10, INPUT); // Setup for leads off detection LO +
  pinMode(11, INPUT); // Setup for leads off detection LO -
}

void loop(void)
{ 
  ds18b20.requestTemperatures(); // Envia el comando para solicitar la temperatur
  temperatura = ds18b20.getTempCByIndex(0); // Lee la temperatura del sensor
  Serial.println(temperatura); // Envia el dato al monitor serie

// PROCESO CARDIO

if((digitalRead(10) == 1)||(digitalRead(11) == 1)){
    Serial.println('!');
  }
  else{
    // send the value of analog input 0:
      Serial.println(analogRead(A0));
 //Wait for a bit to keep serial data from saturating
  delay(1);
}
