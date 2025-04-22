/****************************************************************************/
CREATE OR REPLACE NONEDITIONABLE PACKAGE TICAN2.PKG_PRODUCTS_MAINTENANCE
AS

PROCEDURE SP_LIST_PRODUCTS(p_PageStart IN   NUMBER,
                           p_PageRows  IN   NUMBER,
                           c_RESULT         OUT  SYS_REFCURSOR);

PROCEDURE SP_LIST_PRODUCTS_PRICES(p_PageStart     IN NUMBER,
                                  p_PageRows      IN NUMBER,
                                  c_RESULT        OUT SYS_REFCURSOR);
                                  
END PKG_PRODUCTS_MAINTENANCE;
/

CREATE OR REPLACE NONEDITIONABLE PACKAGE BODY TICAN2.PKG_PRODUCTS_MAINTENANCE
AS

/*
SELECT
  REGEXP_SUBSTR('An example sentence.', '[^e]+', 1, level) AS parts
FROM dual
CONNECT BY REGEXP_SUBSTR('An example sentence.', '[^e]+', 1, level) IS NOT NULL;
*/

PROCEDURE SP_LIST_PRODUCTS(p_PageStart IN   NUMBER,
                           p_PageRows  IN   NUMBER,
                           c_RESULT         OUT  SYS_REFCURSOR)
IS
LIST#PAGE_START_ERROR EXCEPTION;
LIST#PAGE_ROWS_ERROR  EXCEPTION;
LIST#QUERY            VARCHAR2(4000):= 
'SELECT 
   p.* 
 FROM
   TICAN2.VIEW_PRODUCTS_X_SUBSTANCES p
 p_Where
 p_Order_by
 OFFSET ((p_PageStart - 1) * p_PageRows) ROWS 
 FETCH NEXT p_PageRows ROWS ONLY';
BEGIN


  --Validations params -------------------------------
  IF (p_PageStart < 1) THEN
    RAISE LIST#PAGE_START_ERROR;
  END IF;
  IF (p_PageRows < 1 OR p_PageRows > 1000) THEN
    RAISE LIST#PAGE_ROWS_ERROR;
  END IF;
  LIST#QUERY:=  REPLACE(LIST#QUERY, 'p_PageStart', p_PageStart);
  LIST#QUERY:=  REPLACE(LIST#QUERY, 'p_PageRows', p_PageRows);

  --Build Where Clausules ----------------------------
  LIST#QUERY:=  REPLACE(LIST#QUERY, 'p_Where', ' WHERE p.SUBSTANCE LIKE ' || '''%CEFALEXINA%''');

  --Build Order by -----------------------------------
  LIST#QUERY:=  REPLACE(LIST#QUERY, 'p_Order_by', ' ORDER  BY p.ID ');

  --Execute query ------------------------------------
  OPEN c_RESULT FOR LIST#QUERY;

EXCEPTION
  WHEN LIST#PAGE_START_ERROR THEN
    RAISE_APPLICATION_ERROR(-20901, 'Page Start invalid! Page Start minimum is "1"');
  WHEN LIST#PAGE_ROWS_ERROR THEN
    RAISE_APPLICATION_ERROR(-20902, 'Page Rows invalid! Page Rows minimum is "1" and maximum is "1000"');
  WHEN OTHERS THEN
    RAISE_APPLICATION_ERROR(-20999, 'Unexpected Error! >>' || SQLCODE || ' >> ' || SQLERRM);
END;

PROCEDURE SP_LIST_PRODUCTS_PRICES(p_PageStart     IN NUMBER,
                                  p_PageRows      IN NUMBER,
                                  c_RESULT        OUT SYS_REFCURSOR)
IS
LIST#PAGE_START_ERROR EXCEPTION;
LIST#PAGE_ROWS_ERROR  EXCEPTION;
LIST#QUERY            VARCHAR2(4000):= 
'SELECT 
   p.id, p.PRODUCT, p.SUBSTANCE, p.STATUS,
   p.base_price,
   p.price_icms_12,
   p.price_icms_17,
   p.price_icms_18
 FROM
   TICAN2.VIEW_PRODUCTS_X_SUBSTANCES p
 p_Where
 p_Order_by
 OFFSET ((p_PageStart - 1) * p_PageRows) ROWS 
 FETCH NEXT p_PageRows ROWS ONLY';
BEGIN

  --Validations params -------------------------------
  IF (p_PageStart < 1) THEN
    RAISE LIST#PAGE_START_ERROR;
  END IF;
  IF (p_PageRows < 1 OR p_PageRows > 1000) THEN
    RAISE LIST#PAGE_ROWS_ERROR;
  END IF;
  LIST#QUERY:=  REPLACE(LIST#QUERY, 'p_PageStart', p_PageStart);
  LIST#QUERY:=  REPLACE(LIST#QUERY, 'p_PageRows', p_PageRows);

  --Build Where Clausules ----------------------------
  LIST#QUERY:=  REPLACE(LIST#QUERY, 'p_Where', ' WHERE p.SUBSTANCE LIKE ' || '''%CEFALEXINA%''');

  --Build Order by -----------------------------------
  LIST#QUERY:=  REPLACE(LIST#QUERY, 'p_Order_by', ' ORDER  BY p.ID ');

  --Execute query ------------------------------------ 
  OPEN c_RESULT FOR LIST#QUERY;                  

END;

END;
/

GRANT ALL PRIVILEGES ON TICAN2.PKG_PRODUCTS_MAINTENANCE TO TICAN2;
GRANT EXECUTE ON TICAN2.PKG_PRODUCTS_MAINTENANCE TO TICAN2_USER;
/
