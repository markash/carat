CREATE SCHEMA IF NOT EXISTS "JBATCH";

DROP TABLE IF EXISTS "JBATCH".JOBSTATUS;
DROP TABLE IF EXISTS "JBATCH".STEPSTATUS;
DROP TABLE IF EXISTS "JBATCH".CHECKPOINTDATA;
DROP TABLE IF EXISTS "JBATCH".JOBINSTANCEDATA;
DROP TABLE IF EXISTS "JBATCH".EXECUTIONINSTANCEDATA;
DROP TABLE IF EXISTS "JBATCH".STEPEXECUTIONINSTANCEDATA;

CREATE TABLE "JBATCH".JOBINSTANCEDATA(
  jobinstanceid		serial not null PRIMARY KEY,
  name		character varying (512),
  apptag VARCHAR(512)
);

CREATE TABLE "JBATCH".EXECUTIONINSTANCEDATA(
  jobexecid		serial not null PRIMARY KEY,
  jobinstanceid	bigint not null REFERENCES "JBATCH".JOBINSTANCEDATA (jobinstanceid),
  createtime	timestamp,
  starttime		timestamp,
  endtime		timestamp,
  updatetime	timestamp,
  parameters	bytea,
  batchstatus		character varying (512),
  exitstatus		character varying (512)
);

CREATE TABLE "JBATCH".STEPEXECUTIONINSTANCEDATA(
  stepexecid			serial not null PRIMARY KEY,
  jobexecid			bigint not null REFERENCES "JBATCH".EXECUTIONINSTANCEDATA (jobexecid),
  batchstatus         character varying (512),
  exitstatus			character varying (512),
  stepname			character varying (512),
  readcount			integer,
  writecount			integer,
  commitcount         integer,
  rollbackcount		integer,
  readskipcount		integer,
  processskipcount	integer,
  filtercount			integer,
  writeskipcount		integer,
  startTime           timestamp,
  endTime             timestamp,
  persistentData		bytea
);

CREATE TABLE "JBATCH".JOBSTATUS (
  id		bigint not null REFERENCES "JBATCH".JOBINSTANCEDATA (jobinstanceid),
  obj		bytea
);

CREATE TABLE "JBATCH".STEPSTATUS(
  id		bigint not null REFERENCES "JBATCH".STEPEXECUTIONINSTANCEDATA (stepexecid),
  obj		bytea
);

CREATE TABLE "JBATCH".CHECKPOINTDATA(
  id		character varying (512),
  obj		bytea
);