/*var frd = "Aravind"

console.log(frd);


const person = {
    a:10,
    b:20
}
console.log(person.a)*/



// local varaiables which we acess in the function is called bloxk scope and function scope

/*function a()
{
    var data = 10;
    console.log(data);
}

a();
 // global varaiable which we acess from any function

var data = 20;

function b()
{
    console.log(data)
}

function c()
{
    console.log(data)
}
b()
c()
*/
// condiditional statements 
var data = 10;
if(data>20)
{
    console.log("smaller");
}
else
{
    console.log("Greater");
}

// moduls

var a=20;  
if(a%2==0){  
 console.log("a is even number");  
}  
else{  
console.log("a is odd number");  
}  


// objects

var obj = new Object()

obj.id =10;

obj.name = 'Arru';


console.log(obj.id+obj.name)


// string methods

// is charAt
var str="javascript";  
var str2 = "TypeSccript";
console.log(str.charAt(2)); 

// concat
var str3 = str.concat(str2);
console.log(str3)

var str4 = str.toLowerCase()
console.log(str4)

var str5 = str.toUpperCase()

console.log(str5)


// slice
var str6 = str.slice(2,5)

console.log(str6)

// sub string

console.log(str.substring(1,7));
