<?php	

    $CRLF 	  = " <br /> ";
	$CRLF2    = "\n";  
	$headers  = "MIME-Version: 1.0 {$CRLF2}";           
	$headers .= "Content-type: text/html; charset=iso-8859-1{$CRLF2}";        
	$headers .= "From:contact@magikhelper.com{$CRLF2}";		
	$headers .= "X-Sender:<contact@magikhelper.com>{$CRLF2}";
	$headers .= "X-Mailer:PHP{$CRLF2}"; 
	$headers .= "X-Priority:3{$CRLF2}"; 
	$headers .= "Return-Path:<contact@magikhelper.com>{$CRLF2}";
  
	$HTML = "";
	  
	$HTML .= '<table>';
	$HTML .= "<tr><td>Name : </td><td>".$_REQUEST['name']."</td></tr>";
	$HTML .= "<tr><td>Phone : </td><td>".$_REQUEST['phone']."</td></tr>";
	$HTML .= "<tr><td>Email : </td><td>".$_REQUEST['email']."</td></tr>";
	$HTML .= "<tr><td>Address : </td><td>".$_REQUEST['address']."</td></tr>";
	$HTML .= "<tr><td>City : </td><td>".$_REQUEST['city']."</td></tr>";
	$HTML .= "<tr><td>ZIP : </td><td>".$_REQUEST['zip']."</td></tr>";
	$HTML .= "<tr><td>Appointment Confirm ? : </td><td>".$_REQUEST['appt']."</td></tr>";
	$HTML .= "<tr><td>Convicted of Felony ? : </td><td>".$_REQUEST['felony']."</td></tr>";
	$HTML .= "<tr><td>Background check ? : </td><td>".$_REQUEST['background']."</td></tr>";
	$HTML .= "<tr><td>Expertise : </td><td>".$_REQUEST['expertise']."</td></tr>";
	$HTML .= '<td>';
	$HTML .= '<table>';
	$HTML .= '</tr>';
	$HTML .= '</table>';
	  
	$from        = "contact@magikhelper.com";
	$to          = "info#magikhelper.com";
	$subject     = "Message from Magik Help site - PROSPECTIVE AFFILIATE APPLICATION";
	
	$ok = @mail($to, $subject, $HTML, $headers);
	
	//echo $HTML;
	echo "<div class='success'>";
	echo "success"; 
	echo "</div>"; 

?>	