# Webrtc signalling server

>The server connect two users together.
>Our signaling server will allow one user to call another. 
>Once a user has called another, the server passes the offer, 
>answer, ICE candidates between them and setup a WebRTC connection.

**Getting started**

```
git clone https://github.com/canadmin/webrtc-signalling-server.git
cd dual-chat
mvn spring-boot:run
```
**to view the api document** :
        
        http://localhost:8081/swagger-ui.html

**Docker installation** :

        docker pull canadmin/dualchat:first
        docker run -d -p 8081:8081 canadmin/dualchat:first


