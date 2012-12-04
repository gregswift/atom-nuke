package org.atomnuke.syslog.parser;

/**
 * @author zinic
 */
public enum SyslogParserState {

   START,
   PRIORITY,
   VERSION,
   TIMESTAMP_HEAD,
   TIMESTAMP_CONTENT,
   HOSTNAME_HEAD,
   HOSTNAME_CONTENT,
   APPNAME_HEAD,
   APPNAME_CONTENT,
   PROCESS_ID_HEAD,
   PROCESS_ID_CONTENT,
   MESSAGE_ID_HEAD,
   MESSAGE_ID_CONTENT,
   STRUCTURED_DATA_HEAD,
   STRUCTURED_DATA_NAME,
   STRUCTURED_DATA_VALUE,
   STOP,
   ERROR
}
