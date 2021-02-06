#!/usr/bin/env python
 
import serial
import json
import MySQLdb
 
arduino = serial.Serial('/dev/ttyACM0',9600)
print arduino.readline()
print arduino.readline()
while True:
    character= arduino.readline()   
    MyJson = character
    db = MySQLdb.connect("localhost","root","pucese11","datos")
    if character != '\n':
        try:
            data=json.loads(character)
            print data
            print data['cardio']
            curs = db.cursor()
            curs.execute("INSERT INTO datos(fecha,temperatura,cardio)values(now(),"+str(data['temperatura'])+","+str(data['cardio'])+")")
            db.commit()
        except ValueErro            print ""
 
 
arduino.close()
