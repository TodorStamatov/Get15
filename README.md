# Get15

## Idea of the game
The project is a multiplayer game in which both players take turns to choose a number between 1 and 9. The winner is the player who first got 3 numbers which sum is 15.
Any number can be taken only once. If all numbers are taken and there is no player met the rule for winnig the game the result is a draw.

## Project implementation
Game with Client-server architecture which provides the opportunity of multiplayer execution. The project is a Java FX application in which both of the players conncect 
to RMI server. Callback is used for this purpose. Scene Builder is used for developing the user interface. 

## Description of each class:

**Interface Callback** – interface implemented by the CallbackImp class, in which are defined methods helping the server to inform the client when it is his turn,
send him a message in the text area and inform him for about a move made by his opponent
**Class CallbackImp** – contains private field of type Client, as well as the implementation of each of the methods declared in the Interface Callback 

**Interface ServerInterface** - interface implemented by the ServerImp class, in which are defined two methods: connect, which takes an object of type Callback and returns
id if the client is connected successfully with the server or empty string if he doesn't and move method which takes a number chosen by the player and id of the player.

**Class ServerImp** – contains 2 private variables of type Callback, which are references to both players and char array called number, which in each position keeps 
information about the corresponding number whether it is selected and if so by which of the players. Connect and move methods of ServerInterface are implemented as well as
auxiliary methods that check during the game whether there is a winner and whether the game is over.

**Class ClientRMI** – has private field: id, which got from the Server, myTurn, which determines whether the player is on the move, server - the server to which the 
client is connected as well as the javafx controller, which controls the player's visual interface.Initialize method in which the player tries to find the server and 
connect to it and returns a response whether it was successful.NotifyServer method, which informs the server of a move made. Because the class is an application, it 
overrides the start method, which shows the player interface from SceneBuilder.

**Class Controller** – contains private data about the fields, labels and buttons, as well as a reference to the client whose interface it manages.Contains methods: 
opponetTurnMark, which makes the label of the number selected by the opponent invisible and adds the number to the opponent numbers field; setTxtArea,which displays a 
submitted message in the information area. Features that if the player is on the move and clicks on a visible label, he becomes invisible, the number of it is adds your 
numbers in the field and sends a message to the server about the move. There is also a feature that shows when you click on the game rules buttonwindow with their 
detailed description. In the initialize method of the controller, the object is initialized by a team client and the current controller is assigned to its controller data.

![eferfrefefre](D:/Capture1.png "Image with UI example")
