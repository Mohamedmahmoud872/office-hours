function submitUserForm(event)
{
		var response = grecaptcha.getResponse();

		if(response.length == 0)
		{
			document.getElementById('g-recaptcha-error').innerHTML = '<span style="color:red;">This field is required.</span>';
			document.getElementById('g-recaptcha-error').style.display="block";
			return false;
		}else{
			document.getElementById('g-recaptcha-error').style.display="none";
			registar(event);
		}
	}
function verifyCaptcha()
{
		document.getElementById('g-recaptcha-error').innerHTML ='';
	}
function registar(event)
{
		event.preventDefault();
		var res="";
		var Email=document.getElementById("email").value;
		var xmlhttp = new XMLHttpRequest();
		xmlhttp.open("GET","SignUpAjax?email="+Email,true);
		xmlhttp.send();
		res=xmlhttp.responseText;
		const response_element = document.getElementById("show_response");
		xmlhttp.onreadystatechange=function()
		{
			if (xmlhttp.readyState===4 && xmlhttp.status===200)
			{
				response_element.innerHTML=xmlhttp.responseText;
				response_element.style.display="block";
				if(xmlhttp.responseText.toString() === "You are already have account"){
					document.getElementById("#show_response").focus();
					return false;
				}else{
					document.getElementById("show_response").style.display="none";
					const myform = document.getElementById("signupForm");
					myform.submit();
				}
			}
		}
	}
function CheckCredentials(e)
{
	e.preventDefault();

	var username =document.getElementById("name").value;
	var password =document.getElementById("pass").value;
	var xmlhttp = new XMLHttpRequest();

	xmlhttp.open("GET","SignInAjax?name="+username + "&pass=" + password,true);
	xmlhttp.send();

	var response_element = document.getElementById("show_response");
	xmlhttp.onreadystatechange=function()
	{
		if (xmlhttp.readyState==4 && xmlhttp.status==200)
		{
			response_element.innerHTML=xmlhttp.responseText

			console.log(xmlhttp.responseText.toString());
			if(xmlhttp.responseText.toString() == "The Credentials are wrong"){
				document.getElementById("#show_response").focus();
				return false;
			}
			else
			{
				const myform = document.getElementById("SignInForm");
				myform.submit();
				return true;
			}
		}
	}
}

