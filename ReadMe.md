## Smart Home Automation System based on a RaspberryPi and controlled by an Android application.

### Description : 
In this project, I implemented a RaspberryPi into a network to gather all the connected objects present in a house to make them communicate with each other and thus, have the whole house flexibly and remotely controlled without needing all the remote controls of each equipment. I created a Android application to communicate directly with the RaspberryPi using a server/client communication (TCP/IP socket) to control the connected objects like to power on/off, increase/decrease volume, turn on/off lights, etc.
The system's functionalities are quite basic at this moment, the possibilities or the scenarios that can be added are infinite, relays and sensors can be added to give the system more autonomy (door locks, detecting heat, presence, humidity, etc).


### Equipements :
- RaspberryPi module B2
- Android system version 5.0 or higher.
- [Connected objects.](https://github.com/Annaane/RPiSmartHome/tree/master/Docs)


### Prerequisite :
- [LIRC](http://www.lirc.org/) library. 
```
    sudo apt-get install lirc
```
- [Onkyo](https://github.com/miracle2k/onkyo-eiscp) library. 
```
    easy_install onkyo-eiscp
    onkyo --discover
```

### Potential improvements :

- Client-server communication encryption.
- Voice commands.
- Monitoring : graphs.


### TL;DR :
Do you want to **_see it_** working ? Check the video : https://youtu.be/DYRsG5hRbvs
