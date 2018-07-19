function Quiz(){
	var quiz = 0;
	var counter = 0;
	var startTimer = null;
	this.fill = function(quizId){
		var xhttp = new XMLHttpRequest();
	    xhttp.onreadystatechange = function() {
	        if (this.readyState == 4 && this.status == 200) {
	        	quiz = JSON.parse(this.responseText);
	        	htmlStartQuiz = "<table style=\"width:100%;max-width:600px\" id=editorsTable class=\"mdl-data-table mdl-js-data-table mdl-data-table--selectable mdl-shadow--2dp\">"+
	    		"<tr>"+
	    			"<th><div>"+
	    				"<h5 class=\"mdl-data-table__cell--non-numeric\">" + quiz.title + "</h5>"+
	    				"<p class=\"mdl-data-table__cell--non-numeric\">" + quiz.description + "</p>"+
	    			"</div></th>" +
	    		"</tr><tr>"+
	    			"<td><div>"+
    				"<h5 class=\"mdl-data-table__cell--non-numeric\"> Rules</h5>"+
    				"<p class=\"mdl-data-table__cell--non-numeric\">Number of questions: "+ quiz.questions.length+"" +
    																"<br>You have 60 seconds for each question" +
    																"<br>Questions can have multiple correct answers" +
    																"<br>" +
    																"</p>"+
    			"</div></td>" +
	    		"</tr>" +
	    		"</table>"+
	    		"<br><div style=\"text-align :center;width:100%;\">" +
	    		"<input style=\"width:30%\" id=\"startQuiz\" class=\"mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect mdl-button--accent\"  onclick=\"location.href='quizes';\" type=\"submit\" value=\"BACK\" />"+
	    		" <input style=\"width:30%\" id=\"startQuiz\" class=\"mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect mdl-button--accent\"  onclick=\"q1.initResults();q1.openQuestion(0)\" type=\"submit\" value=\"START\" /></div>";
	        	$("#playQuiz").fadeOut("1000",function(){document.getElementById("playQuiz").innerHTML = htmlStartQuiz;});
	        	$("#playQuiz").fadeIn("1000");
	       }
	    };
	    xhttp.open("POST", "getquizdata?quizId="+quizId, true);
	    xhttp.send();
	};
	this.initResults = function(){
		result.quizId= quiz.id;
		result.quizTitle= quiz.title;
		result.userId= quiz.userId;
		for(i = 0; i < quiz.questions.length; ++i){
			var answers =[];
			for(j = 0; j < quiz.questions[i].answers.length; ++j){
				var answer = "unchecked";
				answers[j] = answer;
			}
			var question = {"text":quiz.questions[i].text, "correct":false, "locked":false, "timer":60, "answers":answers};
			result.questions[i]= question;
		}
	};
	this.openQuestion = function(c){
		counter = c;
		if(result.questions[counter].locked == false && result.questions[counter].timer > 50){
			startTimer = setInterval(function(){
				result.questions[counter].timer--;
				document.getElementById("counterH5"+counter).textContent = result.questions[counter].timer; 
				if(result.questions[counter].timer == 0){
					result.questions[counter].locked = true;
					clearInterval(startTimer);
					q1.openQuestion(counter);
				}
				if(result.questions[counter].timer > 10){
					document.getElementById("counterH5"+counter).style.color = "green";
				}else {
					document.getElementById("counterH5"+counter).style.color = "red";
				}
			}, 1000);
			
			
		}
		var disabledElement = "";
		var disabledElementPrev = "";
		var disabledElementNext = "";
		if(result.questions[counter].locked == false)
			disabledElement = "";
		else
			disabledElement = "disabled";
		if(counter == 0) disabledElementPrev = "disabled";
		if(counter == result.questions.length-1) disabledElementNext = "disabled";
		//
		var finishButtonString = "";
		if(counter == quiz.questions.length-1)
				finishButtonString = "<br><div style=\"text-align :center;width:100%;\">" +
				"<br><input style=\"width:25%\" id=\"finishQuiz\" class=\"mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect mdl-button--accent\"  onclick=\"q1.finish()\" type=\"submit\" value=\"Finish\" />"+
				"</div>";
		var answersString = "";
		for( i = 0; i < quiz.questions[counter].answers.length; ++i){
			answersString += "<tr><td class=\"mdl-data-table__cell--non-numeric\" style=\"width:5%\"><input id=answer"+i+" type=\"checkBox\" "+disabledElement+" "+result.questions[c].answers[i]+"></td>"+
						  	 "<td class=\"mdl-data-table__cell--non-numeric\" style=\"width:80%\">"+ quiz.questions[counter].answers[i].text +"</td></tr>";
		}
		
		htmlQuestion = "<h5 id=\"counterH5"+counter+"\" style=\"text-align:center;color:green\">"+result.questions[counter].timer+"</h5> "+
		"<table style=\"width:100%;max-width:600px;\" id=editorsTable class=\"mdl-data-table mdl-js-data-table mdl-data-table--selectable mdl-shadow--2dp\">"+
		"<tr><th colspan=\"2\" style=\"text-align:center\">"+ (counter+1)+"/"+ quiz.questions.length+"</th></tr>"+
		"<tr>" +
		"<th colspan=\"2\"><div style=\"word-wrap:break-word;\">"+
				"<h5 class=\"mdl-data-table__cell--non-numeric\">" + quiz.questions[counter].text+ "</h5>"+
		"</div></th>" +
		"</tr>"+
		answersString+
		"</table>"+
		"<br><div style=\"text-align :center;width:100%;\">" +
			"<input style=\"width:25%\" id=\"previous\" class=\"mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect mdl-button--accent\"  onclick=\"clearInterval("+startTimer+");q1.openQuestion("+(counter-1)+")\" type=\"submit\" value=\"<\" "+disabledElementPrev+"/>" +
			" <input style=\"width:25%\" id=\"ok\" class=\"mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect mdl-button--accent\"  onclick=\"clearInterval("+startTimer+");q1.answerQuestion("+counter+")\" type=\"submit\" value=\"OK\" "+disabledElement+">"+
			" <input style=\"width:25%\" id=\"next\" class=\"mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect mdl-button--accent\"  onclick=\"clearInterval("+startTimer+");q1.openQuestion("+(counter+1)+")\" type=\"submit\" value=\">\" "+disabledElementNext+"/>"+
		"</div>"+ finishButtonString;
		$("#playQuiz").fadeOut("1000",function(){document.getElementById("playQuiz").innerHTML = htmlQuestion;});
    	$("#playQuiz").fadeIn("1000");
	};
	this.answerQuestion = function(c){
		result.questions[c].locked = true;
		for(i = 0; i < result.questions[c].answers.length; ++i){
			if(document.getElementById("answer"+i).checked == true)
				result.questions[c].answers[i] = "checked";
		}
		//
		var isCorrect = false;
		for(i = 0; i < quiz.questions[c].answers.length; ++i){
			if((result.questions[c].answers[i] == "checked" && quiz.questions[c].answers[i].correct == true)||
			   (result.questions[c].answers[i] == "unchecked" && quiz.questions[c].answers[i].correct == false)){
				result.questions[c].correct = true;
			}
			else{
				result.questions[c].correct = false;
				break;
			}
		}
		if(result.questions[c].correct) result.numOfCorrect++;

		if(c == quiz.questions.length-1)
			this.openQuestion(c);
		else
			this.openQuestion(c+1);
	}
	this.submit = function(){
		var jsonString = JSON.stringify(result);
		$.ajax({
			url:'submitresults',
			type:'GET',
			data:{jsonstring:jsonString},
			success:function(msg){}
		});
	}
	this.finish = function(){
		var htmlFinish = "<dialog style=\"height:300px\" class=\"mdl-dialog\">" +
		"<div style=\"height:150px\" class=\"mdl-dialog__content\">" +
			"<br><input class=\"mdl-textfield__input tbox\" id=\"firstName\" name=\"firstName\" placeholder=\"First name\"/>" +
			"<br><input class=\"mdl-textfield__input tbox\" id=\"lastName\" name=\"lastName\" placeholder=\"Last name\"/>" +
			"<br><input class=\"mdl-textfield__input tbox\" id=\"email\" name=\"email\" placeholder=\"Email\"/>" +
		"</div>" +
		"<div class=\"mdl-dialog__actions mdl-dialog__actions--full-width\">" +
			"<button type=\"button\" id=\"okfinish\" class=\"mdl-button\">Submit</button>" +
			"<button type=\"button\" class=\"mdl-button close\">Cancel</button>" +
		"</div>" +
		"</dialog>";
		$("#playQuiz").fadeOut("1000",function(){
			document.getElementById("playQuiz").innerHTML = htmlFinish;
			var dialog = document.querySelector('dialog');
			var showModalButton = document.getElementById("finishQuiz");
			if (!dialog.showModal) {
			  dialogPolyfill.registerDialog(dialog);
			}
			dialog.showModal();
			document.getElementById('okfinish').addEventListener('click', function() {
				if(document.getElementById("firstName").value.length > 3 &&
						document.getElementById("lastName").value.length > 3 &&
						document.getElementById("email").value.length > 6){
					result.firstName = document.getElementById("firstName").value;
					result.lastName = document.getElementById("lastName").value;
					result.email = document.getElementById("email").value;
					//send results
					q1.submit();
					//message
					var color;
					if(result.numOfCorrect/result.questions.length > 0.51) color = "green"; else color = "red";
					htmlAnswers = "<p style=\"text-align:center\">";
					for(i = 0; i < result.questions.length;++i){
						if(result.questions[i].correct)
							htmlAnswers += "<span style = \"color:green\">Question " + (i+1) +":&#10004;</span><br>";
						else
							htmlAnswers += "<span style = \"color:red\">Question " + (i+1) +":&#10008;<br>";
					}
					htmlAnswers += "</p>";
					var html = "<table style=\"width:100%;max-width:600px;\" id=editorsTable class=\"mdl-data-table mdl-js-data-table mdl-data-table--selectable mdl-shadow--2dp\">"+
					
					"<tr><th style=\"text-align:center\">"+result.firstName+" "+result.lastName+", Thank you for participating!</th></tr>"+
					"<tr>" +
					"<th><div style=\"word-wrap:break-word;\">"+
							"<h5 style=\"text-align:center\" class=\"mdl-data-table__cell--non-numeric\">Your results:</h5>"+
					"</div></th>" +
					"</tr>"+
					"<tr>" +
					"<th><div style=\"word-wrap:break-word;\">"+
							"<h5 style=\"text-align:center;color:"+color+"\" class=\"mdl-data-table__cell--non-numeric\" >"+result.numOfCorrect+"/"+ result.questions.length+"</h5>"+
							htmlAnswers+
					"</div></th>" +
					"</tr>"+
					"</table>"+
					"<br><div style=\"text-align :center;width:100%;\">" +
					"<br><input style=\"width:25%\" id=\"finishQuiz\" class=\"mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect mdl-button--accent\"  onclick=\"location.href='quizes'\" type=\"submit\" value=\"EXIT\" />"+
					"</div>";
					$("#playQuiz").fadeOut("1000",function(){
						document.getElementById("playQuiz").innerHTML = html;
					});
					$("#playQuiz").fadeIn("1000");
				}
			});
			dialog.querySelector('.close').addEventListener('click', function() {
			  dialog.close();
			});
		});
		$("#playQuiz").fadeIn("1000");
	};
}
var result = {
		quizId: 0,
		quizTitle: "",
		userId: 0,
		firstName : "",
		lastName : "",
		email : "",
		numOfCorrect: 0,
		questions: [],
		
}
var q1 = new Quiz();
var q2 = new Quiz();
