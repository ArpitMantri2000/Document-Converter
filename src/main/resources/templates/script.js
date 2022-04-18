

const A = document.querySelector("#signUp");
const B = document.querySelector("#signIn");
const C = document.querySelector("#signupComplete");	
const D = document.querySelector("#newUserLogin");
const E = document.querySelector("#loginUser");	
const page1 = document.querySelector('#page1');		
const page2 = document.querySelector('#page2');		
const page3 = document.querySelector('#page3');
const page4 = document.querySelector('#page4');
const page6 = document.querySelector('#page6');



A.addEventListener("click", () => {
	page1.classList.remove('B');
	page1.classList.add('A');
	page2.classList.remove('A');
	page2.classList.add('B');
});


B.addEventListener("click", () => {	
	page1.classList.remove('B');
	page1.classList.add('A');
	page4.classList.remove('A');
	page4.classList.add('B');
});

C.addEventListener("click",() =>{
	page2.classList.remove('B');
	page2.classList.add('A');
	page3.classList.remove('A');
	page3.classList.add('B');
});

D.addEventListener("click",()=>{
	page3.classList.remove('B');
	page3.classList.add('A');
	page4.classList.remove('A');
	page4.classList.add('B');
});

E.addEventListener("click",()=>{
	page4.classList.remove('B');
	page4.classList.add('A');
	page6.classList.remove('A');
	page6.classList.add('B');
})