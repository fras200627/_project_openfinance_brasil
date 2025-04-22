/***********************************************************************/
ALTER SESSION SET "_ORACLE_SCRIPT"=true;
/

CREATE OR REPLACE NONEDITIONABLE PACKAGE TICAN2."PKG_TICAN_DICTIONARY" IS
/* Key Secret definition **********************************************
*  Attention!!! This secret is not suitable for staying in this location.
*  This is just an example.
**********************************************************************/
Security#Key_Secret CONSTANT VARCHAR2(200):= 'SECRET_PROJECT_TICAN';

/* Crypto Algorithms **************************************************
  4353 := dbms_crypto.DES_CBC_PKCS5
  4356 := dbms_crypto.ENCRYPT_AES256
  4358 := dbms_crypto.ENCRYPT_AES128
  OR
  dbms_crypto.? FOR MORE options
***********************************************************************/
Security#Crypto_Algorithm CONSTANT NUMBER:= 4353;

/* Base for Error Raised **********************************************/
ErrorRaised VARCHAR2(2000):='TICAN INTERCEPTOR ERROR' || CHR(13) || CHR(13) ||
                    'Error Raised [<SQLERRM>]' || CHR(13) || CHR(13) ||
                    'Package: [<PACKAGE>]' || CHR(13) ||
                    'Method: [<METHOD>]' || CHR(13) ||
                    'Error in <LINE>'  ||
                    CHR(13) || CHR(13);


/* Users Raise Controls ***************************************************************
 * Codes -20100 to -20120
 *************************************************************************************/
Users#LoginDuplicateRaise               EXCEPTION;
Users#LoginDuplicateCode                NUMBER:= -20101;
Users#LoginDuplicateMessage             VARCHAR2(2000):= 'User Login already exists!';

Users#LoginValueRaise                   EXCEPTION;
Users#LoginValueCode                    NUMBER:= -20102;
Users#LoginValueMessage                 VARCHAR2(2000):= 'User Login is invalid! Must been 5 to 20 characters';

Users#LoginNotFoundRaise                EXCEPTION;
Users#LoginNotFoundCode                 NUMBER:= -20110;
Users#LoginNotFoundMessage              VARCHAR2(2000):= 'User Login not found!';

Users#LoginValidateRaise                EXCEPTION;
Users#LoginValidateCode                 NUMBER:= -20111;
Users#LoginValidateMessage              VARCHAR2(2000):= 'User Login or Password is invalid!';

Users#PasswordValueRaise                EXCEPTION;
Users#PasswordValueCode                 NUMBER:= -20109;
Users#PasswordValueMessage              VARCHAR2(2000):= 'User Password is invalid! Must been 5 to 20 characters';

Users#NewPasswordValueRaise             EXCEPTION;
Users#NewPasswordValueCode              NUMBER:= -20112;
Users#NewPasswordValueMessage           VARCHAR2(2000):= 'New Password is invalid! Must been 5 to 20 characters!';

Users#ConfirmPasswordValueRaise         EXCEPTION;
Users#ConfirmPasswordValueCode          NUMBER:= -20113;
Users#ConfirmPasswordValueMessage       VARCHAR2(2000):= 'Confirm Password is invalid! Must been 5 to 20 characters!';

Users#New_x_ConfirmPasswordValueRaise   EXCEPTION;
Users#New_x_ConfirmPasswordValueCode    NUMBER:= -20114;
Users#New_x_ConfirmPasswordValueMessage VARCHAR2(2000):= 'Password confirmation is not the same as new password!';

Users#New_x_CurrentPasswordValueRaise   EXCEPTION;
Users#New_x_CurrentPasswordValueCode    NUMBER:= -20115;
Users#New_x_CurrentPasswordValueMessage VARCHAR2(2000):= 'The new password is the same as the current password!';

Users#AdminNotFoundRaise                EXCEPTION;
Users#AdminNotFoundCode                 NUMBER:= -20103;
Users#AdminNotFoundMessage              VARCHAR2(2000):= 'The user responsible for "creating the new user" or ' || CHR(13) ||
                                        '"update status a existing user" is invalid! ' || CHR(13) ||
                                        'Must be an existing user (valid login), have user profile = ADMIN and ' || CHR(13) ||
                                        'user status = ACTIVE';

Users#ProfileValueRaise                 EXCEPTION;
Users#ProfileValueCode                  NUMBER:= -20104;
Users#ProfileValueMessage               VARCHAR2(2000):= 'User Profile is invalid! ' || CHR(13) ||
                                        'Valid Profiles is "ADMIN", "USER", "OPERATOR" or "ASSISTENT"';

Users#EmailValueRaise                   EXCEPTION;
Users#EmailValueCode                    NUMBER:= -20105;
Users#EmailValueMessage                 VARCHAR2(2000):= 'The users email is invalid! ' || CHR(13) ||
                                        'It must be 10 to 500 characters long ' || CHR(13) ||
                                        'and have a valid format. E.g.: abc@host.com';

Users#EmailDuplicateRaise               EXCEPTION;
Users#EmailDuplicateCode                NUMBER:= -20106;
Users#EmailDuplicateMessage             VARCHAR2(2000):= 'User Email already exists!';

Users#DepartmentValueRaise              EXCEPTION;
Users#DepartmentValueCode               NUMBER:= -20107;
Users#DepartmentValueMessage            VARCHAR2(2000):= 'User Department: is invalid! ' || CHR(13) ||
                                        'Valid Departments is "SALES", "PRODUCTS", "PROJECTS" or "MARKETING"';

Users#StatusValueRaise                  EXCEPTION;
Users#StatusValueCode                   NUMBER:= -20108;
Users#StatusValueMessage                VARCHAR2(2000):= 'User Status is invalid! ' || CHR(13) ||
                                        'Valid Status is "ACTIVE", "LOCKED", "BLOCKED", "INACTIVE" or "DISABLED"';


/*****************************************
*
*****************************************/
FUNCTION FN_BUILD_RAISE_ERROR(PackageName       IN VARCHAR2,
                              MethodName        IN VARCHAR2,
                              ErrorMessage      IN VARCHAR2,
                              BackTraceMessage  IN VARCHAR2)
RETURN VARCHAR2;


end PKG_TICAN_DICTIONARY;
/

CREATE OR REPLACE NONEDITIONABLE PACKAGE BODY "TICAN2"."PKG_TICAN_DICTIONARY" IS

/*****************************************
*
*****************************************/
FUNCTION FN_BUILD_RAISE_ERROR(PackageName       IN VARCHAR2,
                              MethodName        IN VARCHAR2,
                              ErrorMessage      IN VARCHAR2,
                              BackTraceMessage  IN VARCHAR2)
RETURN VARCHAR2
IS
BEGIN

    ErrorRaised:= REPLACE(ErrorRaised, '<SQLERRM>', ErrorMessage);
    ErrorRaised:= REPLACE(ErrorRaised, '<PACKAGE>', PackageName);
    ErrorRaised:= REPLACE(ErrorRaised, '<METHOD>', MethodName);
    ErrorRaised:= REPLACE(ErrorRaised, '<LINE>', REPLACE(TRIM(Substr(BackTraceMessage,
                                                 Instr(BackTraceMessage, 'line'))),CHR(13), ''));

    RETURN ErrorRaised;
END;

END PKG_TICAN_DICTIONARY;
/
GRANT ALL PRIVILEGES ON "TICAN2"."PKG_TICAN_DICTIONARY" TO TICAN2;
GRANT EXECUTE ON "TICAN2"."PKG_TICAN_DICTIONARY" TO "TICAN2_USER";
/
