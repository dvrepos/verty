select
  case
    when OBJECT_TYPE = 'PACKAGE BODY' then 'ALTER PACKAGE ' || OBJECT_NAME || ' COMPILE BODY;'
    when OBJECT_TYPE = 'PACKAGE' then 'ALTER PACKAGE ' || OBJECT_NAME || ' COMPILE;'
    when OBJECT_TYPE = 'PROCEDURE' then 'ALTER PROCEDURE ' || OBJECT_NAME || ' COMPILE;'
    when OBJECT_TYPE = 'FUNCTION' then 'ALTER FUNCTION ' || OBJECT_NAME || ' COMPILE;'
    when OBJECT_TYPE = 'TRIGGER' then 'ALTER TRIGGER ' || OBJECT_NAME || ' COMPILE;'
    when OBJECT_TYPE = 'VIEW' then 'ALTER VIEW ' || OBJECT_NAME || ' COMPILE;'
    when OBJECT_TYPE = 'MATERIALIZED VIEW' then 'ALTER MATERIALIZED VIEW ' || OBJECT_NAME || ' COMPILE;'
    when OBJECT_TYPE = 'TYPE BODY' then 'ALTER TYPE ' || OBJECT_NAME || ' COMPILE BODY;'
    else OBJECT_NAME || '/' || OBJECT_TYPE
  end as sql
  from user_objects
 where status = 'INVALID'
 order by 1;

