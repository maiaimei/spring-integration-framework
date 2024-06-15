package cn.maiaimei.spring.integration.sftp.config.rule;

import lombok.Data;

/**
 * BaseSftpInboundRule
 */
@Data
public class BaseSftpInboundRule {

  /**
   * the rule id
   */
  private String id;
  /**
   * the rule name
   */
  private String name;
  /**
   * the schema for SFTP connection
   */
  private String schema;
  /**
   * the cron expression
   */
  private String cron = "* * * * * ?";
  /**
   * max messages per poll
   */
  private long maxMessagesPerPoll = 100;
  /**
   * the files match this pattern will send to remote host
   */
  private String pattern;
  /**
   * the source path to download files on remote host
   */
  private String remoteSource;
  /**
   * the temp path of downloading files on remote host
   */
  private String remoteTemp;
  /**
   * the archive path of downloaded files on remote host
   */
  private String remoteArchive;
  /**
   * the path of downloaded files on local host
   */
  private String local;
  /**
   * Specify a SpEL expression for files renaming during download.
   */
  private String renameExpression;
  /**
   * whether passes files only one time
   */
  private boolean acceptOnce;
  private boolean archiveByDate;
}
