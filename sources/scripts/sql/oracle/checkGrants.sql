select 'revoke ' || privilege || ' from ' || grantee || ';' from DBA_SYS_PRIVS where grantee like '%USER_NAME_IN_UPPER_CASE%';
select 'revoke ' || privilege || ' on ' || owner || '.'|| table_name || ' from ' || grantee || ';' from DBA_TAB_PRIVS where grantee like '%USER_NAME_IN_UPPER_CASE%' and grantor = '__GRANTOR__';
select 'revoke ' || granted_role || ' from ' || grantee || ';' from DBA_ROLE_PRIVS where grantee like '%USER_NAME_IN_UPPER_CASE%';
