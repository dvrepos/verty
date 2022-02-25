select
    'Status: '
   || decode (bitand(tr.flag,128),128,'Rolling back',tr.status)
   || '. Started: '
   || tr.start_time
   || ', ('
   || to_char(round((sysdate - tr.start_date)*24*60*60,0))
   || ' seconds ago. Undo blocks: '
   || tr.used_ublk
   || ').' as transaction_info
from v$transaction tr
where tr.addr in (
  select s.taddr
  from v$session s
  where s.sid = :sid
);
