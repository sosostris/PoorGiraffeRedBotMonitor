			
var robot;

var worldCounter = 0;
			
//This is used to get center coordinates to base point 0 
var mappedX = 0;
var mappedY = 0;

var coorX = 0;
var coorY = 0;

//Sets world specifications
var world = {
    canvas : document.createElement("giraffeMap"),
    start : function() {
	this.canvas.width = 1000;
	this.canvas.height = 800;
	this.context = this.canvas.getContext("2d");
	document.body.insertBefore(this.canvas, document.body.childNodes[0]);
	this.interval = setInterval(updatePos, 25);
    },
    clear : function() {
	this.context.clearRect(0, 0, this.canvas.width, this.canvas.height);
    }
};

//Creates robot object in the world
function Robot(width, height, x, y) {
	this.width = width;
	this.height = height;
	this.x = x;
	this.y = y;    
				
	this.newPos = function() {
            this.x = this.x;
            this.y = this.y;        
	};   
};
		
//This gets as parameters the coordinates of the current position of the robot. 
//First time should get as input the centered coordinates from index.jsp
function init(initX, initY) {
        //Center = Half the axis minus half the size of robot block (50px)  
        robot = new Robot(25, 25, initX, initY);
        world.start();
                        
        document.getElementById("coorX").innerHTML = mappedX;
        document.getElementById("coorY").innerHTML = mappedY;
}
		
function updatePos() {
	world.clear();
	robot.newPos();    
	robot.update();
}

//Key press event handler
$(document).keyup(function(event) {
    if (event.keyCode === 37) {
        $("#left").click();
    }
    if (event.keyCode === 38) {
        $("#up").click();
    }
    if (event.keyCode === 39) {
        $("#right").click();
    }
    if (event.keyCode === 40) {
        $("#down").click();
    }   
    if (event.keyCode === 82) {
        $("#reset").click();
    } 
});


