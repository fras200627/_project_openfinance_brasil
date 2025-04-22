/* Session settings */
--SELECT * FROM NLS_SESSION_PARAMETERS;
--SELECT * FROM NLS_DATABASE_PARAMETERS;
--SELECT * FROM NLS_INSTANCE_PARAMETERS;
-- Change session settings
ALTER SESSION SET NLS_DATE_FORMAT = 'DD/MM/YYYY';
ALTER SESSION SET NLS_NUMERIC_CHARACTERS = ',.';
ALTER SESSION SET NLS_TIME_FORMAT = 'HH24:MI:SSXFF';
ALTER SESSION SET NLS_TIMESTAMP_FORMAT = 'DD/MM/YYYY HH24:MI:SSXFF';
ALTER SESSION SET NLS_TIME_TZ_FORMAT = 'HH24:MI:SSXFF TZR';
ALTER SESSION SET NLS_TIMESTAMP_TZ_FORMAT = 'DD/MM/YYYY HH24:MI:SSXFF TZR';
--
ALTER SESSION SET "_ORACLE_SCRIPT" = true;

--
select * from v$version;
select * from v$active_services;
select * from v$session_connect_info;
select * from v$session_event;

--
select * from v$session where username like '%%';
select * from gv$session s;
alter system kill session 'sid,serial#';
alter system disconnect session 'sid,serial#' immediate;

--
select 
s.inst_id, s.sid, s.serial#, p.spid, s.username, s.program
from   
gv$session s
join gv$process p on p.addr = s.paddr and p.inst_id = s.inst_id
where  s.type != 'BACKGROUND';

--
select * from dict_columns order by table_name, column_name;
select * from dictionary order by table_name, comments;
-- 
select * from all_users order by username;
--
select 
* 
from sys.all_objects 
where 
owner like '%%' 
and object_name like '%%' 
and object_type like '%%'
order by 
owner, object_type, object_name;

--
select 
* 
from all_tab_columns 
where 
owner like '%%' 
and table_name like '%%' 
and column_name like '%%'
order by 
owner, table_name, column_name;

--
select 
* 
from all_col_comments
where 
owner like '%%' 
and table_name like '%%' 
and column_name like '%%'
and comments like '%%'
order by 
owner, table_name, column_name;

--
select 
* 
from all_constraints
where 
owner like '%%' 
and table_name like '%%' 
and constraint_type like '%%'
and constraint_name like '%%'
order by 
owner, table_name, constraint_type;

--
select 
* 
from all_indexes
where
owner like '%%'
and table_owner like '%%'
and table_name like '%%'
order by
table_owner, table_name, index_name;


