SELECT 
       sysdate,
       s.sid,
       s.serial#,
       s.username,
       s.machine,
       ROUND(sl.elapsed_seconds/60) || ':' || MOD(sl.elapsed_seconds,60) elapsed
       ,ROUND(sl.time_remaining/60) || ':' || MOD(sl.time_remaining,60) remaining
       ,ROUND(sl.sofar/sl.totalwork*100, 2) progress_pct
      ,sl.sofar
      ,sl.totalwork
      ,sl.sql_id
FROM   v$session s,
       v$session_longops sl
WHERE  s.sid     = sl.sid
AND    s.serial# = sl.serial# 
and sl.totalwork != sl.sofar
;
