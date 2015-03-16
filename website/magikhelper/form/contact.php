<?php
$ajax = (!empty($_SERVER['HTTP_X_REQUESTED_WITH']) && strtolower($_SERVER['HTTP_X_REQUESTED_WITH']) == 'xmlhttprequest');
$ajax = true;
$to = 'info@magikhelper.com';

//we do not allow direct script access
if (!$ajax) {
	//redirect to contact form
	echo "Please enable Javascript";
	exit;
}
require_once "config.php";

//we set up subject
$subject = isset($_REQUEST['email_message']) ? $_REQUEST['email_message'] : "Message from Magik Help site - Contact Form";

//let's validate and return errors if required
$data = $mail->validateDynamic(array('required_error' => $requiredMessage, 'email_error' => $invalidEmail), $_REQUEST);

$html = '<body style="margin: 10px;">
<div style="width: 640px; font-family: Arial, Helvetica, sans-serif; font-size: 11px;">
  <h2>' . $subject . '</h2>
';

foreach ($data['fields'] as $label => $val) {
	$html .= '<p>' . $label . ': ' . $val . '</p>';
}

 $html .= '</body>';

// To send HTML mail, the Content-type header must be set
$headers  = 'MIME-Version: 1.0' . "\r\n";
$headers .= 'Content-type: text/html; charset=iso-8859-1' . "\r\n";

// Additional headers
$headers .= 'To: '.$to."\r\n";
$headers .= 'From: Magik Help Contact<contact@magikhelper.com>' . "\r\n";


// Mail it
mail($to, $subject, $html, $headers);


$result = array('success' => 1);

echo json_encode($result);
exit;