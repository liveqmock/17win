
var n=0;
var showNum = document.getElementById("num");

function Mea(value){
	n=value;
	setBg(value);
	}
function setBg(value){
	for(var i=0;i<10;i++){
	   if(value==i){
	     document.getElementById("a"+value).className='li1';      
			}	else{	
			 document.getElementById("a"+i).className='li0';
			}  
	} 
}

function clearAuto(){clearInterval(autoStart)}
function auto(){
	n++;
	if(n>10)n=0;
	Mea(n);
} 
function sub(){
	n--;
	if(n<0)n=10;
	Mea(n);
} 
function setAuto(){}