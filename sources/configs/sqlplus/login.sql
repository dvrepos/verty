-- Put this file to %SQLPATH% folder
set serveroutput on size unlimited
set pagesize 50000
set linesize 135
set long 50000
set trimspool on
set tab off
set timing on
def _editor = "path_to_editor"

define gname=idle
column global_name new_value gname
select lower(user) || '@' || instance_name || '(' || host_name || ')'
  as global_name
from v$instance;

set sqlprompt '&gname> '
alter session set nls_date_format = 'dd.mm.yyyy hh24:mi:ss';
alter session set NLS_TIMESTAMP_FORMAT = 'DD.MM.YYYY HH24:MI:SSXFF';
