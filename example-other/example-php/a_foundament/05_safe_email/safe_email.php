<html>
<body>
<?php

/**
 * FILTER_SANITIZE_EMAIL 过滤器从字符串中删除电子邮件的非法字符
 * FILTER_VALIDATE_EMAIL 过滤器验证电子邮件地址的值
 * @param $field
 * @return bool
 */
function spamcheck($field)
{
    //filter_var() sanitizes the e-mail
    //address using FILTER_SANITIZE_EMAIL
    $field=filter_var($field, FILTER_SANITIZE_EMAIL);

    //filter_var() validates the e-mail
    //address using FILTER_VALIDATE_EMAIL
    if(filter_var($field, FILTER_VALIDATE_EMAIL))
    {
        return TRUE;
    }
    else
    {
        return FALSE;
    }
}

if (isset($_REQUEST['email']))
{//if "email" is filled out, proceed

    //check if the email address is invalid
    $mailcheck = spamcheck($_REQUEST['email']);
    if ($mailcheck==FALSE)
    {
        echo "Invalid input";
    }
    else
    {//send email
        $email = $_REQUEST['email'] ;
        $subject = $_REQUEST['subject'] ;
        $message = $_REQUEST['message'] ;
        mail("someone@example.com", "Subject: $subject",
            $message, "From: $email" );
        echo "Thank you for using our mail form";
    }
}
else
{//if "email" is not filled out, display the form
    echo "<form method='post' action='mailform.php'>
 Email: <input name='email' type='text'>

 Subject: <input name='subject' type='text'>

 Message:

 <textarea name='message' rows='15' cols='40'>
 </textarea>

 <input type='submit'>
 </form>";
}
?>

</body>
</html>