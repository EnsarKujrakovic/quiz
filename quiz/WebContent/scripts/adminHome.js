var adminHome = {
		onLoad: function(){
			adminHome.hideButtons();
		},
		hideButtons: function(){
			var xhttp = new XMLHttpRequest();
		    xhttp.onreadystatechange = function() {
		        if (this.readyState == 4 && this.status == 200) {
		            adminHome.hideButtons2(this.responseText);
		        }
		    };
		    xhttp.open("GET", "super", true);
		    xhttp.send(); 
		},
		hideButtons2: function(Case){
			if(Case == 1)
				document.getElementById("adminEditUsers").style.display= "block";
			else
				document.getElementById("adminEditUsers").style.display= "none";	
		},
		contentEditors: function(){
			document.getElementById("adminHomepagePassword").style.display = "none";
			document.getElementById("adminHomepageQuizes").style.display = "none";
			document.getElementById("adminHomepageEditors").style.display = "block";
			document.getElementById("adminHomepageInbox").style.display = "none";
			adminHome.getEditors();
		},
		contentQuizes: function(){
			document.getElementById("adminHomepagePassword").style.display = "none";
			document.getElementById("adminHomepageEditors").style.display = "none";
			document.getElementById("adminHomepageQuizes").style.display = "block";
			document.getElementById("adminHomepageInbox").style.display = "none";
			adminHome.getQuizes();
		},
		contentInbox: function(){
			document.getElementById("adminHomepagePassword").style.display = "none";
			document.getElementById("adminHomepageQuizes").style.display = "none";
			document.getElementById("adminHomepageEditors").style.display = "none";
			document.getElementById("adminHomepageInbox").style.display = "block";
			adminHome.getInbox();
		},
		contentPassword: function(){
			document.getElementById("adminHomepagePassword").style.display = "block";
			document.getElementById("adminHomepageQuizes").style.display = "none";
			document.getElementById("adminHomepageEditors").style.display = "none";
			document.getElementById("adminHomepageInbox").style.display = "none";
			document.getElementById("adminHomepagePassword").innerHTML =
				"<div style=\"width:50%\"><form style=\"text-align:center\" id=\"changepassform\" method=\"get\" action=\"javascript:adminHome.changePass()\">"
				+"<input class=\"mdl-textfield__input tbox\" id=\"oldPass\" type=\"password\" name=\"password\" placeholder=\"old password\"/>"
				+"<br><input class=\"mdl-textfield__input tbox\" id=\"newPass\" type=\"password\" name=\"password\" placeholder=\"new password\"/>"
				+"<br><input class=\"mdl-textfield__input tbox\" id=\"againPass\" type=\"password\" name=\"password\" placeholder=\"again...\"/>"
				+"<br><input style=\"width:20%\" id=\"id\" class=\"mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect mdl-button--accent\" type=\"submit\" value=\"Submit\" />"
				+"</form></div>";
		},
		getInbox: function(){
			
			var xhttp = new XMLHttpRequest();
		    xhttp.onreadystatechange = function() {
		        if (this.readyState == 4 && this.status == 200) {
		            document.getElementById("adminHomepageInbox").innerHTML =
		            this.responseText;
		       }
		    };
		    xhttp.open("GET", "getresults", true);
		    xhttp.send(); 
		},
		getEditors: function(){
			var xhttp = new XMLHttpRequest();
		    xhttp.onreadystatechange = function() {
		        if (this.readyState == 4 && this.status == 200) {
		            document.getElementById("adminHomepageEditors").innerHTML =
		            this.responseText;
		       }
		    };
		    xhttp.open("GET", "editors?removeEditor=false&editorId=0", true);
		    xhttp.send(); 
		},
		getQuizes: function(){
			var xhttp = new XMLHttpRequest();
		    xhttp.onreadystatechange = function() {
		        if (this.readyState == 4 && this.status == 200) {
		            document.getElementById("adminHomepageQuizes").innerHTML =
		            this.responseText;
		       }
		    };
		    xhttp.open("GET", "quizes?removeQuiz=false&quizId=0", true);
		    xhttp.send(); 
		},
		removeEditor: function(buttonId){
			var c = confirm("Are you sure you want to remove user?");
			if (c == true){
				var xhttp = new XMLHttpRequest();
			    xhttp.onreadystatechange = function() {
			        if (this.readyState == 4 && this.status == 200) {
			            adminHome.contentEditors();
			       }
			    };
			    xhttp.open("GET", "editors?removeEditor=true&editorId="+buttonId, true);
			    xhttp.send(); 
			}
		},
		removeQuiz: function(buttonId){
			var c = confirm("Are you sure you want to remove quiz?");
			if (c == true){
				var xhttp = new XMLHttpRequest();
			    xhttp.onreadystatechange = function() {
			        if (this.readyState == 4 && this.status == 200) {
			            adminHome.contentQuizes();
			       }
			    };
			    xhttp.open("GET", "quizes?removeQuiz=true&quizId="+buttonId, true);
			    xhttp.send(); 
			}
		},
		addEditor: function(){
			var html = "<dialog style=\"height:300px\" class=\"mdl-dialog\">" +
					"<div style=\"height:150px\" class=\"mdl-dialog__content\">" +
						"<br><input class=\"mdl-textfield__input tbox\" id=\"firstName\" name=\"firstName\" placeholder=\"First name\"/>" +
						"<br><input class=\"mdl-textfield__input tbox\" id=\"lastName\" name=\"lastName\" placeholder=\"Last name\"/>" +
						"<br><input class=\"mdl-textfield__input tbox\" id=\"username\" name=\"username\" placeholder=\"Username\"/>" +
					"</div>" +
					"<div class=\"mdl-dialog__actions mdl-dialog__actions--full-width\">" +
						"<button type=\"button\" id=\"ok\" class=\"mdl-button\">Add</button>" +
						"<button type=\"button\" class=\"mdl-button close\">Cancel</button>" +
					"</div>" +
					"</dialog>";
			document.getElementById("adminHomepageEditors").insertAdjacentHTML('beforeend', html);
			var dialog = document.querySelector('dialog');
		    var showModalButton = document.getElementById("addNewEditor");
		    if (!dialog.showModal) {
		      dialogPolyfill.registerDialog(dialog);
		    }
		      dialog.showModal();
		    document.getElementById('ok').addEventListener('click', function() {
		       //send data to servlet
		    	var fn = document.getElementById('firstName');
				var ln = document.getElementById('lastName');
				var un = document.getElementById('username');
		    	if(fn.value.length < 3 || ln.value.length < 3 || un.value.length < 3){
					alert("First name, last name and username must be at least 3 characters long!");
					return;
				}
					var xhttp = new XMLHttpRequest();
				    xhttp.onreadystatechange = function() {
				        if (this.readyState == 4 && this.status == 200) {
				        	adminHome.getEditors();
				        }
				    };
				    xhttp.open("GET", "adduser?firstName="+fn.value+"&lastName="+ln.value+"&username="+un.value, true);
				    xhttp.send();
				    dialog.close();
			        
		     });
		    dialog.querySelector('.close').addEventListener('click', function() {
		      dialog.close();
		    });
		},
		addQuiz: function(){
			var html = "<dialog style=\"height:300px\" class=\"mdl-dialog\">" +
					"<div style=\"height:150px\" class=\"mdl-dialog__content\">" +
						"<br><input class=\"mdl-textfield__input tbox\" id=\"title\" name=\"Title\" placeholder=\"Title\"/>" +
						"<textarea class=\"mdl-textfield__input tbox\" type=\"text\" rows= \"3\" id=\"description\" name=\"Description\" placeholder=\"Description\" ></textarea>" +
					"<input id=\"inputFileToLoad\" type=\"file\" onchange=\"loadImageFileAsURL();\" />" +
					"</div>" +
					"<div class=\"mdl-dialog__actions mdl-dialog__actions--full-width\">" +
						"<button type=\"button\" id=\"okq\" class=\"mdl-button\">Add</button>" +
						"<button type=\"button\" class=\"mdl-button close\">Cancel</button>" +
					"</div>" +
					"</dialog>";
			document.getElementById("adminHomepageQuizes").insertAdjacentHTML('beforeend', html);
			var dialog = document.querySelector('dialog');
		    var showModalButton = document.getElementById("addNewQuiz");
		    if (!dialog.showModal) {
		      dialogPolyfill.registerDialog(dialog);
		    }
		      dialog.showModal();
		    document.getElementById('okq').addEventListener('click', function() {
		       //send data to servlet
		    	var title= document.getElementById('title');
				var description = document.getElementById('description');
		    	if(title.value.length < 1 || description.value.length < 1 ){
					alert("Please fill all fields!");
					return;
				}
					var xhttp = new XMLHttpRequest();
				    xhttp.onreadystatechange = function() {
				        if (this.readyState == 4 && this.status == 200) {
				        	adminHome.getQuizes();
				        }
				    };
				    xhttp.open("GET", "addquiz?title="+title.value+"&description="+description.value, true);
				    xhttp.send();
				    dialog.close();
			        
				//}
		     });
		    dialog.querySelector('.close').addEventListener('click', function() {
		      dialog.close();
		    });
		},
		editQuiz: function(quizId){
			var xhttp = new XMLHttpRequest();
		    xhttp.onreadystatechange = function() {
		        if (this.readyState == 4 && this.status == 200) {
		        	document.getElementById("adminHomepageQuizes").innerHTML = this.responseText;
		        }
		    };
		    xhttp.open("GET", "editquiz?quizId="+quizId, true);
		    xhttp.send();
		},
		addQuestion: function(){
			var qId = document.getElementById("quizIdDiv").textContent;
			var html = "<dialog style=\"height:300px\" class=\"mdl-dialog\">" +
			"<div style=\"height:150px\" class=\"mdl-dialog__content\">" +
				"<br><input class=\"mdl-textfield__input tbox\" id=\"questionText\" name=\"questionText\" placeholder=\"Question\"/>" +
			"</div>" +
			"<div class=\"mdl-dialog__actions mdl-dialog__actions--full-width\">" +
				"<button type=\"button\" id=\"okquest\" class=\"mdl-button\">Add</button>" +
				"<button type=\"button\" class=\"mdl-button close\">Cancel</button>" +
			"</div>" +
			"</dialog>";
			document.getElementById("adminHomepageQuizes").insertAdjacentHTML('beforeend', html);
			var dialog = document.querySelector('dialog');
			var showModalButton = document.getElementById("addNewQuestion");
			if (!dialog.showModal) {
				dialogPolyfill.registerDialog(dialog);
			}
			dialog.showModal();
			document.getElementById('okquest').addEventListener('click', function() {
			var qText= document.getElementById('questionText').value;
			var xhttp = new XMLHttpRequest();
		    xhttp.onreadystatechange = function() {
		        if (this.readyState == 4 && this.status == 200) {
		        	adminHome.editQuiz(qId);
		       }
		    };
		    xhttp.open("GET", "editquestion?quizId="+ qId +"&option=1&questionText="+qText+"&questionId=\"\"", true);
		    xhttp.send();
			});
			dialog.querySelector('.close').addEventListener('click', function() {
			      dialog.close();
			});
		},
		removeQuestion: function(questionId){
			var qId = document.getElementById("quizIdDiv").textContent;
			var xhttp = new XMLHttpRequest();
		    xhttp.onreadystatechange = function() {
		        if (this.readyState == 4 && this.status == 200) {
		           adminHome.editQuiz(qId);
		       }
		    };
		    xhttp.open("GET", "editquestion?questionId="+questionId+"&quizId="+qId+"&option=2&questionText=\"\"", true);
		    xhttp.send();
		},
		moveQuestion: function(questionId, direction){
			var qId = document.getElementById("quizIdDiv").textContent;
			var xhttp = new XMLHttpRequest();
		    xhttp.onreadystatechange = function() {
		        if (this.readyState == 4 && this.status == 200) {
		        	document.getElementById("adminHomepage").innerHTML = this.responseText;
		       }
		    };
		    xhttp.open("GET", "editquestion?questionId="+questionId+"&quizId="+qId+"&option=3&direction="+direction+"&questionText=\"\"", true);
		    xhttp.send();
		},
		editQuestion: function(questionId){
			var qId = document.getElementById("quizIdDiv").textContent;
			var xhttp = new XMLHttpRequest();
		    xhttp.onreadystatechange = function() {
		        if (this.readyState == 4 && this.status == 200) {
		        	document.getElementById("adminHomepageQuizes").innerHTML = this.responseText;
		       }
		    };
		    xhttp.open("GET", "editquestion?quizId="+qId+"&option=4&questionId="+questionId, true);
		    xhttp.send();
			
		},
		addAnswer: function(){
			var qId = document.getElementById("quizIdDiv").textContent;
			var questionId = document.getElementById("questionIdDiv").textContent;
			var html = "<dialog style=\"height:300px\" class=\"mdl-dialog\">" +
			"<div style=\"height:150px\" class=\"mdl-dialog__content\">" +
				"<br><input class=\"mdl-textfield__input tbox\" id=\"answerText\" name=\"answerText\" placeholder=\"Answer\"/>" +
				"<br><label>Correct?<input id=\"checkBoxId\" type=\"checkBox\" name=\"isCorrect\"/></label>" +
			"</div>" +
			"<div class=\"mdl-dialog__actions mdl-dialog__actions--full-width\">" +
				"<button type=\"button\" id=\"okanswer\" class=\"mdl-button\">Add</button>" +
				"<button type=\"button\" class=\"mdl-button close\">Cancel</button>" +
			"</div>" +
			"</dialog>";
			document.getElementById("dialogDiv").innerHTML = html;
			var dialog = document.querySelector('dialog');
			var showModalButton = document.getElementById("addNewAnswer");
			if (!dialog.showModal) {
				dialogPolyfill.registerDialog(dialog);
			}
			dialog.showModal();
			document.getElementById('okanswer').addEventListener('click', function() {
			var aText= document.getElementById('answerText').value;
			var chkBox = document.getElementById('checkBoxId').checked;
			var xhttp = new XMLHttpRequest();
		    xhttp.onreadystatechange = function() {
		        if (this.readyState == 4 && this.status == 200) {
		        	adminHome.editQuestion(questionId);
		       }
		    };
		    xhttp.open("GET", "editanswer?option=1&quizId="+qId+"&answerText="+aText+"&isCorrect="+chkBox+"&questionId="+questionId, true);
		    xhttp.send();
			});
			dialog.querySelector('.close').addEventListener('click', function() {
			      dialog.close();
			});
		},
		editAnswer: function(answerId,answerIsCorrect){
			var aText = document.getElementById("answerId"+answerId).textContent;
			var qId = document.getElementById("quizIdDiv").textContent;
			var questionId = document.getElementById("questionIdDiv").textContent;
			
			var html = "<dialog style=\"height:300px\" class=\"mdl-dialog\">" +
			"<div style=\"height:150px\" class=\"mdl-dialog__content\">" +
				"<br><input class=\"mdl-textfield__input tbox\" id=\"answerText\" name=\"answerText\" />" +
				"<br><label>Correct?<input id=\"checkBoxId\" type=\"checkBox\" name=\"isCorrect\"/></label>" +
			"</div>" +
			"<div class=\"mdl-dialog__actions mdl-dialog__actions--full-width\">" +
				"<button type=\"button\" id=\"okanswer\" class=\"mdl-button\">Submit</button>" +
				"<button type=\"button\" class=\"mdl-button close\">Cancel</button>" +
			"</div>" +
			"</dialog>";
			document.getElementById("dialogDiv").innerHTML=html;
			var chkBox = document.getElementById('checkBoxId').checked = answerIsCorrect;
			var answerText = document.getElementById('answerText').value = aText;
			
			var dialog = document.querySelector('dialog');
			var showModalButton = document.getElementById("addNewAnswer");
			
			if (!dialog.showModal) {
				dialogPolyfill.registerDialog(dialog);
			}
			dialog.showModal();
			document.getElementById('okanswer').addEventListener('click', function() {
			chkBox = document.getElementById('checkBoxId').checked;
			answerText = document.getElementById('answerText').value;
			var xhttp = new XMLHttpRequest();
		    xhttp.onreadystatechange = function() {
		        if (this.readyState == 4 && this.status == 200) {
		        	adminHome.editQuestion(questionId);
		       }
		    };
		    xhttp.open("GET", "editanswer?option=3&answerId="+answerId+"&answerText="+answerText+"&isCorrect="+chkBox, true);
		    xhttp.send();
			});
			dialog.querySelector('.close').addEventListener('click', function() {
			      dialog.close();
			});
		},
		removeAnswer: function(answerId){
			var qId = document.getElementById("quizIdDiv").textContent;
			var questionId = document.getElementById("questionIdDiv").textContent;
			var xhttp = new XMLHttpRequest();
		    xhttp.onreadystatechange = function() {
		        if (this.readyState == 4 && this.status == 200) {
		           adminHome.editQuestion(questionId);
		       }
		    };
		    xhttp.open("GET", "editanswer?questionId="+questionId+"&quizId="+qId+"&option=2&answerId="+answerId, true);
		    xhttp.send();
		},
		changePass: function(){
			var op = document.getElementById('oldPass');
			var np = document.getElementById('newPass');
			var ap = document.getElementById('againPass');
			if(np.value.length < 6 || ap.value.length < 6){
				alert("Password is too short!");
				return;
			}
			if(np.value != ap.value){
				alert('New passwords are not matching!');
				return;
			}
			var c = confirm("Are you sure you want to proceed?");
				if(c == true){
				var xhttp = new XMLHttpRequest();
			    xhttp.onreadystatechange = function() {
			        if (this.readyState == 4 && this.status == 200) {
			            document.getElementById("adminHomepagePassword").innerHTML =
			            this.responseText;
			       }
			    };
			    xhttp.open("GET", "chngpass?oldPass="+op.value+"&newPass="+np.value, true);
			    xhttp.send();
			}
		}
}