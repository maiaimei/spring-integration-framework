package cn.maiaimei.example.factory;

import cn.maiaimei.example.TestConfig;
import cn.maiaimei.example.config.SimpleSftpOutboundRule;
import cn.maiaimei.samples.constants.StringConstants;
import cn.maiaimei.samples.utils.IOUtils;
import java.io.File;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.context.IntegrationFlowContext;
import org.springframework.integration.samples.sftp.SftpConstants;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ActiveProfiles("local")
@PropertySource("classpath:application-local.yml")
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {SftpOutboundFactoryTest.ContextConfig.class})
public class SftpOutboundFactoryTest {

  @Autowired
  private IntegrationFlowContext flowContext;

  @Autowired
  private SftpOutboundFactory sftpOutboundFactory;

  @BeforeEach
  public void setUp() {
    // java -Djava.io.tmpdir=/path/to/tmpdir YourApp
    System.setProperty("java.io.tmpdir", SftpConstants.TMP_DIR);
  }

  @Test
  public void testSimpleSftpOutbound() throws InterruptedException {
    String tmpdir = System.getProperty("java.io.tmpdir");

    SimpleSftpOutboundRule rule = new SimpleSftpOutboundRule();
    rule.setSchema("s1");
    rule.setName("r1");
    rule.setPattern("*.txt");
    rule.setLocal(tmpdir + "local");
    rule.setSent(tmpdir + "sent");
    rule.setRemote("/path/to/destination");

    final String uuid = UUID.randomUUID().toString();
    IOUtils.writeStringToFile(String.format("%s%s%s%s",
        rule.getLocal(), File.separator, uuid, StringConstants.FILE_SUFFIX_TXT), uuid);

    final IntegrationFlow flow = sftpOutboundFactory.createSimpleSftpOutboundFlow(rule);
    final String flowId = flowContext.registration(flow).register().getId();

    TimeUnit.SECONDS.sleep(1);

    flowContext.remove(flowId);
  }

  @Import({TestConfig.class, SftpOutboundFactory.class})
  public static class ContextConfig {

  }
}