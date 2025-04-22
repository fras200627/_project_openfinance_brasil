/***********************************************************************/
ALTER SESSION SET "_ORACLE_SCRIPT"=true;
/
/***********************************************************************/
CREATE OR REPLACE NONEDITIONABLE PACKAGE TICAN2.PKG_USERS_MAINTENANCE
AS

/* Validate User Password **********************************************/
FUNCTION FN_VALIDATE_USER(p_LOGIN       IN TICAN2.TB_USERS.login%TYPE,
                          p_PASSWORD    IN TICAN2.TB_USERS.password%TYPE)
RETURN VARCHAR2;
                         
/* Change User Password ************************************************/
FUNCTION FN_CHANGE_USER_PASSWORD(p_LOGIN                IN TICAN2.TB_USERS.login%TYPE,
                                 p_CURRENT_PASSWORD     IN TICAN2.TB_USERS.password%TYPE,
                                 p_NEW_PASSWORD         IN TICAN2.TB_USERS.password%TYPE,
                                 p_CONFIRM_NEW_PASSWORD IN TICAN2.TB_USERS.password%TYPE)
RETURN VARCHAR2;   

/* Change User Status ************************************************/         
FUNCTION FN_CHANGE_USER_STATUS(p_LOGIN_ADMIN          IN TICAN2.TB_USERS.login%TYPE,
                               p_PASSWORD_ADMIN       IN TICAN2.TB_USERS.password%TYPE,
                               p_USER_LOGIN           IN TICAN2.TB_USERS.login%TYPE,
                               p_USER_NEW_STATUS      IN TICAN2.TB_USERS.status%TYPE)
RETURN VARCHAR2;
                  
/* Create a new User ***************************************************/
PROCEDURE SP_INSERT_USER(p_LOGIN       IN TICAN2.TB_USERS.login%TYPE,
                         p_PASSWORD    IN TICAN2.TB_USERS.password%TYPE,
                         p_PROFILE     IN TICAN2.TB_USERS.profile%TYPE,
                         p_EMAIL       IN TICAN2.TB_USERS.email%TYPE,
                         p_DEPARTMENT  IN TICAN2.TB_USERS.department%TYPE,
                         p_USER_CODE   IN TICAN2.TB_USERS.user_code%TYPE,
                         newUser       OUT SYS_REFCURSOR); 

END PKG_USERS_MAINTENANCE;
/

CREATE OR REPLACE NONEDITIONABLE PACKAGE BODY TICAN2.PKG_USERS_MAINTENANCE
AS

/*****************************************
*
*****************************************/
FUNCTION FN_ENCRYPT_TEXT(p_TEXT IN VARCHAR2)
RETURN VARCHAR2
IS
p_ENCRYPTO VARCHAR2(200);
BEGIN
    SELECT
        DBMS_CRYPTO.encrypt(UTL_RAW.CAST_TO_RAW(p_TEXT),
                            TICAN2.PKG_TICAN_DICTIONARY.Security#Crypto_Algorithm,
                            UTL_RAW.CAST_TO_RAW(TICAN2.PKG_TICAN_DICTIONARY.Security#Key_Secret))
    INTO
        p_ENCRYPTO
    FROM DUAL;

    RETURN p_ENCRYPTO;
END FN_ENCRYPT_TEXT;

/*****************************************
*
*****************************************/
FUNCTION FN_DECRYPT_TEXT(p_TEXT_ENCRYPTED IN VARCHAR2)
RETURN VARCHAR2
IS
p_DECRYPTO VARCHAR2(200);
BEGIN
    SELECT
        UTL_RAW.CAST_TO_varchar2(
            DBMS_CRYPTO.decrypt(p_TEXT_ENCRYPTED,
                                TICAN2.PKG_TICAN_DICTIONARY.Security#Crypto_Algorithm,
                                UTL_RAW.CAST_TO_RAW(TICAN2.PKG_TICAN_DICTIONARY.Security#Key_Secret)
                                )
        )
    INTO
        p_DECRYPTO
    FROM DUAL;

    RETURN p_DECRYPTO;
END FN_DECRYPT_TEXT;

/*****************************************
*
*****************************************/
FUNCTION FN_VALIDATE_PWD(p_PWD_Text IN VARCHAR2,
                         p_PWD IN VARCHAR2)
RETURN BOOLEAN
IS
p_PWD_ENCRYPT VARCHAR2(200);
BEGIN
    SELECT
        DBMS_CRYPTO.encrypt(UTL_RAW.CAST_TO_RAW(p_PWD_Text),
                            TICAN2.PKG_TICAN_DICTIONARY.Security#Crypto_Algorithm,
                            UTL_RAW.CAST_TO_RAW(TICAN2.PKG_TICAN_DICTIONARY.Security#Key_Secret))
    INTO
        p_PWD_ENCRYPT
    FROM DUAL;

    IF (p_PWD_ENCRYPT = p_PWD) THEN
        RETURN TRUE;
    END IF;
    RETURN FALSE;
END;


/*****************************************
*
*****************************************/
FUNCTION FN_VALIDATE_USER(p_LOGIN       IN TICAN2.TB_USERS.login%TYPE,
                          p_PASSWORD    IN TICAN2.TB_USERS.password%TYPE)
RETURN VARCHAR2
IS
v_Id                   NUMBER:=0;
p_PASSWORD_ENCRYPTED   VARCHAR2(200);
BEGIN

    SELECT u.ID, u.Password INTO v_Id, p_PASSWORD_ENCRYPTED
    FROM TICAN2.TB_USERS u
    WHERE
    Upper(TRIM(u.Login)) = Upper(TRIM(p_LOGIN));

    IF (FN_ENCRYPT_TEXT(p_PASSWORD) != p_PASSWORD_ENCRYPTED) THEN
        RAISE TICAN2.PKG_TICAN_DICTIONARY.Users#LoginValidateRaise;
    END IF;

    RETURN 'TRUE';
    
EXCEPTION
    WHEN TICAN2.PKG_TICAN_DICTIONARY.Users#LoginValidateRaise OR no_data_found THEN
        RAISE_APPLICATION_ERROR(TICAN2.PKG_TICAN_DICTIONARY.Users#LoginValidateCode, 
                            TICAN2.PKG_TICAN_DICTIONARY.Users#LoginValidateMessage);

    WHEN OTHERS THEN RAISE_APPLICATION_ERROR(-20000, 
        TICAN2.PKG_TICAN_DICTIONARY.FN_BUILD_RAISE_ERROR(regexp_substr(dbms_utility.format_call_stack,
                             'package\s+body\s+([^[:space:]]+)',1,1,'i',1), 
                             regexp_substr(regexp_substr(dbms_utility.format_call_stack, 
                             'package\s+body\s+([^[:space:]]+)',1,1,'i',1),'[^.]+$'),
                             SQLERRM,
                             DBMS_UTILITY.FORMAT_ERROR_BACKTRACE));
END;

/*****************************************
*
*****************************************/
FUNCTION FN_CHANGE_USER_PASSWORD(p_LOGIN                IN TICAN2.TB_USERS.login%TYPE,
                                 p_CURRENT_PASSWORD     IN TICAN2.TB_USERS.password%TYPE,
                                 p_NEW_PASSWORD         IN TICAN2.TB_USERS.password%TYPE,
                                 p_CONFIRM_NEW_PASSWORD IN TICAN2.TB_USERS.password%TYPE)
RETURN VARCHAR2
IS
v_PWD     VARCHAR2(200);
BEGIN

    --Validate Login ------------------------------------------------------
    IF (p_LOGIN IS NULL OR NOT (LENGTH(TRIM(p_LOGIN)) BETWEEN 5 AND 20)) THEN
        RAISE TICAN2.PKG_TICAN_DICTIONARY.Users#LoginValueRaise;
    END IF;

    --Validate Current Password -------------------------------------------
    IF (TRIM(p_CURRENT_PASSWORD) IS NULL OR NOT (LENGTH(TRIM(p_CURRENT_PASSWORD)) BETWEEN 5 AND 20)) THEN
        RAISE TICAN2.PKG_TICAN_DICTIONARY.Users#PasswordValueRaise;
    END IF;

    --Validate New Password -----------------------------------------------
    IF (TRIM(p_NEW_PASSWORD) IS NULL OR NOT (LENGTH(TRIM(p_NEW_PASSWORD)) BETWEEN 5 AND 20)) THEN
        RAISE TICAN2.PKG_TICAN_DICTIONARY.Users#NewPasswordValueRaise;
    END IF;

    --Validate Confirm Password -------------------------------------------
    IF (TRIM(p_CONFIRM_NEW_PASSWORD) IS NULL OR NOT (LENGTH(TRIM(p_CONFIRM_NEW_PASSWORD)) BETWEEN 5 AND 20)) THEN
        RAISE TICAN2.PKG_TICAN_DICTIONARY.Users#ConfirmPasswordValueRaise;
    END IF;

    --Validate New/Confirm Passwords ----------------------------------------
    IF (p_CURRENT_PASSWORD = p_NEW_PASSWORD) THEN
        RAISE TICAN2.PKG_TICAN_DICTIONARY.Users#New_x_CurrentPasswordValueRaise;
    END IF;
    IF (p_NEW_PASSWORD != p_CONFIRM_NEW_PASSWORD) THEN
        RAISE TICAN2.PKG_TICAN_DICTIONARY.Users#New_x_ConfirmPasswordValueRaise;
    END IF;

    --Validate User Login and Password -------------------------------------
    IF (FN_VALIDATE_USER(p_LOGIN, p_CURRENT_PASSWORD) = 'FALSE') THEN
        RAISE TICAN2.PKG_TICAN_DICTIONARY.Users#LoginValidateRaise;
    END IF;

    --Encrypt new password
    v_PWD:= FN_ENCRYPT_TEXT(p_NEW_PASSWORD); 
    
    --Update password
    UPDATE TICAN2.TB_USERS u 
    SET u.password  = v_PWD, 
    u.modify_at     = current_timestamp,
    u.user_code     = p_LOGIN
    WHERE UPPER(TRIM(u.login)) = UPPER(TRIM(p_LOGIN));
    COMMIT;

    RETURN 'TRUE';
EXCEPTION
    WHEN TICAN2.PKG_TICAN_DICTIONARY.Users#LoginValueRaise THEN
        RAISE_APPLICATION_ERROR(TICAN2.PKG_TICAN_DICTIONARY.Users#LoginValueCode, 
                            TICAN2.PKG_TICAN_DICTIONARY.Users#LoginValueMessage);

    WHEN TICAN2.PKG_TICAN_DICTIONARY.Users#PasswordValueRaise THEN
        RAISE_APPLICATION_ERROR(TICAN2.PKG_TICAN_DICTIONARY.Users#PasswordValueCode, 
                            TICAN2.PKG_TICAN_DICTIONARY.Users#PasswordValueMessage);

    WHEN TICAN2.PKG_TICAN_DICTIONARY.Users#NewPasswordValueRaise THEN
        RAISE_APPLICATION_ERROR(TICAN2.PKG_TICAN_DICTIONARY.Users#NewPasswordValueCode, 
                            TICAN2.PKG_TICAN_DICTIONARY.Users#NewPasswordValueMessage);

    WHEN TICAN2.PKG_TICAN_DICTIONARY.Users#ConfirmPasswordValueRaise THEN
        RAISE_APPLICATION_ERROR(TICAN2.PKG_TICAN_DICTIONARY.Users#ConfirmPasswordValueCode, 
                            TICAN2.PKG_TICAN_DICTIONARY.Users#ConfirmPasswordValueMessage);

    WHEN TICAN2.PKG_TICAN_DICTIONARY.Users#LoginValidateRaise THEN
        RAISE_APPLICATION_ERROR(TICAN2.PKG_TICAN_DICTIONARY.Users#LoginValidateCode, 
                            TICAN2.PKG_TICAN_DICTIONARY.Users#LoginValidateMessage);

    WHEN TICAN2.PKG_TICAN_DICTIONARY.Users#New_x_CurrentPasswordValueRaise THEN
        RAISE_APPLICATION_ERROR(TICAN2.PKG_TICAN_DICTIONARY.Users#New_x_CurrentPasswordValueCode, 
                            TICAN2.PKG_TICAN_DICTIONARY.Users#New_x_CurrentPasswordValueMessage);

    WHEN TICAN2.PKG_TICAN_DICTIONARY.Users#New_x_ConfirmPasswordValueRaise THEN
        RAISE_APPLICATION_ERROR(TICAN2.PKG_TICAN_DICTIONARY.Users#New_x_ConfirmPasswordValueCode, 
                            TICAN2.PKG_TICAN_DICTIONARY.Users#New_x_ConfirmPasswordValueMessage);

    WHEN OTHERS THEN
        ROLLBACK; 
        RAISE_APPLICATION_ERROR(-20000, 
        TICAN2.PKG_TICAN_DICTIONARY.FN_BUILD_RAISE_ERROR(regexp_substr(dbms_utility.format_call_stack,
                             'package\s+body\s+([^[:space:]]+)',1,1,'i',1), 
                             regexp_substr(regexp_substr(dbms_utility.format_call_stack, 
                             'package\s+body\s+([^[:space:]]+)',1,1,'i',1),'[^.]+$'),
                             SQLERRM,
                             DBMS_UTILITY.FORMAT_ERROR_BACKTRACE));
END;

/*****************************************
*
*****************************************/
FUNCTION FN_CHANGE_USER_STATUS(p_LOGIN_ADMIN          IN TICAN2.TB_USERS.login%TYPE,
                               p_PASSWORD_ADMIN       IN TICAN2.TB_USERS.password%TYPE,
                               p_USER_LOGIN           IN TICAN2.TB_USERS.login%TYPE,
                               p_USER_NEW_STATUS      IN TICAN2.TB_USERS.status%TYPE)
RETURN VARCHAR2
IS
v_PWD     VARCHAR2(200);
v_COUNT   NUMBER:= 0;
BEGIN

    --Validate Login ------------------------------------------------------
    IF (p_LOGIN_ADMIN IS NULL OR NOT (LENGTH(TRIM(p_LOGIN_ADMIN)) BETWEEN 5 AND 20)) THEN
        RAISE TICAN2.PKG_TICAN_DICTIONARY.Users#LoginValueRaise;
    END IF;

    --Validate Current Password -------------------------------------------
    IF (TRIM(p_PASSWORD_ADMIN) IS NULL OR NOT (LENGTH(TRIM(p_PASSWORD_ADMIN)) BETWEEN 5 AND 20)) THEN
        RAISE TICAN2.PKG_TICAN_DICTIONARY.Users#PasswordValueRaise;
    END IF;

    --Validate User Admin Login and Password ------------------------------
    IF (FN_VALIDATE_USER(p_LOGIN_ADMIN, p_PASSWORD_ADMIN) = 'FALSE') THEN
        RAISE TICAN2.PKG_TICAN_DICTIONARY.Users#LoginValidateRaise;
    END IF;

    --Validate User Admin to change ststus --------------------------------
    SELECT COUNT(*) INTO v_COUNT FROM TICAN2.TB_USERS u
    WHERE
    u.LOGIN = p_LOGIN_ADMIN
    AND u.PROFILE = 'ADMIN' AND u.status =  'ACTIVE';
    IF (SQL%NOTFOUND OR v_COUNT = 0) THEN
        RAISE TICAN2.PKG_TICAN_DICTIONARY.Users#AdminNotFoundRaise;
    END IF;

    --Validate User login --------------------------------------------------
    IF (p_USER_LOGIN IS NULL OR NOT (LENGTH(TRIM(p_USER_LOGIN)) BETWEEN 5 AND 20)) THEN
        RAISE TICAN2.PKG_TICAN_DICTIONARY.Users#LoginValueRaise;
    END IF;

    --Verify User Login exists ---------------------------------------------
    SELECT COUNT(*) INTO v_COUNT FROM TICAN2.TB_USERS u
    WHERE 
    UPPER(TRIM(u.login)) = UPPER(TRIM(p_USER_LOGIN));
    IF (v_COUNT = 0) THEN
        RAISE TICAN2.PKG_TICAN_DICTIONARY.Users#LoginNotFoundRaise;
    END IF;

    --Validate User New Status ---------------------------------------------
    IF (TRIM(p_USER_NEW_STATUS) IS NULL 
        OR 
        UPPER(TRIM(p_USER_NEW_STATUS)) NOT IN ('ACTIVE', 'LOCKED', 'BLOCKED', 'INACTIVE', 'DISABLED')) THEN
        RAISE TICAN2.PKG_TICAN_DICTIONARY.Users#StatusValueRaise;
    END IF;

    --Update status
    UPDATE TICAN2.TB_USERS u 
    SET 
    u.status = UPPER(TRIM(p_USER_NEW_STATUS)),
    u.modify_at     = current_timestamp,
    u.user_code     = p_LOGIN_ADMIN
    WHERE UPPER(TRIM(u.login)) = UPPER(TRIM(p_USER_LOGIN));
    COMMIT;

    RETURN 'TRUE';
EXCEPTION
    WHEN TICAN2.PKG_TICAN_DICTIONARY.Users#LoginValueRaise THEN
        RAISE_APPLICATION_ERROR(TICAN2.PKG_TICAN_DICTIONARY.Users#LoginValueCode, 
                            TICAN2.PKG_TICAN_DICTIONARY.Users#LoginValueMessage);

    WHEN TICAN2.PKG_TICAN_DICTIONARY.Users#PasswordValueRaise THEN
        RAISE_APPLICATION_ERROR(TICAN2.PKG_TICAN_DICTIONARY.Users#PasswordValueCode, 
                            TICAN2.PKG_TICAN_DICTIONARY.Users#PasswordValueMessage);

    WHEN TICAN2.PKG_TICAN_DICTIONARY.Users#LoginValidateRaise THEN
        RAISE_APPLICATION_ERROR(TICAN2.PKG_TICAN_DICTIONARY.Users#LoginValidateCode, 
                            TICAN2.PKG_TICAN_DICTIONARY.Users#LoginValidateMessage);

    WHEN TICAN2.PKG_TICAN_DICTIONARY.Users#AdminNotFoundRaise THEN
        RAISE_APPLICATION_ERROR(TICAN2.PKG_TICAN_DICTIONARY.Users#AdminNotFoundCode, 
                            TICAN2.PKG_TICAN_DICTIONARY.Users#AdminNotFoundMessage);

    WHEN TICAN2.PKG_TICAN_DICTIONARY.Users#LoginNotFoundRaise THEN
        RAISE_APPLICATION_ERROR(TICAN2.PKG_TICAN_DICTIONARY.Users#LoginNotFoundCode, 
                            TICAN2.PKG_TICAN_DICTIONARY.Users#LoginNotFoundMessage);
    WHEN TICAN2.PKG_TICAN_DICTIONARY.Users#StatusValueRaise THEN
        RAISE_APPLICATION_ERROR(TICAN2.PKG_TICAN_DICTIONARY.Users#StatusValueCode, 
                            TICAN2.PKG_TICAN_DICTIONARY.Users#StatusValueMessage);

    WHEN OTHERS THEN
        ROLLBACK; 
        RAISE_APPLICATION_ERROR(-20000, 
        TICAN2.PKG_TICAN_DICTIONARY.FN_BUILD_RAISE_ERROR(regexp_substr(dbms_utility.format_call_stack,
                             'package\s+body\s+([^[:space:]]+)',1,1,'i',1), 
                             regexp_substr(regexp_substr(dbms_utility.format_call_stack, 
                             'package\s+body\s+([^[:space:]]+)',1,1,'i',1),'[^.]+$'),
                             SQLERRM,
                             DBMS_UTILITY.FORMAT_ERROR_BACKTRACE));
END;

/*****************************************
*
*****************************************/
PROCEDURE SP_INSERT_USER(p_LOGIN       IN TICAN2.TB_USERS.login%TYPE,
                         p_PASSWORD    IN TICAN2.TB_USERS.password%TYPE,
                         p_PROFILE     IN TICAN2.TB_USERS.profile%TYPE,
                         p_EMAIL       IN TICAN2.TB_USERS.email%TYPE,
                         p_DEPARTMENT  IN TICAN2.TB_USERS.department%TYPE,
                         p_USER_CODE   IN TICAN2.TB_USERS.user_code%TYPE,
                         newUser       OUT SYS_REFCURSOR)
IS
v_PWD_CRYPTO VARCHAR2(200);
v_ID         NUMBER:= 0;

exFieldNotNull EXCEPTION;
PRAGMA EXCEPTION_INIT (exFieldNotNull, -20001);
BEGIN

    v_PWD_CRYPTO:= FN_ENCRYPT_TEXT(p_PASSWORD);
    
    -- Execute Insert query -------------------------------------------------
    INSERT INTO TICAN2.TB_USERS
      (LOGIN, PASSWORD, PROFILE, EMAIL,
       DEPARTMENT, STATUS, USER_CODE)
    VALUES
      (p_LOGIN, v_PWD_CRYPTO, p_PROFILE, p_EMAIL,
       p_DEPARTMENT, 'LOCKED', p_USER_CODE)
    RETURNING ID INTO v_ID;
    COMMIT;

    -- Return newUser registry ----------------------------------------------
    OPEN newUser FOR
    SELECT
        u.id, u.login, '****' AS PASSWORD,
        u.profile, u.email, u.department,
        u.status, u.create_at, u.modify_at,
        u.user_code
     FROM TICAN2.tb_users u
     WHERE id = v_ID;
     
EXCEPTION
    WHEN OTHERS THEN 
    ROLLBACK;
    RAISE_APPLICATION_ERROR(-20000, 
    TICAN2.PKG_TICAN_DICTIONARY.FN_BUILD_RAISE_ERROR(regexp_substr(dbms_utility.format_call_stack,
                   'package\s+body\s+([^[:space:]]+)',1,1,'i',1), 
                   regexp_substr(regexp_substr(dbms_utility.format_call_stack, 
                   'package\s+body\s+([^[:space:]]+)',1,1,'i',1),'[^.]+$'),
                   SQLERRM,
                   DBMS_UTILITY.FORMAT_ERROR_BACKTRACE));
END;


END PKG_USERS_MAINTENANCE;
/
GRANT ALL PRIVILEGES ON "TICAN2"."PKG_USERS_MAINTENANCE" TO TICAN2;
GRANT EXECUTE ON "TICAN2"."PKG_USERS_MAINTENANCE" TO "TICAN2_USER";
/
