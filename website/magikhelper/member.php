<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/html">
<head>
<script>
$(document).ready(function() {
	//alert(2);
	$("#submit").click(function() {
		var name = $("#name").val();
		var phone = $("#phone").val();
		var email = $("#email").val();
		var address = $("#address").val();
		var city = $("#city").val();
		var zip = $("#zip").val();
		var appt = $("#appointment").val();
		var felony = $("#felony").val();
		var background = $("#background").val();
		var expertise = $("#expertise").val();
		
		 if ($('.expertise:checked').length) {
          var chkId = '';
          $('.expertise:checked').each(function () {
            chkId += $(this).val() + ",";
          });
          chkId = chkId.slice(0, -1);
          expertise = chkId;
        }
				
		if(name=="") {
			//alert("Please enter name");
			$("#name").css("border-color", "red");
			$("#name").focus();
		}
		else if(phone=="" || isNaN(phone)) {
			$("#phone").css("border-color", "red");
			$("#phone").focus();
		}
		else if(email=="") {
			$("#email").css("border-color", "red");
			$("#email").focus();
		}
		else if(!validateEmail(email)) {
			$("#email").css("border-color", "red");
			$("#email").focus();
		}
		else if(address=="") {
			$("#address").css("border-color", "red");
			$("#address").focus();
		}
		else if(city=="") {
			$("#city").css("border-color", "red");
			$("#city").focus();
		}
		else if(zip=="") {
			$("#zip").css("border-color", "red");
			$("#zip").focus();
		}
		else if(felony=="") {
			$("#felony").css("border-color", "red");
			$("#felony").focus();
		}
		else {
		var url = "form/send.php?name="+name+"&phone="+phone+"&email="+email+"&address="+address+"&city="+city+"&zip="+zip+"&appt="+appt+"&felony="+felony+"&background="+background+"&expertise="+expertise;
		
		$("#loader").show();
		$.post(url,function(data) {
			//alert(data);
			var $response=$(data);
			var msg = $response.filter('.success').text();
			if(msg=='success')  {
				$(".successMessage2").show();
				$("#loader").hide();
				$("#memberform").reset();
			}
			else {
				$(".errorMessage2").show();
			}
			
		});
		}
		
		function validateEmail(sEmail) {
			var filter = /^[\w\-\.\+]+\@[a-zA-Z0-9\.\-]+\.[a-zA-z0-9]{2,4}$/;
			if (filter.test(sEmail)) {
			return true;
			}
			else {
			return false;
			}
		}
		
	return false;	
	});
	
	$(".form-control").blur(function() {
		var id = $(this).attr("id");		
		var chk = $("#"+id).val();
		//alert(chk);
		if(chk=="") {
		$("#"+id).css("border-color", "red");
		}
		else {
		$("#"+id).css("border-color", "");
		}
	});
});
</script>
</head>
<body >
<h5 style="text-align:center"><span class="color-font">Prospective Affiliate Application</span></h5>

 <div class="container padding-small">
        <div class="contactForm">
            
			
		<form role="form" id="memberform" class="contactForm validateIt" data-email-subject="Become A Member Form" data-show-errors="true">
        <input type="hidden" name="action" value="send">
						<div class="row">
							<div class="col-md-12 col-sm-12">
								<div class="form-group">
									<input required type="text" name="name" id="name" class="form-control input-lg" placeholder="Name">
								</div>
								<div class="form-group">
									<input required type="text" name="phone" id="phone" class="form-control input-lg" placeholder="Phone">
								</div>
								<div class="form-group">
									<input required type="email" name="email" id="email" class="form-control input-lg" placeholder="Email">
								</div>
								<div class="form-group">
									<input required type="text" name="address" id="address" class="form-control input-lg" placeholder="Address">
								</div>
								<div class="form-group">
									<input required type="text" name="city" id="city" class="form-control input-lg" placeholder="City">
								</div>
								<div class="form-group">
									<input required type="text" name="zip" id="zip" class="form-control input-lg" placeholder="Zip">
								</div>
								<div class="form-group">
									<select name="appointment" id="appointment"  class="form-control input-lg">
										<option value=""> - Can you immediately confirm an appointment? - </option>
										<option value="yes" > - YES - </option>
										<option value="no"> - NO - </option>
									</select>
								</div>

								<div class="form-group">
									<select name="felony" id="felony" required class="form-control input-lg">
										<option value=""> - *Have you ever been convicted of a felony? - </option>
										<option value="yes"> - YES - </option>
										<option value="no"> - NO - </option>
									</select>
								</div>

								 <div class="form-group">
									<select name="background" id="background"  class="form-control input-lg">
										<option value=""> - *Are you willing to allow a background check? - </option>
										<option value="yes"> - YES - </option>
										<option value="no"> - NO - </option>
									</select>
								</div>      

								 <div class="form-group" style="font-size:18px">
									<div class="input-lg">Areas of expertise</div>

									<div class="col-md-3 col-sm-6">
										<input type="checkbox" name="expertise[]" id="expertise" class="expertise" value="Handyman"> Handyman
									</div>
									<div class="col-md-3 col-sm-6">
										<input type="checkbox" name="expertise[]" id="expertise" class="expertise"  value="Cleaning"> Cleaning
									</div>
									<div class="col-md-3 col-sm-6">
										<input type="checkbox" name="expertise[]" id="expertise" class="expertise"  value="Gardening"> Gardening
									</div>
									<div class="col-md-3 col-sm-6">
										<input type="checkbox" name="expertise[]" id="expertise" class="expertise"  value="Moving"> Moving
									</div>

								</div>                          

							</div>
							<div class="col-md-12 col-sm-12" style="padding-top: 40px">

								<button id="submit" class="btn btn-default btn-lg btn-block">Send Message</button>
                                <div id="loader" style="display:none"><img src="images/loader.gif"></div>
							</div>
						</div>
                        
                        <div class="row">
                            <div class="successMessage2 alert alert-success" style="display: none">
                                <p>Thank You! We will contact you shortly.</p>
                            </div>        
                            <div class="errorMessage2 alert alert-error" style="display: none">
                                <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                                <p>Oops! An error occurred. Please try again later.</p>
                            </div>       
                        </div>
					</form>
				</div>
        <!-- / contactForm -->
    </div>
<style type="text/css">
.form-control, .btn-default{width:98%;}
</style>
</body>
</html>
