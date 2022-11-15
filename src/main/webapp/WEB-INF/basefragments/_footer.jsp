<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
 <!-- changed to relative from fixed also works if position is not there  -->
<footer
	style="bottom: 0; left: 0; position: relative; font-size : small; width: 100%;"
	class="page-footer font-small blue bg-dark" >
	<!-- Copyright -->

	<div class="footer-copyright text-center py-3">
		<span id="myid" class="text-white"> </span> <b class="text-white">© Copyright:</b> <a href="#" class="text-white">
			Health Care System</a>
	</div>
	<!-- Copyright -->
</footer>
<!-- Footer -->
<script>
  var myid = document.getElementById("myid");
  var a = new Date().getFullYear();
  myid.innerHTML = a;
 </script>
