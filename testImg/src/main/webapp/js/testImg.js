window.onload = function(){
	loadImg();
	document.getElementById("addPic").addEventListener("click", uploadPic);
};
/*
function loadImg(){
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
			var dto = JSON.parse(xhr.responseText);
			var imgLocation = dto.imgs[1].link;
			console.log(imgLocation);
			var img = document.createElement("img");
			img.src = imgLocation;
			var src = document.getElementById("here");
			src.appendChild(img);
		}
	}
	xhr.open("GET", "load", true);
	xhr.send();
};*/
function loadImg(){
	
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
			var dto = JSON.parse(xhr.responseText);
			var dto = JSON.parse(xhr.responseText);
			var imgList = dto.imgs;
			for(var i=imgList.length-1; i>=0; i--){
				
				var table = document.getElementById("imgList");
				var row = table.insertRow();
				var cake = row.insertCell(0);
				var word = row.insertCell(1);
				
				var img = document.createElement("img");
				img.src = imgList[i].link;
				var src = document.getElementById("here");
				
				cake.appendChild(img);
				word.innerHTML=imgList[i].description;
			}
		}
	}
	
	xhr.open("GET", "load", true);
	xhr.send();
}

function uploadPic(){
	var descript = document.getElementById("description").value;
	var file = document.getElementById("file").value;
	var filetho = file.replace("C:\\fakepath\\", "C:\\Users\\1Z4XS\\Desktop\\");
	
	var tx = [descript,filetho];
	tx = JSON.stringify(tx);
	var xhr = new XMLHttpRequest();

	xhr.open("POST", "upload", true);
	xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	xhr.send(tx);
}