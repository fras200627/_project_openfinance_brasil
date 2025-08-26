DECLARE

CURSOR cTables IS
SELECT DISTINCT OBJECT_NAME 
  FROM ALL_OBJECTS
 WHERE OBJECT_TYPE = 'TABLE'
   AND OWNER = 'OFB';
   
CURSOR cViews IS
SELECT DISTINCT OBJECT_NAME 
  FROM ALL_OBJECTS
 WHERE OBJECT_TYPE = 'VIEW'
   AND OWNER = 'OFB';   
   
CURSOR cSequences IS
SELECT DISTINCT OBJECT_NAME 
  FROM ALL_OBJECTS
 WHERE OBJECT_TYPE = 'SEQUENCE'
   AND OWNER = 'OFB';  
   
obj VARCHAR2(200):= '';
obj2 VARCHAR2(200):= '';
BEGIN
  OPEN cTables;
  LOOP FETCH cTables INTO obj;
  EXIT WHEN cTables%NOTFOUND;
       obj:=  'OFB.' || obj;
       
       EXECUTE IMMEDIATE 'GRANT ALL ON ' || obj || '  TO OFB, OFB_OWNER';
       EXECUTE IMMEDIATE 'GRANT SELECT, UPDATE, DELETE, INSERT ON ' || obj || ' TO OFB_USER';
       
       dbms_output.put_line ('table: ' || 'OFB.' || obj);
  END LOOP;
  CLOSE cTables;
  
  OPEN cViews;
  LOOP FETCH cViews INTO obj;
  EXIT WHEN cViews%NOTFOUND;
       obj:=  'OFB.' || obj;
       obj2:= REPLACE(obj, 'OFB.', '');
       obj2:= REPLACE(obj2, 'VW_', '');
       obj2:= 'OFB.List_of_' || LOWER(obj2);
       
      
       dbms_output.put_line (obj2);
       
       EXECUTE IMMEDIATE 'GRANT ALL ON ' || obj || '  TO OFB, OFB_OWNER';
       EXECUTE IMMEDIATE 'GRANT SELECT, UPDATE, DELETE, INSERT ON ' || obj || ' TO OFB_USER';
       
       EXECUTE IMMEDIATE 'CREATE OR REPLACE SYNONYM ' || obj2 || ' FOR ' || obj;
       EXECUTE IMMEDIATE 'GRANT ALL ON ' || obj2 || ' TO OFB, OFB_OWNER';
       EXECUTE IMMEDIATE 'GRANT SELECT ON ' || obj2 || ' TO OFB_USER';
       
       dbms_output.put_line ('VIEW: ' || 'OFB.' || obj);
  END LOOP;
  CLOSE cViews;
 
  OPEN cSequences;
  LOOP FETCH cSequences INTO obj;
  EXIT WHEN cSequences%NOTFOUND;
       obj:=  'OFB.' || obj;
       
       EXECUTE IMMEDIATE 'GRANT ALL ON ' || obj || '  TO OFB, OFB_OWNER';
       EXECUTE IMMEDIATE 'GRANT SELECT ON ' || obj || ' TO OFB_USER';
       
       dbms_output.put_line ('SEQUENCE: ' || 'OFB.' || obj);
  END LOOP;
  CLOSE cSequences; 
  
END;
