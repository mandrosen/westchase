<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html>

<head>
	<title>Westchase</title>
	<style type="text/css">
	body {
		font-family: Arial, Helvetica, sans-serif;
	}
	.header {
		text-align: center;
		margin: 5px 0 10px 0;
	}
	.header h2 { display: inline }
	.header img {
		vertical-align: middle;
	}
	.content {
		text-align: center;
	}
	.main {
		width: 500px;
		margin: 0 auto;
	}
	.roundedcornr_box_343864 {
		background: url(http://localhost/images/roundedcornr_343864_tl.png) no-repeat top left;
	}
	.roundedcornr_top_343864 {
		background: url(http://localhost/images/roundedcornr_343864_tr.png) no-repeat top right;
	}
	.roundedcornr_bottom_343864 {
		background: url(http://localhost/images/roundedcornr_343864_bl.png) no-repeat bottom left;
	}
	.roundedcornr_bottom_343864 div {
		background: url(http://localhost/images/roundedcornr_343864_br.png) no-repeat bottom right;
	}
	.roundedcornr_content_343864 {
		background: url(http://localhost/images/roundedcornr_343864_r.png) top right repeat-y;
	}
	
	.roundedcornr_top_343864 div,.roundedcornr_top_343864,
	.roundedcornr_bottom_343864 div, .roundedcornr_bottom_343864 {
		width: 100%;
		height: 15px;
		font-size: 1px;
	}
	.roundedcornr_content_343864, .roundedcornr_bottom_343864 {
		margin-top: -19px;
	}
	.roundedcornr_content_343864 { padding: 0 15px; }	
	</style>
	<script type="text/javascript">
		function trim(val) {
			return val.replace(/^\s*([\S\s]*?)\s*$/, '$1');
		}
		function init() {
			var un = document.getElementById("username").value;
			if (un == null || trim(un) == "") {
				document.getElementById("username").focus();
			} else {
				document.getElementById("password").focus();
			}
		}
		function validate() {
			var un = document.getElementById("username").value;
			var pw = document.getElementById("password").value;
			if (un == null || trim(un) == "") {
				alert("Please provide a Username");
				return false;
			}
			if (pw == null || trim(pw) == "") {
				alert("Please provide a Password");
				return false;
			}
			return true;
		}
	</script>
</head>

<body onload="init()">

	<div class="header">
		<img src="http://localhost/images/w-logo.jpg" alt="" width="21" />
		<h2>Westchase District</h2>
	</div>

	<div class="content">
		<div class="main">
		
		
		<div class="roundedcornr_box_343864">
   <div class="roundedcornr_top_343864"><div></div></div>
      <div class="roundedcornr_content_343864">


		
			<div class="loginform">
				<p>
					Please login using your given username and password.  
				</p>
				<c:if test="${not empty error}">
					<p class="error">Username or password are incorrect.</p>
				</c:if>
				<form action="j_security_check" method="post" onsubmit="return validate();">
					<table>
						<tbody>
							<tr><th>Username</th>
								<td><input type="text" name="j_username" size="20" maxlength="50" id="username" /></td></tr>
							<tr><th>Password</th>
								<td><input type="password" name="j_password" size="20" maxlength="100" id="password" /></td></tr>
							<tr>
								<td colspan="2"><input type="submit" value="Log In" /></td></tr>
						</tbody>
					</table>
				</form>
			</div>
			<p>
				If you have forgotten one or both, or if you are having an issue logging in, please contact 
				<a href="mailto:marcr@alumni.rice.edu">Marc (mandrosen@gmail.com)</a> for help.
			</p>
			
      </div>
   <div class="roundedcornr_bottom_343864"><div></div></div>
</div>			
		</div>
	</div>
	
	<div class="footer">
	
	</div>
	
</body>
</html>