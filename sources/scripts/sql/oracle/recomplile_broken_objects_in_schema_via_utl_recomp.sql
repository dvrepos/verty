-- details https://oracle-base.com/articles/misc/recompiling-invalid-schema-objects 
-- Schema level.
EXEC UTL_RECOMP.recomp_serial('SCOTT');
EXEC UTL_RECOMP.recomp_parallel(4, 'SCOTT');

-- Database level.
EXEC UTL_RECOMP.recomp_serial();
EXEC UTL_RECOMP.recomp_parallel(threads => 4, schema => null);

@$ORACLE_HOME/rdbms/admin/utlrp.sql

-- Using job_queue_processes value.
EXEC UTL_RECOMP.recomp_parallel();
EXEC UTL_RECOMP.recomp_parallel(NULL, 'SCOTT');

