import socket               # Import socket module
import os
import time
import sys
import telnetlib


soc = socket.socket(socket.AF_INET, socket.SOCK_STREAM)         # Create a socket object

Media_State = False
host = "0.0.0.0"                        # Get local machine name
port = 2009                             # Socket Port
#*************************
VSwitch_add = "192.168.1.39"            # IP address of KRAMER
VSwitch_port = 5000                     # Port number of KRAMER
#*************************
Relay_add = "192.168.1.53"             # IP address of Relay     THE PAINTING
Relay_port = 23                         # Port number of Relay
#*************************
MediaServer_add = "192.168.1.105"       # IP address of Kaleidescap
MediaServer_port = 10000                # Port number of Kaleidescap
#*************************
Lutron_add = "192.168.1.224"            # IP address of Lighting Processor Lutron
Lutron_port = 23                        # Port number of Lighting Processor Lutron
#*************************
Projection_add = "192.168.1.65"         # IP address of the Projector's Relay
Projection_port = 23                    # Port number of the Projector's Relay



def MediaServer(str):
	try:
		tn = telnetlib.Telnet(MediaServer_add, MediaServer_port)
		time.sleep(0.1)
		tn.write(str)
		time.sleep(0.1)
		tn.close()
	except socket.error:
		print 'no connection to MediaServer'


def Lighting(str):
	try:
		tn = telnetlib.Telnet(Lutron_add, Lutron_port)
		tn.read_until("login: ")
		tn.write("nwk2\r\n")
		tn.write(str)
		time.sleep(0.5)
		tn.close()
	except socket.error:
		print 'no connection to Lighting'


def Projection(str1,str2):
	try:
		tn = telnetlib.Telnet(Projection_add, Projection_port)
		time.sleep(0.1)
		tn.write("\n")
		tn.write(str1)
		time.sleep(0.1)
		tn.write(str2)
		time.sleep(0.1)
		tn.close()
	except socket.error:
		print 'no connection to Projection'


def VideoSwitch():
	try:
		tn = telnetlib.Telnet(VSwitch_add, VSwitch_port)
		time.sleep(0.3)
		tn.write("\x01\x83\x82\x81")
		tn.close()
	except socket.error:
		print 'no connection to VideoSwitch'

def Painting(str1,str2)
	try:
		tn = telnetlib.Telnet(Relay_add, Relay_port)
		time.sleep(1)
		tn.write("\n")
		time.sleep(1)
		tn.write(str1)
		time.sleep(1)
		time.write(str2)
		time.sleep(1)
		tn.close()
	except socket.error:
		print 'no connection to Painting'


try:
	soc.bind((host, port))              # Bind to the port
except socket.error as e:
	print(str(e))

soc.listen(5)                           # Now wait for client connection.
print("Waiting for connections..")

while True:
	conn, addr = soc.accept()       # Establish connection with client.

	while True:
		print('Got Connexion from',addr,'\n')
		recievedMsg = conn.recv(1024)
		if not recievedMsg:
			break

		elif recievedMsg=="MediaON":
			#I did this condition to test if the system is already on or not.
			if Media_State== False:
				print('Button MEDIA ON was pressed\n')
			#-----------
			#    TV
			#-----------
				os.system("irsend SEND_ONCE LG_AKB KEY_POWER")			#Power ON the TV
				print('TV ON')
			#-----------
			#  AV Reciever
			#-----------
				os.system("onkyo -i 0009B0459AA1 system-power=on")        #Power ON the AV Reciever 
			#-----------
			#   Source
			#-----------
				VideoSwitch()                     
			#-----------
			#  Relay
			#-----------
				Painting('relay off 0\n','relay on 1\n')

				Media_State= True
				break

			else:
				break


		elif recievedMsg=="MediaOFF":
			if Media_State== True:
				print('Button MEDIA OFF was pressed')
				print('HDMI PORT OFF\n')
			#-----------
			# Amplifier
			#-----------
				os.system("onkyo -i 0009B0459AA1 system-power=standby")   #Turn OFF the AV Reciever
			#-----------
			#    TV
			#-----------
				os.system("irsend SEND_ONCE LG_AKB KEY_POWER")
			#-----------
			#  Relay
			#-----------
				Painting('relay off 1\n','relay on 0\n')
				Media_State=False
				break
			else:
				break

		elif recievedMsg =="ProjectionON":
			print('\nButton PROJECTION ON was pressed')
		# Bring down the projector
			Projection('relay off 1\n','relay on 0\n')
		# AV Reciever 
			os.system('onkyo -i 0009B0459AA1 system-power=on')
		# Select Source
			VideoSwitch()
			time.sleep(1)
		# Power ON Projector
			os.system("irsend SEND_ONCE OPTOMA KEY_POWER")
			break

					
		elif recievedMsg=="ProjectionOFF":
			print('\nButton Projection OFF was pressed')
		# Raise the Projector
			Projection('relay off 0\n','relay on 1\n')
		# AV Reciever 
			os.system('onkyo -i 0009B0459AA1 system-power=standby')
		# Power OFF Projector
			os.system("irsend SEND_ONCE OPTOMA KEY_POWER")
			break			

		elif recievedMsg=="Bright":
			print('\nLight is bright!')
			#Please read the docs attached to understand how I built the command.
			Lighting('#DEVICE,0x00bd3dda,5,14,100\r\n#DEVICE,0x00bd3dda,4,14,100\r\n')
		elif recievedMsg == "Mid"
			print('\nLight is Mid!')
			Lighting('#DEVICE,0x00bd3dda,5,14,50\r\n#DEVICE,0x00bd3dda,4,14,50\r\n')
		elif recievedMsg == "Dim"
			print('\nLight is dim!')
			Lighting('#DEVICE,0x00bd3dda,5,14,30\r\n#DEVICE,0x00bd3dda,4,14,30\r\n')	
		elif recievedMsg == "Dim"
			print('\nLight is off')
			Lighting('#DEVICE, 0x00bd3dda,5,14,0\r\n#DEVICE,0x00bd3dda,4,14,0\r\n')
		#---------------------------------
		#         CONTROL PANEL          |
		#---------------------------------

		elif recievedMsg=="VolumeUp":
			#Please look at Onkyo library on github to see the available commands.
			os.system("onkyo -i 0009B0459AA1 master-volume=level-up")
			print('\nVolume is up')
			break
		elif recievedMsg=="VolumeDown":
			os.system("onkyo -i 0009B0459AA1 master-volume=level-down")
			print('\nVolume is down')
			break
		elif recievedMsg=="Mute":
			os.system("onkyo -i 0009B0459AA1 audio-muting=on")
			break
			#Please look at Kaleidescap docs on github to see the available commands.
		elif recievedMsg=="UpArrow":                       
			MediaServer("52/1/UP:")                        
			break
		elif recievedMsg=="DownArrow":                                      
			MediaServer("51/1/DOWN:")                       
			break
		elif recievedMsg=="RightArrow":
			MediaServer("51/1/RIGHT:")
			break
		elif recievedMsg=="LeftArrow":
			MediaServer("51/1/LEFT:")
			break
		elif recievedMsg=="Play":
			MediaServer("51/1/PLAY:")
			break
		elif recievedMsg=="Movies":
			MediaServer("51/1/GO_MOVIE_COVERS:") 
			break
		elif recievedMsg=="Music":
			MediaServer("51/1/GO_MUSIC_COVERS:")
			break
					
		else:
			#This happens when you send a command that doesn't match any of the ones above
			print('Received msg is : ',recievedMsg)
			print('Client connected but BAD CODE!')
			break

	conn.close()
#soc.close()

