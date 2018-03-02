<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%String email=(String)session.getAttribute("email"); %>
<!doctype html>
<html lang="en">
 <head>
  <meta charset="UTF-8">
  <meta name="Generator" content="EditPlus®">
  <meta name="Author" content="">
  <meta name="Keywords" content="">
  <meta name="Description" content="">
  <title>i-chat</title>
  <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
	<link href="chatstyle.css" rel="stylesheet">
	<script src="chatmessage.js"></script>
 </head>
 <body>
  <div id="" class="chatBox">
	<div id="chat-logs" class="chatLogs">
		
		<div id="" class="chat self">
			<div id="" class="userPhoto"><p class="p">satya</p></div>
			<p class="userMessage">hii..!!</p>
		</div>
		
	</div>
	<div id="" class="chatForm">
			<textarea name="message" id="msgs" rows="" cols=""></textarea>
			<input type="button" value="Send" onclick="insertMsgs()">
			<input type="hidden" id="email" value="<%=email %>">
	</div>
  </div>
 </body>
</html>
<script id="insmsg"></script>
